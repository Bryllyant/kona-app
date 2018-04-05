/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.controller.sales;

import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CommerceService;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KNameParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Sales Controller.
 */
@RestController
@RequestMapping("/api/app/sales/charges")
public class ChargesController extends SalesController {
    private static Logger logger = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private KConfig config;

    @Autowired
    UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CommerceService commerceService;

    @Autowired
    private PaymentAccountService paymentAccountService;

    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private SystemService system;


    @RequestMapping(method=RequestMethod.GET)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<List<Map<String,Object>>> fetchCharges(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /app/sales/charges");

        logger.debug("ChargesController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        if (filter == null) {
            filter = new HashMap<String,Object>();
        }

        logger.debug("filter: " + KJsonUtil.toJson(filter));


        //String[] sortOrder = { "displayOrder" };
        String[] sortOrder = null;

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 99999;
        }

        logger.debug("ChargesController: filter: " + KJsonUtil.toJson(filter));

        return ok(toPaymentMapList(
                paymentService.fetchByCriteria(offset, limit, sortOrder, filter, distinct)));
    }



    private String getValue(Map<String,Object> map, String key) {
        return getValue(map, key, true);
    }

    private String getValue(Map<String,Object> map, String key, boolean required) {
        String value = (String) map.get(key);

        if (value == null && required) {
            throw new BadRequestException(key + " is required");
        }

        return value;
    }



    @RequestMapping(method=RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    @ResponseBody
    public ResponseEntity<Map<String,Object>>  createCharge(
            HttpServletRequest req,
            @RequestBody Map<String,Object> map) {
        logApiRequest(req, "POST /sales/charges");
        
        
        /*
        String street1 = getValue(map, "street1");
        String street2 = getValue(map, "street2", false);
        String city = getValue(map, "city");
        String state = getValue(map, "state");
        String country = getValue(map, "country");
        String postalCode = getValue(map, "postal_code");
        String productUid = getValue(map, "product_uid");
        */

        String email = getValue(map, "email");
        String name = getValue(map, "name");
        String cardToken = getValue(map, "card_token");
        String productName = config.getString("sales.products.report");

        String firstName = null;
        String lastName = null;

        if (name != null) {
            KNameParser parser = KNameParser.parse(name, true);
            firstName = parser.getFirstName();
            lastName = parser.getLastName();
        }

        KServiceClient client = getServiceClient(req);

        // TODO: get the user for this product
        User user = apiAuthService.getUser(req);

        // see if a user already exists with the email address
        User parent = userService.fetchByEmail(email);

        if (parent == null)  {
            user.setEmail(email);
        }  else {
            user.setParentId(parent.getId());
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        /*
        user.setStreet1(street1);
        user.setStreet2(street2);
        user.setCity(city);
        user.setState(state);
        user.setCountry(country);
        user.setPostalCode(postalCode);
        */

        user = userService.save(user);

        if (parent != null) {
            user.setEmail(parent.getEmail());
        }

        PaymentAccount paymentAccount = paymentAccountService.fetchDefault(user.getAccountId());

        Payment payment = commerceService.charge(client, paymentAccount, cardToken, productName);

        // if payment is successful, email copy of report to user's email address
        Media media = new Media();

        sendMedia(user, media);

        Map<String,Object> charge = toMap(payment);

        /*
        charge.put("user", userModelService.toMap(user));
        charge.put("media", toMap(media));
        */

        return created(charge);
    }



    private void sendMedia(User user, Media media) {
        if (user.getEmail() == null) {
            logger.warn("ChargeController: sendReport: User email is null");
            return;
        }

        String template = "email.templates.app.patentReport";

        String from = config.getString("system.mail.from");
        String to = user.getEmail();
        String replyTo = from;

        String subject = "Patentable Subject Matter Report";
        subject = "[" + system.getSystemApp().getName() + "] " + subject;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user", user);

        List<File> fileList = new ArrayList<>();

        try {
            File file = fileService.fetchById(media.getFileId(), true);
            fileList.add(file);
        } catch (IOException e) {
            system.alert("sendReport: File Fetch Error", e);
        }

        system.sendEmail(template, params, subject, from, replyTo, to, fileList, new Callback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {

                system.alert("sendReport: sendMail() Error", t);
            }
        });



    }
}
