/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.MediaMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.MediaExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractMediaService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(MediaService.SERVICE_PATH)
public class MediaServiceImpl 
		extends KAbstractMediaService<Media, MediaExample, MediaMapper,User,File>
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
	

	@Override
	protected Integer getThumbnailWidth() {
	    return config.getInteger("image.thumbnailWidth", null);
	}


	@Override
	protected Integer getThumbnailHeight() {
	    return config.getInteger("image.thumbnailHeight", null);
	}


	@Override
	protected boolean autoGenerateThumbnail() {
	    return config.getBoolean("image.createThumbnail", false);
	}


	@Override 
	protected Media getNewMediaObject() {
		return new Media();
	}


	@Override 
	protected File getNewFileObject() {
		return new File();
	}


	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}


	@Override @SuppressWarnings("unchecked")
	protected FileService getFileService() {
		return fileService;
	}


	 @Override
    protected MediaExample getEntityExampleObject() { return new MediaExample(); }

	

    @Override 
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
    
}
