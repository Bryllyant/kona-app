/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.web.controller.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bryllyant.kona.util.KJsonUtil;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.model.SmsMessage;
import com.bryllyant.kona.app.service.SmsService;


/**
 * Sms Controller.
 */
@Controller
@RequestMapping("/system/sms")
public class SmsController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	SmsService smsService;
	
	public static class Message {
	    public Message() {
	        
	    }
	}

	// ----------------------------------------------------------------------------

	private SmsMessage createMessage(HttpServletRequest req, HttpServletResponse resp) {
		//String messageSid = req.getParameter("MessageSid");
		//String accountSid = req.getParameter("AccountSid");
		//String numMedia = req.getParameter("NumMedia");
		String from = req.getParameter("From");
		String to = req.getParameter("To");
		String body = req.getParameter("Body");
		String numMedia = req.getParameter("NumMedia");
		String channelMessageSid = req.getParameter("MessageSid");

		Map<String,String> mediaMap = null;

		Integer mediaCount = 0;

		if (numMedia != null) {
			mediaCount = Integer.parseInt(numMedia);
		}

		logger.debug("SMS Received:"
				+ "\nFrom: " + from
				+ "\nTo: " + to
				+ "\nBody: " + body
				+ "\nMediaCount: " + mediaCount
				);

		if (mediaCount > 0) {
			mediaMap = new HashMap<String,String>();

			// media params are 0-based
			for (int i=0; i<mediaCount; i++) {
				String contentType = req.getParameter("MediaContentType" + i);
				String url = req.getParameter("MediaUrl" + i);
				mediaMap.put(url, contentType);

				logger.debug("Media: [" + contentType + "]" + url);
			}
		}


		SmsMessage message = new SmsMessage();

		message.setMessageSid(channelMessageSid);
		message.setInbound(true);
		message.setMediaCount(mediaCount);
		message.setMediaMap(mediaMap);
		message.setRawMessage(body);
		message.setMobileNumber(from);
		message.setUser(null);

		return message;

	}

	// ----------------------------------------------------------------------------

	// application/x-www-form-urlencoded 
	// https://www.twilio.com/docs/api/twiml/sms/twilio_request
	@RequestMapping(value="/simulator", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
	public void simulator(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SmsMessage message = createMessage(req, resp);
		logger.debug("SmsController: simulator: message:\n\n" + message);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

	// ----------------------------------------------------------------------------
   
	// application/x-www-form-urlencoded 
	// https://www.twilio.com/docs/api/twiml/sms/twilio_request
	@RequestMapping(method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
	public void receiveMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SmsMessage message = createMessage(req, resp);
		//chatbotService.handleMessage(message);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
    

	// ----------------------------------------------------------------------------

	// don't specify method to allow GET or POST
	//@RequestMapping(value="/status", method=RequestMethod.POST)
	@RequestMapping(value="/status", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded" )
	public void status(HttpServletRequest req, HttpServletResponse resp,
			@RequestBody(required=false) final MultiValueMap<String,Object> multiValueMap) {

		logger.debug("smsStatus callback: " + KJsonUtil.toJson(multiValueMap));

		Map<String,Object> map = multiValueMap.toSingleValueMap();

		smsService.processMessageStatus(map);
        
        // Reply with NO_CONTENT
        // https://www.twilio.com/docs/api/twiml/sms/your_response
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
