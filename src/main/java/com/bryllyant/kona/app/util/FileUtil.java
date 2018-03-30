/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.util;

import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.KMedia;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.media.model.KImage;
import com.bryllyant.kona.media.util.KImageUtil;
import com.bryllyant.kona.rest.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * FileUtil 
 */
@Component
public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);



	@Autowired
	private KConfig config;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private ApiAuthService apiAuthService;
	

    
    protected void resizeImage(KMedia media) throws IOException {
        Integer maxWidth = config.getInteger("image.maxWidth", -1);
        Integer maxHeight = config.getInteger("image.maxHeight", -1);
        
        File file = fileService.fetchById(media.getFileId(), true);
        
        if (maxWidth > 0 || maxHeight > 0) {
            KImage image = KImageUtil.resizeToMaxWidthAndHeight(
                file.getData(), maxWidth, maxHeight);

            file.setData(image.getData());
            file.setSize(image.getSize());
            fileService.update(file);

            media.setWidth(image.getWidth());
            media.setHeight(image.getHeight());
            media.setSize(image.getSize());
        }
    }
    

    
    
    // NOTE: this method simply uploads a file into a File object 
    // but does not persist it in any way. It's the responsibility 
    // of the caller to ultimately call fileService().add(file)
    // to store the file and generate a unique ID for it.

    public List<File> upload(
            MultipartHttpServletRequest req, 
            String paramName, 
            User user, 
            File.Access access) throws IOException {

        if (access == null) {
            access = File.Access.PUBLIC;
        }

        List<File> fileList = new ArrayList<>();

        Long tokenId = null;

        try {
            Token token = apiAuthService.getToken();
            tokenId = token.getId();
        } catch (AuthenticationException e) {
            // if we have a valid user and token is invalid, then
            // throw exception. otherwise this is an anonymous user.
            if (user != null) {
                throw new AuthenticationException(e);
            }
        }

        // NOTE: it's assumed that, if not null, contentType[] array
        // length equals number of files being uploaded

        MultiValueMap<String, MultipartFile> map = req.getMultiFileMap();

        List<MultipartFile> multipartFileList = map.get(paramName);

        for (MultipartFile mpf : multipartFileList) {
            long fileSize = mpf.getSize();

            logger.debug(mpf.getOriginalFilename() + " uploaded! ["
                    +  fileSize + "]");

            byte[] data = mpf.getBytes();
            

            // sanity check: make sure filesize and data.lengh match
            Integer _size = new Long(fileSize).intValue();

            if (!_size.equals(data.length)) {
                logger.warn("upload: file size mismatch:"
                        + "\nmpf.getSize(): " + fileSize
                        + "\ndata.length: " + data.length);
            }

            // Don't trust client content-type, see if we can detect it ourselves
            //String contentType = KMimeTypes.getContentType(data);
            //String contentType = mpf.getContentType();
            //logger.debug("upload: detected contentType: " + contentType);
            //if (type == null && contentType != null) {
            //  type = FileType.getInstance(contentType);
            //}

            String contentType = mpf.getContentType();

            File.Type type = File.getTypeByContentType(contentType);

            File file = new File();
            file.setType(type);
            file.setAccess(access);

            if (user != null) {
                file.setUserId(user.getId());
                file.setAccountId(user.getAccountId());
            }

            file.setTokenId(tokenId);
            file.setSrcFilename(mpf.getOriginalFilename());
            file.setSrcHostname(req.getRemoteHost());
            file.setName(mpf.getOriginalFilename());
            file.setContentType(contentType);
            file.setSize(fileSize);
            file.setEnabled(true);
            file.setHidden(false);
            file.setCreatedDate(new Date());

            logger.debug("upload: fileObject (without data): " + file);
            
            if (type != null && type == File.Type.IMAGE && data != null) {
                KImage image = KImageUtil.getNormalizedImage(data);
                data = image.getData();
            }

            file.setData(data);

            fileList.add(file);
        }

        return fileList;
    }



}
