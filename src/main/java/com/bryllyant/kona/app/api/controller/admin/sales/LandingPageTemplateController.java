package com.bryllyant.kona.app.api.controller.admin.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageTemplateModel;
import com.bryllyant.kona.app.api.service.LandingPageTemplateModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
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
@RequestMapping("/api/admin/sales/landing-page-templates")
public class LandingPageTemplateController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateController.class);

    @Autowired
    private LandingPageTemplateService templateService;

    @Autowired
    private LandingPageTemplateModelService templateModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageTemplateModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/landing-page-templates");

        logger.debug("LandingPageTemplateController: raw query: " + query);

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

        logger.debug("LandingPageTemplateController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = templateService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, templateModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<LandingPageTemplateModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/landing-page-templates/" + uid);

        LandingPageTemplate template = templateModelService.getTemplate(uid);

        return ok(templateModelService.toModel(template));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LandingPageTemplateModel> create(
            HttpServletRequest req,
            @RequestBody LandingPageTemplateModel model
    ) {
        logApiRequest(req, "POST /admin/sales/landing-page-templates");

        LandingPageTemplate template = new LandingPageTemplate();

        template = saveObject(req, template, model);

        return created(templateModelService.toModel(template));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<LandingPageTemplateModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody LandingPageTemplateModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/landing-page-templates/" + uid);

        LandingPageTemplate template = templateModelService.getTemplate(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        template = saveObject(req, template, model);

        return ok(templateModelService.toModel(template));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<LandingPageTemplateModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/landing-page-templates/" + uid);

        LandingPageTemplate template = templateModelService.getTemplate(uid);

        templateService.remove(template);

        return ok(templateModelService.toModel(template));
    }


    public LandingPageTemplate saveObject(
            HttpServletRequest req,
            LandingPageTemplate template,
            LandingPageTemplateModel model
    ) {
        logger.debug("mapToObject called for template: " + template);

        template = templateModelService.mergeEntity(template, model);

        if (template.getId() == null && model.getOwner() == null) {
            template.setOwnerId(getUser().getId());
        }

        return templateService.save(template);
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
                case "ownerUid":
                    User owner = userModelService.getUser(util.getStringValue(value));
                    result.put(prefix + "ownerId", owner == null ? -1L : owner.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

