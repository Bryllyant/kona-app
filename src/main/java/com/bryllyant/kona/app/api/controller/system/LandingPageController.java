package com.bryllyant.kona.app.api.controller.system;

import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageParamModel;
import com.bryllyant.kona.app.api.service.LandingPageModelService;
import com.bryllyant.kona.app.api.service.LandingPageParamModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * LandingPage Controller.
 */
@RestController(value="SystemLandingPageParamController")
@RequestMapping("/api/system/landing-pages")
public class LandingPageController extends SystemController {
    private static Logger logger = LoggerFactory.getLogger(LandingPageController.class);

    @Autowired
    private LandingPageService landingPageService;

    @Autowired
    private LandingPageParamService landingPageParamService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private LandingPageParamModelService landingPageParamModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiUtil util;


//    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
//    public ResponseEntity<LandingPageModel> get(HttpServletRequest req,
//                                                @PathVariable String uid) {
//        logApiRequest(req, "GET /system/landing-pages/" + uid);
//
//        LandingPage landingPage = landingPageModelService.getLandingPage(uid);
//
//        return ok(landingPageModelService.toModel(landingPage));
//    }

    @RequestMapping(value="/{uid}/params", method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageParamModel>> getParams(HttpServletRequest req,
                                                                           @PathVariable String uid) {
        logApiRequest(req, "GET /system/landing-pages/" + uid + "/params");

        LandingPage landingPage = landingPageModelService.getLandingPage(uid);

        KResultList<LandingPageParam> params = landingPageParamService.fetchByLandingPageId(landingPage.getId());

        ModelResultSet resultSet = ModelResultSet.from(params, landingPageParamModelService.toModelList(params));

        return okList(resultSet);
    }
}

