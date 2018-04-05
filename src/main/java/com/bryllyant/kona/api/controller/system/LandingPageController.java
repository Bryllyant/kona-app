package com.bryllyant.kona.api.controller.system;

import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageParamModel;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.service.LandingPageModelService;
import com.bryllyant.kona.api.service.LandingPageParamModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.util.AppUtil;
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
@RestController(value="SystemLandingPageController")
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
    private AppUtil util;


//    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
//    public ResponseEntity<LandingPageModel> get(HttpServletRequest req,
//                                                @PathVariable String uid) {
//        logApiRequest(req, "GET /system/landing-pages/" + uid);
//
//        LandingPage landingpage = landingPageModelService.getLandingPage(uid);
//
//        return ok(landingPageModelService.toModel(landingpage));
//    }

    @RequestMapping(value="/{uid}/params", method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageParamModel>> getParams(HttpServletRequest req,
                                                                           @PathVariable String uid) {
        logApiRequest(req, "GET /system/landing-pages/" + uid + "/params");

        LandingPage landingPage = landingPageModelService.getLandingPage(uid);

        KResultList<LandingPageParam> params = (KResultList<LandingPageParam>)landingPageParamService.fetchByLandingPageId(landingPage.getId());

        ModelResultSet resultSet = ModelResultSet.from(params, landingPageParamModelService.toModelList(params));

        return okList(resultSet);
    }
}

