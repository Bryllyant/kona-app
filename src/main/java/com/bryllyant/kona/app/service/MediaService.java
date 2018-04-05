/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface MediaService extends KService, KEntityService<Media> {
	String SERVICE_PATH = "rpc/MediaService";

    Media fetchByFileId(Long fileId);

    Media fetchBySlug(User user, String folder, String slug);

    Media fetchBySlug(Long accountId, String folder, String slug);

    File fetchFileById(Long id);

    File fetchFileById(Long id, boolean withData) throws IOException;

    List<Media> fetchByUserId(Long userId);

    List<Media> fetchByAccountId(Long accountId);

    List<Media> fetchByFolderPath(Long accountId, String folder);

    Media createThumbnail(
            Media mediaObject,
            Integer width,
            Integer height,
            boolean force
    ) throws IOException;

    Media add(File file) throws IOException;

    Media add(
            File file,
            String folderPath
    ) throws IOException;

    Media add(
            File file,
            String folderPath,
            Double latitude,
            Double longitude,
            String description
    ) throws IOException;


    Media add(
            User user,
            String folderPath,
            String name,
            String srcFilename,
            byte[] data,
            String contentType
    ) throws IOException;


    Media add(
            User user,
            String name,
            byte[] data,
            String contentType
    ) throws IOException;


    List<Media> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
