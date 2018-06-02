/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FileMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.FileExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.bryllyant.kona.http.KMimeTypes;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service(FileService.SERVICE_PATH)
public class FileServiceImpl 
		extends KAbstractService<File,FileExample,FileMapper>
		implements FileService {
	
	private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private FileMapper fileMapper;
    
	@Autowired
	private KConfig config;

	@Autowired
	UserService userService;

	@Autowired
    AppUtil appUtil;
    


    // called once when app/service is initialized
	@PostConstruct
	public void _init() {
		logger.debug("FileService: _init() called");
	}


    
    // called once before app/service is shutdown
    @PreDestroy
    public void _shutdown() {
    	logger.debug("FileService: _shutdown() called");
        
        try {
			List<File> tempFiles = fetchTempFiles(false);
            
            for (File file : tempFiles) {
                remove(file);
            }
		} catch (IOException e) {
            logger.error(e.getMessage(), e);
		}
    }

	@Override @SuppressWarnings("unchecked")
	protected FileMapper getMapper() {
		return fileMapper;
	}
	

	protected String getPublicBaseUrl() {
        return config.getString("system.files.baseUrl");
	}
    

	protected String getLocalBasePath() {
        return config.getString("system.files.localBasePath");
	}
	
    protected String generateUid() {
        return appUtil.uuid();
    }

    protected String toPublicPath(String localPath) {
        return localPath;
    }



    @Override
    public void validate(File file) {
        if (file.getCreatedDate() == null) {
            file.setCreatedDate(new Date());
        }

        if (file.getUid() == null) {
            file.setUid(generateUid());
        }

        file.setUpdatedDate(new Date());
    }



    @Override
    public List<File> fetchByCriteria(Integer startRow, Integer resultSize,
                                      String[] sortOrder, Map<String, Object> filter, boolean distinct,
                                      boolean withData) throws IOException {

        List<File> list = fetchByCriteria(startRow, resultSize, sortOrder, filter, distinct);

        if (!withData) {
            return list;
        }

        for (File file : list) {
            fetchFile(file);
        }

        return list;
    }



    @Override
    public File fetchById(Long fileId) {
        try {
            return fetchById(fileId, false);
        } catch (IOException e) {
            throw new KServiceException(e);
        }
    }



    @Override
    public File fetchById(Long fileId, boolean withData) throws IOException {
        File file = getMapper().selectByPrimaryKey(fileId);

        if (file == null) return null;

        if (withData) {
            file = fetchFile(file);
        }

        return file;
    }



    @Override
    public File fetchByUid(String uid, boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, withData));
    }



    @Override
    public List<File> fetchByParentId(Long parentId, boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
        return fetchByCriteria(0, 99999, null, filter, false, withData);
    }



    @Override
    public List<File> fetchTempFiles(boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("tempFile", true);
        return fetchByCriteria(0, 99999, null, filter, false, withData);
    }



    @Override
    public List<File> fetchByUserId(Long userId, boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false, withData);
    }



    @Override
    public File fetchByUrlPath(String urlPath, boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", urlPath);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, withData));
    }



    @Override
    public File fetchByLocalPath(String localPath, boolean withData) throws IOException {
        Map<String,Object> filter = KMyBatisUtil.createFilter("localPath", localPath);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, withData));
    }



    @Override
    public File add(File file) {
        validate(file);

        if (file.getData() == null) {
            throw new KServiceException("data is null");
        }

        Integer size = file.getData().length;

        file.setSize(size.longValue());

        String localPath = createFilePath(file);

        file.setLocalPath(localPath);

        if (file.getUrlPath() == null) {
            file.setUrlPath(toPublicPath(localPath));
        }


        logger.debug("server add(File) called:"
                + "\n\tdata size: " + size
                + "\n\tfile: " + file);

        try {
            saveFile(file);
            getMapper().insert(file);

        } catch (IOException e) {
            throw new KServiceException(e);
        }

        return file;
    }



    @Override
    public void remove(File file) {
        if (file == null) {
            return;
        }

        try {
            // first delete the underlying file
            deleteFile(file);

            getMapper().deleteByPrimaryKey(file.getId());
        } catch (IOException e) {
            throw new KServiceException(e);
        }
    }



    @Override
    public void removeById(Long fileId) {
        File file = fetchById(fileId);
        remove(file);
    }



    @Override
    public File update(File file) {
        validate(file);

        boolean updateData = false;

        if (file.getData() != null) {
            Integer size = file.getData().length;
            file.setSize(size.longValue());
            updateData = true;
        }

        // just for debugging purposes
        File old = fetchById(file.getId());
        logger.debug("server update:\n\t" + old);


        try {
            if (updateData)  {
                saveFile(file);
            }

            getMapper().updateByPrimaryKey(file);
        } catch (IOException e) {
            throw new KServiceException(e);
        }

        return file;
    }





    @Override
    public String toAbsoluteUrl(String publicPath) {
        if (publicPath == null) return null;

        String publicUrl = null;

        String publicBaseUrl = getPublicBaseUrl();

        if (publicBaseUrl != null) {
            if (publicPath.startsWith("/")) {
                publicPath = publicPath.substring(1, publicPath.length());
            }

            if (!publicBaseUrl.endsWith("/")) {
                publicBaseUrl += "/";
            }

            publicUrl = publicBaseUrl + publicPath;
        }

        return publicUrl;
    }




    // Removing this
    /*
	@Override
	public String toServerLocalPath(String publicPath) {
		String localBasePath = getLocalBasePath();

		if (!localBasePath.endsWith(("/"))) {
			localBasePath += "/";
		}

		Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", publicPath);

		File file = KMyBatisUtil.fetchOne(fetchByCriteria(filter));

		if (file == null) {
			throw new KServiceException("File not found for path: " + publicPath);
		}

		String fullPath = localBasePath + file.getLocalPath();

        return fullPath;
	}
    */



    @Override
    public File fetchFileByUrlPath(String publicPath) throws IOException {

        //Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", publicPath);
        //File file = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, false));

        File file = fetchByUrlPath(publicPath, false);

        if (file == null) {
            throw new IOException("File not found for path: " + publicPath);
        }

        String localBasePath = getLocalBasePath();

        if (!localBasePath.endsWith(("/"))) {
            localBasePath += "/";
        }

        String fullPath = localBasePath + file.getLocalPath();

        logger.debug("fullPath: " + fullPath);

        byte[] fileData = KFileUtil.toByteArray(fullPath);


        String contentType = file.getContentType();

        if (contentType == null) {
            try {
                contentType = KMimeTypes.getContentType(fileData);
            } catch (Exception e) {
                logger.debug("Unable to determine contentType for fileId: " + file.getId(), e);
            }
        }

        if (contentType == null) {
            throw new IOException("Cannot determine file content type: " + fullPath);
        }

        Path path = Paths.get(fullPath);
        String filename = path.getFileName().toString();

        if (file.getName() == null && filename != null) {
            file.setName(filename);
        }

        logger.debug("fetchFileByUrlPath: filename: " + file.getName());

        file.setData(fileData);
        file.setContentType(contentType);

        return file;
    }



    @Override
    public File fetchFile(File file) throws IOException {
        String urlPath = file.getUrlPath();

        if (urlPath == null) {
            file = fetchById(file.getId(), false);
            urlPath = file.getUrlPath();
        }

        File f = fetchFileByUrlPath(urlPath);

        file.setData(f.getData());

        if (file.getSize() == null) {
            file.setSize(f.getSize());
        }

        if (file.getName() == null) {
            file.setName(f.getName());
        }

        if (file.getContentType() == null) {
            file.setContentType(f.getContentType());
        }

        return file;
    }



    @Override
    public void saveFile(File file) throws IOException {
        String localBasePath = getLocalBasePath();

        if (!localBasePath.endsWith(("/"))) {
            localBasePath += "/";
        }

        String fullPath = localBasePath + file.getLocalPath();

        deleteFile(file);

        KFileUtil.writeFile(fullPath, file.getData());

        if (file.getContentType() == null || file.getContentType().equalsIgnoreCase("application/octet-stream")) {
            logger.debug("Probing contentType for file: " + fullPath);
            String contentType = KMimeTypes.getContentType(file.getData());
            logger.debug("Probed contentType: " + contentType);

            if (contentType != null) {
                file.setContentType(contentType);

                if (file.getType() == null ) {
                    file.setType(File.getTypeByContentType(contentType));
                }
            }
        }
    }



    @Override
    public void deleteFile(File file) throws IOException {
        if (file == null || file.getLocalPath() == null) {
            logger.warn("deleteFile: localPath not defined for file: " + file);
            return;
        }

        String localBasePath = getLocalBasePath();

        if (!localBasePath.endsWith(("/"))) {
            localBasePath += "/";
        }

        String fullPath = localBasePath + file.getLocalPath();

        java.io.File f = new java.io.File(fullPath);

        if (!f.isFile()) {
            logger.warn("Local path is not a file: " + fullPath);
            return;
        }

        if (!f.exists()) {
            logger.warn("Local path does not exist: " + fullPath);
            return;
        }

        // delete the file
        f.delete();

        // delete empty parent folders
        if (f.getParentFile() != null) {
            deleteEmptyParentFolder(localBasePath, f.getParentFile().getAbsolutePath(), true);
        }
    }



    public void deleteEmptyParentFolder(String rootPath, String currentPath, boolean recursive) throws IOException {
        logger.debug("deleteEmptyParentFolder called:"
                + "\nrootPath: " + rootPath
                + "\ncurrentPath: " + currentPath
                + "\nrecursive: " + recursive);

        if (currentPath == null) {
            return;
        }

        if (rootPath == null) {
            rootPath = "/";
        }

        if (currentPath.equals(rootPath)) {
            logger.info("currentPath equals rootPath ... done");
            return;
        }

        java.io.File current = new java.io.File(currentPath);

        if (!current.exists()) {
            logger.info("currentPath does not exist: " + current);
            current = current.getParentFile();
        }

        if (!current.exists()) {
            logger.info("currentPath or its parentPath does not exist: " + current + " ... aborting");
            return;
        }

        if (current.isFile()) {
            logger.info("currentPath is a file and not a directory ... setting current to parent.");
            current = current.getParentFile();
        }

        if (!current.isDirectory()) {
            logger.info("currentPath is not a directory ... aborting.");
            return;
        }

        if (current.getAbsolutePath().equals(rootPath)) {
            logger.error("currentPath equals rootPath ... done");
            return;
        }

        java.io.File[] files = current.listFiles();

        if (files == null) {
            logger.error("cannot get file list for directory .. aborting");
            return;
        }

        if (files.length > 0) {
            logger.debug("directory is not empty ... done");
            return;
        }

        if (current.delete()) {
            logger.debug("Removing empty directory: " + current.getAbsolutePath());

            if (recursive && current.getParentFile() != null) {
                deleteEmptyParentFolder(rootPath, current.getParentFile().getAbsolutePath(), recursive);
            }
        } else {
            logger.error("Error removing directory: " + current + " ... aborting");
        };

    }



    private String createFilePath(File file) {

        String fileKey = UUID.randomUUID().toString();

        try {
            fileKey = KEncryptUtil.MD5(fileKey).toUpperCase();
        } catch (Exception e) {
            throw new KServiceException(e);
        }

        int hashcode = fileKey.hashCode();
        int mask = 255;

        int dir1 = hashcode & mask;
        int dir2 = (hashcode >> 8) & mask;
        int dir3 = ((hashcode >> 8) >> 8) & mask;
        int dir4 = (((hashcode >> 8) >> 8)>>8) & mask;

        StringBuilder path = new StringBuilder();
        path.append(String.format("%04d", dir1));
        path.append(java.io.File.separator);
        path.append(String.format("%04d", dir2));
        path.append(java.io.File.separator);
        path.append(String.format("%04d", dir3));
        path.append(java.io.File.separator);
        path.append(String.format("%04d", dir4));
        path.append(fileKey);

        logger.debug("Created path string: " + path);

        return path.toString();
    }



    @Override
    public boolean isAuthorized(File file, String clientId, String accessToken) {
        logger.debug("FileService: isAuthorized: checking file: " + file);

        if (file.getAccess() == null) {
            throw new KServiceException("File access level not defined.");
        }

        if (file.getAccess() == File.Access.PUBLIC) return true;
        if (file.getAccess() == File.Access.NONE) return false;

        // FIXME: any valid app can access
        if (file.getAccess() == File.Access.APP) {
            // access level is app and we have a valid user
            return true;
        }


        // SYSTEM, OWNER, Contact and FRIENDS can access
        if (file.getAccess() == File.Access.FRIEND) {
            return true;
        }

        User user = userService.fetchByAccessToken(accessToken, false);

        if (user == null || user.getDeletedDate() != null || !user.isEnabled()) return false;

        // FIXME: only SYSTEM users can access
        if (file.getAccess() == File.Access.SYSTEM) {
            if (user.getType() == User.Type.SYSTEM) return true;

            if (userService.hasRole(user, "ADMIN")) return true;

            if (userService.hasRole(user, "SYSTEM")) return true;

            return false;
        }


        // FIXME: SYSTEM, OWNER can access
        if (file.getAccess() == File.Access.OWNER){
            if (file.getUserId() == null) {
                logger.warn("File has User access but userId is null");
                return false;
            }

            if (file.getUserId().equals(user.getId())) return true;
            return false;
        }

        return false;
    }
    
}
