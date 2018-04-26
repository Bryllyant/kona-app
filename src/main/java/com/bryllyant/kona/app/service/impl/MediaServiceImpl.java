/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.MediaMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.MediaExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.media.model.KImage;
import com.bryllyant.kona.media.util.KImageUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(MediaService.SERVICE_PATH)
public class MediaServiceImpl 
		extends KAbstractService<Media, MediaExample, MediaMapper>
		implements MediaService {
	
	private static Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private KConfig config;

	@Autowired
	private MediaMapper mediaMapper;
    
	@Autowired
	UserService userService;
	
	@Autowired
	FileService fileService;
    

	@Override @SuppressWarnings("unchecked")
	protected MediaMapper getMapper() {
		return mediaMapper;
	}
	

	protected Integer getThumbnailWidth() {
	    return config.getInteger("image.thumbnailWidth", null);
	}


	protected Integer getThumbnailHeight() {
	    return config.getInteger("image.thumbnailHeight", null);
	}

    protected Boolean getThumbnailCrop() {
        return config.getBoolean("image.thumbnailCrop", true);
    }


	protected boolean autoGenerateThumbnail() {
	    return config.getBoolean("image.createThumbnail", false);
	}


    protected void updateCoords(Long mediaId) {
        getMapper().updateCoords(mediaId);
    }


	@Override
	public List<Media> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
		return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}



    @Override
    public void validate(Media media) {
        if (media.getCreatedDate() == null) {
            media.setCreatedDate(new Date());
        }

        media.setUpdatedDate(new Date());

        if (media.getUid() == null) {
            media.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(media.getName());

        media.setSlug(slug);
    }



    @Override @Transactional
    public Media update(Media media) {
        media = super.update(media);
        updateCoords(media.getId());
        return media;
    }



    @Override @Transactional
    public Media add(Media media) {
        return add(media, null);
    }


    public Media add(Media media, File file) {
	    return add(media, file, autoGenerateThumbnail());
    }

    public Media add(Media media, File file, boolean thumbnail) {
        try {

            if (file == null) {
                file = fileService.fetchById(media.getFileId(), true);
            }

            if (file.getType() == File.Type.IMAGE) {
                if (media.getWidth() == null || media.getHeight() == null) {
                    media = updateImageInfo(media, file);
                }
            }

            media = super.add(media);

            updateCoords(media.getId());

            if (thumbnail && getThumbnailWidth() != null && getThumbnailHeight() != null && file.getType() == File.Type.IMAGE) {
                media = createThumbnail(media, getThumbnailWidth(), getThumbnailHeight(), getThumbnailCrop(), true);
            }

        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }

        return media;
    }


    @Override @Transactional
    public Media add(User user, String name, byte[] data, String contentType) throws IOException {
        return add(user, null, name, null, data, contentType);
    }


    @Override @Transactional
    public Media add(User user, String folderPath, String name, String srcFilename, byte[] data, String contentType) throws IOException {
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;

        File.Type type = File.getTypeByContentType(contentType);

        if (type == null) {
            type = File.Type.OTHER;
        }

        File file = new File();

        file.setData(data);

        file.setType(type);
        file.setAccess(File.Access.PUBLIC);
        file.setUserId(user.getId());
        file.setAccountId(user.getAccountId());
        file.setTokenId(null);
        file.setSrcFilename(srcFilename);
        file.setSrcHostname(srcHostname);
        file.setName(name);
        file.setContentType(contentType);
        file.setSize(new Long(data.length));
        file.setEnabled(true);
        file.setHidden(false);
        file.setTempFile(tempFile);
        file.setCreatedDate(new Date());

        return add(file, folderPath);
    }




    @Override @Transactional
    public Media add(File file) throws IOException {
        return add(file, null);
    }



    @Override @Transactional
    public Media add(File file, String folderPath) throws IOException {
        return add(file, folderPath, null, null, null);
    }



    @Override @Transactional
    public Media add(
            File file,
            String folderPath,
            Double latitude,
            Double longitude,
            String description
    ) throws IOException {

        file = fileService.save(file);

        Media media = new Media();

        media.setUserId(file.getUserId());
        media.setAccountId(file.getAccountId());
        media.setFileId(file.getId());
        media.setFolderPath(folderPath);
        media.setUrlPath(file.getUrlPath());
        media.setContentType(file.getContentType());
        media.setSize(file.getSize());
        media.setEnabled(true);
        media.setName(file.getName());
        media.setResizeable(true);
        media.setLatitude(latitude);
        media.setLongitude(longitude);
        media.setDescription(description);
        media.setCreatedDate(new Date());

        return add(media, file);
    }




    @Override @Transactional
    public void remove(Media media) {
        if (media == null || media.getId() == null) return;

        // Delete the thumbnail first (if exists) since it will be a child of the main File
        // If File were removed first, thumbnail would cascade delete preventing the underlying file from being deleted.

        if (media.getThumbnailId() != null) {
            fileService.removeById(media.getThumbnailId());
        }

        if (media.getFileId() != null) {
            fileService.removeById(media.getFileId());
        }


        getMapper().deleteByPrimaryKey(media.getId());
    }



    @Override @Transactional(readOnly=true)
    public Media fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override @Transactional(readOnly=true)
    public Media fetchByFileId(Long fileId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("fileId", fileId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override @Transactional(readOnly=true)
    public File fetchFileById(Long id) {
        Media media = fetchById(id);

        if (media == null || media.getFileId() == null) return null;

        return fileService.fetchById(media.getFileId());
    }



    @Override @Transactional(readOnly=true)
    public File fetchFileById(Long id, boolean withData) throws IOException {
        Media media = fetchById(id);

        if (media == null || media.getFileId() == null) return null;

        return fileService.fetchById(media.getFileId(), withData);
    }



    @Override @Transactional(readOnly=true)
    public List<Media> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override @Transactional(readOnly=true)
    public List<Media> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    // TODO: Return all subfolder meta-data as well
    @Override @Transactional(readOnly=true)
    public List<Media> fetchByFolderPath(Long accountId, String folderPath) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("folderPath", folderPath);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override @Transactional(readOnly=true)
    public Media fetchBySlug(Long accountId, String folderPath, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("folderPath", folderPath);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    protected void resizeImage(Media media, Integer maxWidth, Integer maxHeight) throws IOException {
        if (!media.isResizeable()) {
            return;
        }

        File file = fileService.fetchById(media.getFileId(), true);

        byte[] src = file.getData();

        KImage image = KImageUtil.resizeToMaxWidthAndHeight(src, maxWidth, maxHeight);

        file.setData(image.getData());
        file.setSize(image.getSize());

        fileService.update(file);

        media.setWidth(image.getWidth());
        media.setHeight(image.getHeight());
        update(media);
    }



    protected Media updateImageInfo(Media media, File file) throws IOException {
        if (media == null) return null;

        boolean updateFile = false;

        if (file == null || file.getData() == null) {
            file = fileService.fetchById(media.getFileId(), true);
        }

        byte[] data = file.getData();

        if (data == null) {
            logger.info("file data is null");
            return null;
        }

        KImage image = KImageUtil.toImage(data);

        media.setWidth(image.getWidth());
        media.setHeight(image.getHeight());
        media.setBitsPerPixel(image.getBitsPerPixel());

        long dataSize = Long.valueOf(data.length);

        if (file.getSize() == null || !file.getSize().equals(dataSize)) {
            logger.warn("setting file size to: " + dataSize + "  old value: " + file.getSize());
            file.setSize(dataSize);
            updateFile = true;
        }

        String contentType = image.getContentType();

        if (file.getContentType() == null) {
            file.setContentType(contentType);
            updateFile = true;
        }


        //sanity check
        if (contentType != null && !contentType.equalsIgnoreCase(file.getContentType())) {
            file.setContentType(contentType);
            updateFile = true;

            logger.info("Content-type mismatch:"
                    + "\n\tfile id: " + file.getId()
                    + "\n\tfile name: " + file.getName()
                    + "\n\tfile content-type: " + file.getContentType()
                    + "\n\tKImageInfo content-type: " + contentType);
        }

        media.setContentType(file.getContentType());

        logger.debug("Image info:"
                + "\n\tfile name: " + file.getName()
                + "\n\tcontentType: " + media.getContentType()
                + "\n\twidth: " + media.getWidth()
                + "\n\theight: " + media.getHeight()
                + "\n\tbitsPerPixel: " + media.getBitsPerPixel());


        if (updateFile) {
            fileService.update(file);
        }

        return media;
    }



    @Override @Transactional
    public Media createThumbnail(Media media, Integer width, Integer height, boolean crop, boolean force) throws IOException {

        // check if we have a thumbnail for this media file 
        if (media.getThumbnailId() != null) {
            if (!force) {
                return media;
            }

            // remove existing thumbnail file
            fileService.removeById(media.getThumbnailId());
        }

        // FIXME: use cache since we most likely just created this file.
        File file = fileService.fetchById(media.getFileId(), true);

        if (file == null) {
            throw new IllegalArgumentException("Media object file is null: " + media);
        }

        File thumbnailFile = new File();

        thumbnailFile.setUid(uuid());
        thumbnailFile.setType(file.getType());
        thumbnailFile.setAccess(file.getAccess());
        thumbnailFile.setParentId(file.getId());
        thumbnailFile.setTokenId(file.getTokenId());
        thumbnailFile.setUserId(file.getUserId());
        thumbnailFile.setAccountId(file.getAccountId());
        thumbnailFile.setName(file.getName() + "-thumbnail");
        thumbnailFile.setContentType(file.getContentType());
        thumbnailFile.setHidden(file.isHidden());
        thumbnailFile.setEnabled(file.isEnabled());
        thumbnailFile.setCreatedDate(new Date());

        String localPath = file.getLocalPath() + "-thumbnail";

        thumbnailFile.setLocalPath(localPath);

        // Don't create a thumbnail larger than original image.
        if (media.getWidth() != null && media.getHeight() != null) {
            if (media.getWidth() <= width && media.getHeight() <= height) {
                width = media.getWidth();
                height = media.getHeight();
            }
        }

        logger.debug("createThumbnail: targetWidth: [{}]  targetHeight: [{}]", width, height);

        KImage thumbnailResult = KImageUtil.resize(file.getData(), width, height);
        //KImage image = KImageUtil.resizeToMaxWidthAndHeight(src, maxWidth, maxHeight);

        logger.debug("createThumbnail: resized image: [{}]", thumbnailResult);

        if (crop) {
            if (width.equals(height)) {
                thumbnailResult = KImageUtil.centerSquareCrop(thumbnailResult, width);
            } else {
                thumbnailResult = KImageUtil.centerCrop(thumbnailResult, width, height);
            }
        }


        byte[] thumbnailBytes = thumbnailResult.getData();
        Integer thumbnailWidth = thumbnailResult.getWidth();
        Integer thumbnailHeight = thumbnailResult.getHeight();

        thumbnailFile.setData(thumbnailBytes);

        thumbnailFile.setSize(Long.valueOf(thumbnailBytes.length));

        thumbnailFile = fileService.add(thumbnailFile);

        media.setThumbnailId(thumbnailFile.getId());
        media.setThumbnailUrlPath(thumbnailFile.getUrlPath());
        media.setThumbnailSize(thumbnailFile.getSize());
        media.setThumbnailWidth(thumbnailWidth);
        media.setThumbnailHeight(thumbnailHeight);

        media = update(media);


        return media;
    }



    @Override @Transactional(readOnly=true)
    public Media fetchBySlug(User user, String folder, String slug) {
        return fetchBySlug(user.getAccountId(), folder, slug);
    }


}
