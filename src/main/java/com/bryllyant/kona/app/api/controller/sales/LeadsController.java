/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.sales;

import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.util.KInflector;
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
@RequestMapping("/api/app/sales/leads")
public class LeadsController extends SalesController {
    private static Logger logger = LoggerFactory.getLogger(LeadsController.class);

    @Autowired
    private KConfig config;

    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private CampaignService campaignService;

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

        logApiRequest(req, "POST /app/sales/leads");

        String appSlug = (String) map.get("app_slug");
        String channel = (String) map.get("channel");
        String campaignSlug = (String) map.get("campaign_slug");
        String referredByUserUid = (String) map.get("referred_by_user_uid");
        String firstName = (String) map.get("first_name");
        String lastName = (String) map.get("last_name");
        String email = (String) map.get("email");
        String mobileNumber = (String) map.get("mobile_number");
        String comment = (String) map.get("comment");
        String interests = (String) map.get("interests"); // comma separated list 


        Long appId = null;
        Long referredByUserId = null;
        Long campaignId = null;

        if (appSlug != null) {
            appSlug = KInflector.getInstance().slug(appSlug);

            App app = appService.fetchBySlug(appSlug);

            if (app != null) {
                appId = app.getId();
            }
        }

        if (referredByUserUid != null) {
            User user = userService.fetchByUid(referredByUserUid);

            if (user != null) {
                referredByUserId = user.getId();
            }
        }

        if (campaignSlug != null) {
            campaignSlug = KInflector.getInstance().slug(campaignSlug);

            Campaign campaign = campaignService.fetchBySlug(campaignSlug);

            if (campaign != null) {
                campaignId = campaign.getId();
            }
        }


        if (channel == null) {
            channel = "website";
        }

        String hostname = KServletUtil.getClientHostname(req);
        String userAgent = KServletUtil.getClientUserAgent(req);

        if (email == null && mobileNumber == null) {
            throw new BadRequestException("email and/or mobile_number must be set");
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
        lead.setMobileNumber(mobileNumber);
        lead.setComment(comment);
        lead.setAppId(appId);
        lead.setRefAppId(refApp.getId());
        lead.setReferredByUserId(referredByUserId);
        lead.setCampaignId(campaignId);
        lead.setInterests(interests);
        lead.setHostname(hostname);
        lead.setUserAgent(userAgent);
        lead.setChannel(channel);
        lead.setCreatedDate(new Date());
        lead = salesLeadService.add(lead);


        return created(getResultObject("lead_uid", lead.getUid()));
    }


}
