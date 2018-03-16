package com.bryllyant.kona.app.api.controller.admin.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageModel;
import com.bryllyant.kona.app.api.service.LandingPageModelService;
import com.bryllyant.kona.app.api.service.LandingPageTemplateModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/admin/sales/landing-pages")
public class LandingPageController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LandingPageController.class);

    @Autowired
    private LandingPageService landingPageService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private LandingPageTemplateModelService landingPageTemplateModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/landing-pages");

        logger.debug("LandingPageController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        if (sortOrder == null) {
            sortOrder = new String[]{
                "name"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("LandingPageController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = landingPageService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, landingPageModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<LandingPageModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/landing-pages/" + uid);

        LandingPage landingPage = landingPageModelService.getLandingPage(uid);

        return ok(landingPageModelService.toModel(landingPage));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LandingPageModel> create(
            HttpServletRequest req,
            @RequestBody LandingPageModel model
    ) {
        logApiRequest(req, "POST /admin/sales/landing-pages");

        LandingPage landingPage = new LandingPage();

        landingPage = saveObject(req, landingPage, model);

        return created(landingPageModelService.toModel(landingPage));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<LandingPageModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody LandingPageModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/landing-pages/" + uid);

        LandingPage landingPage = landingPageModelService.getLandingPage(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        landingPage = saveObject(req, landingPage, model);

        return ok(landingPageModelService.toModel(landingPage));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<LandingPageModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/landing-pages/" + uid);

        LandingPage landingPage = landingPageModelService.getLandingPage(uid);

        landingPageService.remove(landingPage);

        //return ok(landingPageModelService.toModel(landingPage));
        return ok(LandingPageModel.create(landingPage.getUid()));
    }


    public LandingPage saveObject(
            HttpServletRequest req,
            LandingPage landingPage,
            LandingPageModel model
    ) {
        logger.debug("mapToObject called for landingPage: " + landingPage);

        landingPage = landingPageModelService.mergeEntity(landingPage, model);

        if (landingPage.getId() == null){
            if (model.getEnabled() == null) {
                landingPage.setEnabled(true);
            }

            LandingPageTemplate template = landingPageTemplateModelService.getTemplate(landingPage.getTemplateId());

            return landingPageService.create(template, landingPage.getName());
        }

        return landingPageService.save(landingPage);
    }

    @Override
    public Map<String,Object> toFilterCriteria(String json) {
        Map<String,Object> filter = super.toFilterCriteria(json);

        if (filter == null) {
            return new HashMap<>();
        }

        Map<String,Object> result = new HashMap<>();

        for (String key : filter.keySet()) {
            Object value = filter.get(key);

            String[] parts = util.splitKey(key);
            String prefix = parts[0];
            key = parts[1];

            switch (key) {
                case "templateUid":
                    LandingPageTemplate template = landingPageTemplateModelService.getTemplate(util.getStringValue(value));
                    result.put(prefix + "templateId", template == null ? -1L : template.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

