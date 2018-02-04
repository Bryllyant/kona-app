/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.MediaMapper;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.MediaExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractMediaService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(MediaService.SERVICE_PATH)
public class MediaServiceImpl 
		extends KAbstractMediaService<Media,MediaExample,User,File> 
		implements MediaService {
	
	private static Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private KConfig config;

	@Autowired
	private MediaMapper mediaDao;
    
	@Autowired
	UserService userService;
	
	@Autowired
	FileService fileService;
    

	@Override @SuppressWarnings("unchecked")
	protected MediaMapper getDao() {
		return mediaDao;
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
	protected MediaExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		MediaExample example = new MediaExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
		example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
		
		return example;
	}
	

    @Override 
    protected void updateCoords(Long mediaId) {
        getDao().updateCoords(mediaId);
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
		return getDao().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}
    
}
