/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KMedia;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;

public interface KMediaService<
        MEDIA extends KMedia, 
        USER extends KUser, 
        FILE extends KFile
        > extends KEntityService<MEDIA> {

    MEDIA fetchByUid(String uid);
    
    MEDIA fetchByFileId(Long fileId);
    
    MEDIA fetchBySlug(USER user, String folder, String slug);

    MEDIA fetchBySlug(Long accountId, String folder, String slug);

    FILE fetchFileById(Long id);

    FILE fetchFileById(Long id, boolean withData) throws IOException;

    List<MEDIA> fetchByUserId(Long userId);

    List<MEDIA> fetchByAccountId(Long accountId);

    List<MEDIA> fetchByFolderPath(Long accountId, String folder);

    MEDIA createThumbnail(
            MEDIA mediaObject,
            Integer width,
            Integer height,
            boolean force
    ) throws IOException;

    MEDIA add(FILE file) throws IOException;

    MEDIA add(
            FILE file, 
            String folderPath 
    ) throws IOException;

    MEDIA add(
            FILE file, 
            String folderPath, 
            Double latitude, 
            Double longitude, 
            String description
    ) throws IOException;


    MEDIA add(
            USER user,
            String folderPath, 
            String name, 
            String srcFilename, 
            byte[] data, 
            String contentType
    ) throws IOException;


    MEDIA add(
            USER user, 
            String name, 
            byte[] data, 
            String contentType
    ) throws IOException;
  

    List<MEDIA> fetchProximate(
            Double latitude, 
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );
}
