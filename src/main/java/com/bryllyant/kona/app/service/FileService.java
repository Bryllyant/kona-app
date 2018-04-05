/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService extends KService, KEntityService<File>, FileManager {
	String SERVICE_PATH = "rpc/FileService";

    File fetchByUid(String uid, boolean withData) throws IOException;

    File fetchById(Long id, boolean withData) throws IOException;

    File fetchByLocalPath(String localPath, boolean withData) throws IOException;

    File fetchByUrlPath(String urlPath, boolean withData) throws IOException;

    List<File> fetchByParentId(Long parentId, boolean withData) throws IOException;

    List<File> fetchByUserId(Long userId, boolean withData) throws IOException;

    List<File> fetchTempFiles(boolean withData) throws IOException;

    List<File> fetchByCriteria(Integer startRow,  Integer resultSize,
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
