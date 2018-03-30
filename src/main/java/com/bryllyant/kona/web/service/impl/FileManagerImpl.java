package com.bryllyant.kona.web.service.impl;

import java.io.IOException;

import com.bryllyant.kona.web.service.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.service.FileService;


@Service(FileManager.SERVICE_PATH)
public class FileManagerImpl implements FileManager {
	private static Logger logger = LoggerFactory.getLogger(FileManagerImpl.class);

	@Autowired
    private FileService fileService;
    

	//---------------------------------------------------

	@Override
	public File fetchFileByUrlPath(String publicPath) throws IOException {
		logger.debug("fetchFileByUrlPath: publicPath: " + publicPath);
		return fileService.fetchFileByUrlPath(publicPath);
	}

	@Override
	public File fetchFile(File file) throws IOException {
		logger.debug("fetchFile: fileId: " + file.getId());
		return fileService.fetchFile(file);
	}

	@Override
	public void saveFile(File file) throws IOException {
		logger.debug("saveFile: fileId: " + file.getId());
		fileService.saveFile(file);
	}

	@Override
	public void deleteFile(File file) throws IOException {
		logger.debug("deleteFile: fileId: " + file.getId());
		fileService.deleteFile(file);
	}

	@Override
	public boolean isAuthorized(File file, String clientId, String accessToken) {
		boolean authorized = fileService.isAuthorized(file, clientId, accessToken);
		logger.debug("isAuthorized: fileId: " + file.getId() 
		+ "\naccessToken: " + accessToken
		+ "\nauthorized: " + authorized);
		return authorized;
	}
}
