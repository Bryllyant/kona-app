/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.sales;

import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


/**
 * Sales Controller.
 */
@RestController
@RequestMapping("/api/sales/leads")
public class LeadsController extends SalesController {
    private static Logger logger = LoggerFactory.getLogger(LeadsController.class);

    @Autowired
    private KConfig config;

    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    UserService userService;

    @Autowired
    AppService appService;

    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private SystemService system;



    protected void message(HttpServletRequest req, String from, String subject, String body) {
        logger.debug("Support Email:\nfrom: " + from + "\nsubject: " + subject + "\nbody:\n" + body);

        String to = config.getString("system.mail.alertTo");
        String replyTo = null;
        boolean html = false;

        body = apiAuthService.getUserInfo(req, getUser()) + body;

        system.sendEmail(body, subject, from, replyTo, to, html, null);
    }




    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> lead(HttpServletRequest req, @RequestBody Map<String, Object> map) {

        logApiRequest(req, "POST /sales/leads");

        String campaignChannelUid = (String) map.get("campaign_channel_uid");
        String referredByUid = (String) map.get("referred_by_uid");
        String firstName = (String) map.get("first_name");
        String lastName = (String) map.get("last_name");
        String email = (String) map.get("email");
        String phoneNumber = (String) map.get("phone_number");
        String mobileNumber = (String) map.get("mobile_number");
        String message = (String) map.get("message");
        String interests = (String) map.get("interests"); // comma separated list 


        Long referredById = null;
        Long campaignChannelId = null;

        if (referredByUid != null) {
            User user = userService.fetchByUid(referredByUid);

            if (user != null) {
                referredById = user.getId();
            }
        }

        if (campaignChannelUid != null) {
            CampaignChannel channel = campaignChannelService.fetchByUid(campaignChannelUid);

            if (channel != null) {
                campaignChannelId = channel.getId();
            }
        }

        String hostname = KServletUtil.getClientHostname(req);
        String userAgent = KServletUtil.getClientUserAgent(req);

        if (email == null && mobileNumber == null) {
            throw new BadRequestException("email and/or mobile_number must be set");
        }

        if (phoneNumber != null) {
            // Remove whitespace from the number
            phoneNumber = phoneNumber.replaceAll("\\s+", "");

            if (!system.isTestPhoneNumber(phoneNumber) && !KValidator.isE164PhoneNumber(phoneNumber)) {
                throw new BadRequestException("Invalid phone number [" + phoneNumber + "]. Phone numbers must be in E.164 format.");
            }
        }

        if (mobileNumber != null) {
            // Remove whitespace from the number
            mobileNumber = mobileNumber.replaceAll("\\s+", "");

            if (!system.isTestPhoneNumber(mobileNumber) && !KValidator.isE164PhoneNumber(mobileNumber)) {
                throw new BadRequestException("Invalid mobile number [" + mobileNumber + "]. Mobile numbers must be in E.164 format.");
            }
        }

        App refApp = apiAuthService.getApp();

        SalesLead lead = new SalesLead();
        lead.setFirstName(firstName);
        lead.setLastName(lastName);
        lead.setEmail(email);
        lead.setPhoneNumber(phoneNumber);
        lead.setMobileNumber(mobileNumber);
        lead.setMessage(message);
        lead.setReferredById(referredById);
        lead.setCampaignChannelId(campaignChannelId);
        lead.setInterests(interests);
        lead.setHostname(hostname);
        lead.setUserAgent(userAgent);
        lead.setCreatedDate(new Date());
        lead = salesLeadService.add(lead);


        return created(getResultObject("lead_uid", lead.getUid()));
    }


}
