package com.bryllyant.kona.api.controller.system;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.auth.TokenModel;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.app.service.RemoteServiceService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * RemoteAuthController.
 */
@RestController
@RequestMapping("/api/system/remote-auth")
public class RemoteAuthController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RemoteAuthController.class);
    
    @Autowired
    private RemoteServiceService remoteServiceService;

    @Autowired
    private UserModelService userModelService;


    @RequestMapping(value = "/{remoteServiceUidOrSlug}/token-exchange/{code}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<TokenModel> exchangeToken(
            HttpServletRequest req,
            @PathVariable String remoteServiceUidOrSlug,
            @PathVariable String code) {
        logApiRequest(req, "POST /system/remote-auth/" + remoteServiceUidOrSlug + "/token-exchange/" + code);

        RemoteService remoteService = remoteServiceService.fetchByUid(remoteServiceUidOrSlug);

        if (remoteService == null) {
            remoteService = remoteServiceService.fetchBySlug(remoteServiceUidOrSlug);
        }

        if (remoteService == null) {
            throw new NotFoundException("Remote Service not found for: " + remoteServiceUidOrSlug);
        }

        try {
            TokenModel token = remoteServiceService.exchangeToken(remoteService, code);

            return ok(token);
        } catch (Throwable t) {
            throw new NotFoundException("Access Token not found for code: " + code, t);
        }
    }
}
