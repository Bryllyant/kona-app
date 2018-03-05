/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.web.controller.system;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bryllyant.kona.app.entity.KEmailEventType;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.templates.KTemplateException;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.SystemService;


/**
 * Email Event Controller.
 */
@Controller
@RequestMapping("/system/email/events")
public class EmailEventController extends BaseController {
    private static Logger logger = Logger.getLogger(EmailEventController.class);



    @Autowired
    private Environment env;

    @Autowired
    private EmailService emailService; 

    @Autowired
    private EmailAddressService emailAddressService; 

    @Autowired
    private SystemService system; 


    
    private void error(Throwable t) {
        system.alert("EmailEventController: Error", t);
        logger.error(t.getMessage(), t);
        throw new RuntimeException(t);
    }
    

	
    @RequestMapping(value="/open/{messageId}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void open(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId) {
        EmailEvent event = createEvent(req, messageId, KEmailEventType.OPENED);
        if (event != null) {
        	emailService.addEvent(event);
        }
        
        // write out a 1x1 transparent pixel
        try {
        	KServletUtil.serveTransparentPixel(resp);
        } catch (IOException e) {
            error(e);
        }
    }
    

    
    @RequestMapping(value="/forward/{messageId}", method=RequestMethod.GET)
    //@ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void forward(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId) {
        EmailEvent event = createEvent(req, messageId, KEmailEventType.FORWARDED);
        if (event != null) {
        	emailService.addEvent(event);
        }
        
        // write out a 1x1 transparent pixel
        try {
        	KServletUtil.serveTransparentPixel(resp);
        } catch (IOException e) {
            error(e);
        }
    }
    

    
    @RequestMapping(value="/print/{messageId}", method=RequestMethod.GET)
    //@ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void print(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId) {
        EmailEvent event = createEvent(req, messageId, KEmailEventType.PRINTED);
        if (event != null) {
        	emailService.addEvent(event);
        }
        
        // write out a 1x1 transparent pixel
        try {
        	KServletUtil.serveTransparentPixel(resp);
        } catch (IOException e) {
            error(e);
        }
    }
    


    @RequestMapping(value="/click/{messageId}", method=RequestMethod.GET)
    //@ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void click(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId,
    		@RequestParam(value="u", required=true) String redirectUrl) {
        
        EmailEvent event = createEvent(req, messageId, KEmailEventType.CLICKED);
        if (event != null) {
        	event.setTarget(redirectUrl);
        	emailService.addEvent(event);
        }
        
        // redirect 
        try {
        	String url = resp.encodeRedirectURL(redirectUrl);
			resp.sendRedirect(url);
		} catch (IOException e) {
            error(e);
		}
    }
    
    


    @RequestMapping(value="/unsubscribe/{messageId}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void click(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId) {
        
        EmailEvent event = createEvent(req, messageId, KEmailEventType.UNSUBSCRIBED);
        if (event != null) {
        	emailService.addEvent(event);
        }
        
        try {
            String confirmationTmpl = env.getProperty("email.templates.optOutConfirmation");
            KServletUtil.writeTemplate(resp, confirmationTmpl, "text/html", null);
		} catch (IOException e) {
            error(e);
		} catch (KTemplateException e) {
            error(e);
		}
    }
    

    
    private EmailEvent createEvent(HttpServletRequest req, String uid, KEmailEventType type) {
        Date now = new Date();
        Email email = emailService.fetchByUid(uid);
        if (email == null) {
        	logger.error("Invalid email uid: " + uid);
            return null;
        }
        
        EmailEvent event = new EmailEvent();
        event.setEmailId(email.getId());
        event.setTypeId(type.getId());
        event.setHostname(KServletUtil.getClientHostname(req));
        event.setUserAgent(KServletUtil.getClientUserAgent(req));
        event.setEventDate(now);
        event.setCreatedDate(now);
        event.setTarget(null);
        
        EmailAddress address = null;
        
        switch (type) {
        case OPENED:
        	int openCount = email.getOpenCount();
        	openCount +=1;
        	email.setOpenCount(openCount);
        	emailService.update(email);

            address = getEmailAddress(email);
            if (address != null && !address.isConfirmed()) {
                address.setConfirmed(true);
                emailAddressService.update(address);
            }
            break;

        case PRINTED:
        	int printCount = email.getPrintCount();
        	printCount +=1;
        	email.setPrintCount(printCount);
        	emailService.update(email);
            break;

        case FORWARDED:
        	int forwardCount = email.getForwardCount();
        	forwardCount +=1;
        	email.setForwardCount(forwardCount);
        	emailService.update(email);
            break;

        case CLICKED:
        	int clickCount = email.getClickCount();
        	clickCount +=1;
        	email.setClickCount(clickCount);
        	emailService.update(email);
            break;

        case UNSUBSCRIBED:
        	email.setOptedOut(true);
        	emailService.update(email);
            
            address = getEmailAddress(email);
            if (address != null) {
                address.setOptedOutDate(new Date());
                address.setEnabled(false);
                emailAddressService.update(address);
            }
            break;

        default:
        	break;
        }
        
        return event;
    }


    
    private EmailAddress getEmailAddress(Email email) {
        EmailAddress address = null;
        if (email.getToAddressId() != null) {
            address = emailAddressService.fetchById(email.getToAddressId());
        } else if (email.getToAddress() != null) {
            address = emailAddressService.fetchByEmail(email.getToAddress());
        }
        return address;
    }
}
