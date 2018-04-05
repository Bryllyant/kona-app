/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;

import java.io.IOException;

/**
 * Interface that is concerned with managing the data (byte[]) attribute
 * of a File object.
 */
public interface FileManager {
    /**
     * Save the data to the local path defined by File.getLocalPath().
     */
    void saveFile(File file) throws IOException;


    /**
     * Delete the file defined by File.getLocalPath().
     */
    void deleteFile(File file) throws IOException;


    /**
     * Return a File object with the data (byte[]) array populated.
     */
    File fetchFile(File file) throws IOException;


    /**
     * Return a File object with the data (byte[]) array populated.
     */
    File fetchFileByUrlPath(String urlPath) throws IOException;


    boolean isAuthorized(File file, String clientId, String accessToken);
}
