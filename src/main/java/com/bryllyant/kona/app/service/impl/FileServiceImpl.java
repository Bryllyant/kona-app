/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.FileMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.FileExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractFileService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service(FileService.SERVICE_PATH)
public class FileServiceImpl 
		extends KAbstractFileService<File, FileExample, FileMapper,User>
		implements FileService {
	
	private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private FileMapper fileMapper;
    
	@Autowired
	private KConfig config;

	@Autowired
	UserService userService;
    


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



	@Override
	protected File getNewObject() {
		return new File();
	}
	


	@Override @SuppressWarnings("unchecked")
	protected FileMapper getMapper() {
		return fileMapper;
	}
	


	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
    

    
    @Override
	protected String getPublicBaseUrl() {
        return config.getString("system.files.baseUrl");
		
	}
    


    @Override
	protected String getLocalBasePath() {
        return config.getString("system.files.localBasePath");
	}
	


	
	 @Override
    protected FileExample getEntityExampleObject() { return new FileExample(); }

	

    
}
