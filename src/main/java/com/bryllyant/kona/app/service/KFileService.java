/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KFileService<FILE extends KFile> extends KService, KEntityService<FILE>, KFileManager<FILE> {

    FILE fetchByUid(String uid, boolean withData) throws IOException;

    FILE fetchById(Long id, boolean withData) throws IOException;

    FILE fetchByLocalPath(String localPath, boolean withData) throws IOException;

    FILE fetchByUrlPath(String urlPath, boolean withData) throws IOException;
    
    List<FILE> fetchByParentId(Long parentId, boolean withData) throws IOException;
   
    List<FILE> fetchByUserId(Long userId, boolean withData) throws IOException;
    
    List<FILE> fetchTempFiles(boolean withData) throws IOException;
    
    List<FILE> fetchByCriteria(Integer startRow,  Integer resultSize, 
    		String[] sortOrder, Map<String, Object> filterCriteria, 
            boolean distinct, boolean withData) throws IOException;

    String toAbsoluteUrl(String publicPath);

    
    /*
     * Get the path of this file resource on the local server.
     * 
     * Use with care.  Provided for rare cases when knowledge of file path on the local server is required.
     * 
     * @param publicPath
     * @return
    public String toServerLocalPath(String publicPath);
     */
    
}
