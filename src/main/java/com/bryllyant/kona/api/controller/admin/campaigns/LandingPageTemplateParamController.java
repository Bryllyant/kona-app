package com.bryllyant.kona.api.controller.admin.campaigns;

import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageTemplateParamModel;
import com.bryllyant.kona.api.service.LandingPageTemplateModelService;
import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageTemplateParamModel;
import com.bryllyant.kona.api.service.FileModelService;
import com.bryllyant.kona.api.service.LandingPageTemplateModelService;
import com.bryllyant.kona.api.service.LandingPageTemplateParamModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.util.AppUtil;
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
@RequestMapping("/api/admin/campaigns/landing-page-template-params")
public class LandingPageTemplateParamController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateParamController.class);

    @Autowired
    private LandingPageTemplateService templateService;

    @Autowired
    private LandingPageTemplateParamService paramService;

    @Autowired
    private LandingPageTemplateModelService templateModelService;

    @Autowired
    private LandingPageTemplateParamModelService paramModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private FileModelService fileModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private FileService fileService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageTemplateParamModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/campaigns/landing-page-template-params");

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

        KResultList result = paramService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, paramModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<LandingPageTemplateParamModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/campaigns/landing-page-template-params/" + uid);

        LandingPageTemplateParam param = paramModelService.getParam(uid);

        return ok(paramModelService.toModel(param));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LandingPageTemplateParamModel> create(
            HttpServletRequest req,
            @RequestBody LandingPageTemplateParamModel model
    ) {
        logApiRequest(req, "POST /admin/campaigns/landing-page-template-params");

        LandingPageTemplateParam param = new LandingPageTemplateParam();

        param = saveObject(req, param, model);

        return created(paramModelService.toModel(param));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<LandingPageTemplateParamModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody LandingPageTemplateParamModel model
    ) {
        logApiRequest(req, "PUT /admin/campaigns/landing-page-template-params/" + uid);

        LandingPageTemplateParam param = paramModelService.getParam(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        param = saveObject(req, param, model);

        return ok(paramModelService.toModel(param));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<LandingPageTemplateParamModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/campaigns/landing-page-template-params/" + uid);

        LandingPageTemplateParam param = paramModelService.getParam(uid);

        paramService.remove(param);

        //return ok(paramModelService.toModel(param));
        return ok(LandingPageTemplateParamModel.create(param.getUid()));
    }


    public LandingPageTemplateParam saveObject(
            HttpServletRequest req,
            LandingPageTemplateParam param,
            LandingPageTemplateParamModel model
    ) {
        logger.debug("mapToObject called for param: " + param);

        param = paramModelService.mergeEntity(param, model);

        return paramService.save(param);
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
                    LandingPageTemplate template = templateModelService.getTemplate(util.getStringValue(value));
                    result.put(prefix + "templateId", template == null ? -1L : template.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

