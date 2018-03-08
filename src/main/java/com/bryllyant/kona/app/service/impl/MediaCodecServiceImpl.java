package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.service.KAbstractMediaCodecService;
import com.bryllyant.kona.app.service.MediaCodecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(MediaCodecService.SERVICE_PATH)
public class MediaCodecServiceImpl 
    extends KAbstractMediaCodecService
    implements MediaCodecService {

	private static Logger logger = LoggerFactory.getLogger(MediaCodecServiceImpl.class);


	
	@Autowired
	private KConfig config;


	
	protected String ffmpeg() {
		String FFMPEG_BINDIR = config.getString("ffmpeg.bindir");
		if (!FFMPEG_BINDIR.endsWith("/")) {
			FFMPEG_BINDIR += "/";
		}
		return FFMPEG_BINDIR + "ffmpeg";
	}
	

	
	protected String ffprobe() {
		String FFMPEG_BINDIR = config.getString("ffmpeg.bindir");
		if (!FFMPEG_BINDIR.endsWith("/")) {
			FFMPEG_BINDIR += "/";
		}
		return FFMPEG_BINDIR + "ffprobe";
	}



	
}

