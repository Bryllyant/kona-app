/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.exception;

import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.rest.DefaultRestErrorResolver;
import com.bryllyant.kona.rest.RestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RestErrorResolver extends DefaultRestErrorResolver {
    private static Logger logger = LoggerFactory.getLogger(RestErrorResolver.class);

    @Autowired
    private KConfig config;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiAuthService apiAuthService;




    @PostConstruct
    public void init() {
        String defaultMoreInfoUrl = config.getString("api.exception.defaultMoreInfoUrl", "");
        setDefaultMoreInfoUrl(defaultMoreInfoUrl);

        Map<String, String> map = new HashMap<>();

        map.put("com.bryllyant.kona.rest.exception.BadRequestException",
                "status=400, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.AuthenticationException",
                "status=401, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.ForbiddenException",
                "status=403, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.NotFoundException",
                "status=404, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.ConflictException",
                "status=409, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.ValidationException",
                "status=412, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("com.bryllyant.kona.rest.exception.SystemException",
                "status=500, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");

        map.put("java.lang.Throwable",
                "status=500, code=0, clientMessage=_clientMessage, developerMessage=_developerMessage, moreInfoUrl=_moreInfoUrl");


        setExceptionMappingDefinitions(map);

        //add in user-specified mappings (will override defaults as necessary):
        //logger.debug("RestErrorResolver: exceptionMappingDefinitions: "
        //        + KJsonUtil.toJson(getExceptionMappingDefinitions(), 1000)
        //);

    }




    @Override
    public RestError resolveError(ServletWebRequest request, Object handler, Exception ex) {
        RestError error = super.resolveError(request, handler, ex);

        Integer errorCode = error.getStatus().value();

        if (errorCode == 409 || errorCode >= 500) {
            HttpServletRequest req = request.getRequest();

            String message = apiAuthService.getUserInfo(req);

            if (ex != null && ex.getMessage() != null) {
                message += ex.getMessage();
            }

            logger.error(ex.getMessage(), ex);

            String appName = system.getSystemApp().getName();

            String subject = "[" + appName + "] API Error: " + error.getStatus().name();

            system.alert(subject, message, ex);
        }

        return error;
    }

    @Override
    public String getDefaultClientMessage() {
        /*
        return  "<p>An error occurred while processing your request.</p> "
                + "<p>We're working to resolve the problem.</p>"
                + "<p>We apologize for the inconvenience.</p>";
    	 */
        return null;
    }
}
