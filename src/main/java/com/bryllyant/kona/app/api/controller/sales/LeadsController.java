/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.sales;

import com.bryllyant.kona.app.api.model.sales.SalesLeadModel;
import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.api.service.SalesLeadModelService;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.remote.service.KServiceClient;
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

    @Autowired
    SalesLeadModelService salesLeadModelService;



    private void message(HttpServletRequest req, String from, String subject, String body) {
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
    public ResponseEntity<SalesLeadModel> lead(HttpServletRequest req, @RequestBody SalesLeadModel model) {

        logApiRequest(req, "POST /sales/leads");

        SalesLead lead = new SalesLead();

        lead = salesLeadModelService.mergeEntity(lead, model);

        if (lead.getEmail() == null && lead.getMobileNumber() == null) {
            throw new BadRequestException("email and/or mobile_number must be set");
        }

        if (lead.getEmail() != null) {
            if (!KValidator.isEmail(lead.getEmail())) {
                throw new BadRequestException("Invalid email address [" + lead.getEmail() + "].");
            }
        }

        if (lead.getPhoneNumber() != null) {
            // Remove whitespace from the number
            String phoneNumber = lead.getPhoneNumber().replaceAll("\\s+", "");

            if (!system.isTestPhoneNumber(phoneNumber) && !KValidator.isE164PhoneNumber(phoneNumber)) {
                throw new BadRequestException("Invalid phone number [" + phoneNumber + "]. Phone numbers must be in E.164 format.");
            }

            lead.setPhoneNumber(phoneNumber);
        }

        if (lead.getMobileNumber() != null) {
            // Remove whitespace from the number
            String mobileNumber = lead.getMobileNumber().replaceAll("\\s+", "");

            if (!system.isTestPhoneNumber(mobileNumber) && !KValidator.isE164PhoneNumber(mobileNumber)) {
                throw new BadRequestException("Invalid mobile number [" + mobileNumber + "]. Mobile numbers must be in E.164 format.");
            }

            lead.setMobileNumber(mobileNumber);
        }

        KServiceClient client = getServiceClient(req);

        lead = salesLeadService.create(lead, client);


        return created(SalesLeadModel.from(lead));
    }
}
