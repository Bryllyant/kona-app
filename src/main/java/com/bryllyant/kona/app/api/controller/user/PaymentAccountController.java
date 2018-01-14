/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.user;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Payment Controller.
 */
@RestController
@RequestMapping("/api/payment-accounts")
public class PaymentAccountController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(PaymentAccountController.class);

	// ----------------------------------------------------------------------
	
	@Autowired
    private StripeService stripeService;
	
	// ----------------------------------------------------------------------

    @RequestMapping(value="/stripe/token", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addStripeToken(HttpServletRequest req,
            @RequestBody Map<String,Object> map) {
        logApiRequest(req, "POST /payment-accounts/stripe/token");
        
        @SuppressWarnings("unchecked")
        Map<String,Object> stripeToken = (Map<String,Object>) map.get("stripe_token");
        
        if (stripeToken == null) {
            throw new BadRequestException("stripe_token is null");
        }
        
        /*
         * {
         *    "stripe_token":{
         *        "id":"tok_19xLJkHyiJrhVaLsjaEGJ9E7",
         *        "object":
         *        "token",
         *        "card":{
         *            "id": "card_19xLJjHyiJrhVaLsgWih8jcs",
         *            "object":"card",
         *            "address_city":null,
         *            "address_country":null,
         *            "address_line1":null,
         *            "address_line1_check":null,
         *            "address_line2":null,
         *            "address_state":null,
         *            "address_zip":"80303",
         *            "address_zip_check": "unchecked",
         *            "brand":"Visa",
         *            "country":"US",
         *            "cvc_check":"unchecked",
         *            "dynamic_last4":null,
         *            "exp_month":1,
         *            "exp_year":2020,
         *            "funding":"credit",
         *            "last4":"4242",
         *            "metadata":{},
         *            "name":"Test User",
         *            "tokenization_method":null
         *        },
         *        "client_ip":"68.81.59.120",
         *        "created":1489519704,
         *        "livemode":false,
         *        "type":"card",
         *        "used":false
         *    }
         * }
         */
        
        //@SuppressWarnings("unchecked")
        //Map<String,Object> card = (Map<String,Object>) map.get("stripe_token");
        
        
        String cardToken = (String) stripeToken.get("id");
        //String cardLast4 = (String) card.get("last4");
        
        Long appId = getAppId(req);

        User user = getUser();
        
        // FIXME: This creates a PaymentAccount object behind the scenes.  Stripe needs to be
        // completely hidden underneath PaymentAccount with all Stripe-specific services accessed via
        // a generic interface.
        stripeService.updateStripeUid(appId, user.getId(), cardToken);
        
        return created(getResultObject("token", cardToken));
    }
	// ----------------------------------------------------------------------
}
