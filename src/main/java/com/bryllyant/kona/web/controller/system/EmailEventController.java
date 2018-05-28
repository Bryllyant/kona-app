/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.web.controller.system;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailCampaignService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.templates.KTemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Email Event Controller.
 */
@Controller
@RequestMapping("/system/email/events")
public class EmailEventController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailEventController.class);

    @Autowired
    private Environment env;

    @Autowired
    private EmailService emailService; 

    @Autowired
    private EmailAddressService emailAddressService;

    @Autowired
    private EmailCampaignService emailCampaignService;

    @Autowired
    private UserService userService;

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
        EmailEvent event = createEvent(req, messageId, EmailEvent.Type.OPENED);
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
        EmailEvent event = createEvent(req, messageId, EmailEvent.Type.FORWARDED);
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
        EmailEvent event = createEvent(req, messageId, EmailEvent.Type.PRINTED);
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
    @ResponseStatus(HttpStatus.OK)
    public void click(HttpServletRequest req,
            HttpServletResponse resp,
            @PathVariable final String messageId,
    		@RequestParam(value="u", required=true) String redirectUrl) {
        
        EmailEvent event = createEvent(req, messageId, EmailEvent.Type.CLICKED);
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
        
        EmailEvent event = createEvent(req, messageId, EmailEvent.Type.UNSUBSCRIBED);
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
    

    
    private EmailEvent createEvent(HttpServletRequest req, String uid, EmailEvent.Type type) {
        Date now = new Date();

        Email email = emailService.fetchByUid(uid);

        if (email == null) {
            logger.error("Invalid email uid: " + uid);
            return null;
        }

        if (!email.isDelivered()) {
            email.setDelivered(true);
        }

        Long userId = null;

        User user = userService.fetchByEmail(email.getToAddress());

        if (user != null) {
            userId = user.getId();
        }


        EmailEvent event = new EmailEvent();
        event.setEmailId(email.getId());
        event.setUserId(userId);
        event.setEmailCampaignId(email.getEmailCampaignId());
        event.setType(type);
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

            boolean updateAddress = false;

            if (address != null) {
                if (address.getConfirmedDate() == null) {
                    address.setConfirmedDate(new Date());
                    updateAddress = true;
                }

                if (address.getUserId() == null && userId != null) {
                    address.setUserId(userId);
                    updateAddress = true;
                }

                if (updateAddress) {
                    emailAddressService.update(address);
                }
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

        if (email.getEmailAddressId() != null) {
            address = emailAddressService.fetchById(email.getEmailAddressId());
        } else if (email.getToAddress() != null) {
            address = emailAddressService.fetchByEmail(email.getToAddress());
        }

        return address;
    }
}
