package com.bryllyant.kona.web.service;

import com.bryllyant.kona.app.service.KFileManager;
import com.bryllyant.kona.app.entity.File;

public interface FileManager extends KFileManager<File> {
    public static final String SERVICE_PATH = "rpc/FileManager";
}
