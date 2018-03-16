package com.bryllyant.kona.app.api.controller.admin.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.media.FileModel;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageParamModel;
import com.bryllyant.kona.app.api.service.FileModelService;
import com.bryllyant.kona.app.api.service.LandingPageModelService;
import com.bryllyant.kona.app.api.service.LandingPageParamModelService;
import com.bryllyant.kona.app.api.service.LandingPageTemplateModelService;
import com.bryllyant.kona.app.api.service.LandingPageTemplateParamModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageParamService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/admin/sales/landing-page-params")
public class LandingPageParamController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LandingPageParamController.class);

    @Autowired
    private LandingPageParamService paramService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private LandingPageTemplateModelService templateModelService;

    @Autowired
    private LandingPageTemplateParamModelService templateParamModelService;

    @Autowired
    private LandingPageParamModelService paramModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private FileModelService fileModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private FileService fileService;

    @Autowired
    private ApiUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<LandingPageParamModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/landing-page-params");

        logger.debug("LandingPageTemplateController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

//        if (sortOrder == null) {
//            sortOrder = new String[]{
//                    "name"
//            };
//        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("LandingPageTemplateController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = paramService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet<LandingPageParamModel> resultSet = ModelResultSet.from(result, paramModelService.toModelList(result));

        resultSet.getData().sort((LandingPageParamModel a, LandingPageParamModel b) -> {
            return a.getTemplateParam().getName().compareTo(b.getTemplateParam().getName());
        });

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<LandingPageParamModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/landing-page-params/" + uid);

        LandingPageParam param = paramModelService.getLandingPageParam(uid);

        return ok(paramModelService.toModel(param));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LandingPageParamModel> create(
            HttpServletRequest req,
            @RequestBody LandingPageParamModel model
    ) {
        logApiRequest(req, "POST /admin/sales/landing-page-params");

        LandingPageParam param = new LandingPageParam();

        param = saveObject(req, param, model);

        return created(paramModelService.toModel(param));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<LandingPageParamModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody LandingPageParamModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/landing-page-params/" + uid);

        LandingPageParam param = paramModelService.getLandingPageParam(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        param = saveObject(req, param, model);

        return ok(paramModelService.toModel(param));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<LandingPageParamModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/landing-page-params/" + uid);

        LandingPageParam param = paramModelService.getLandingPageParam(uid);

        paramService.remove(param);

        //return ok(paramModelService.toModel(param));
        return ok(LandingPageParamModel.create(param.getUid(),  null));
    }


    // NOTE: A landing page param can be a direct value or a file upload.  If it's a file upload,
    // the value contains the absolute URL of the file.  If a call is made to update this object
    // and the value didn't change, then due to the way the save() method works, the URL value
    // will be saved and fileId will be set to null.  That is why we check the templateParam type
    // set value to null if it equals MEDIA.

    public LandingPageParam saveObject(
            HttpServletRequest req,
            LandingPageParam param,
            LandingPageParamModel model
    ) {
        logger.debug("mapToObject called for param: " + param);

        param = paramModelService.mergeEntity(param, model);

        LandingPage landingPage = landingPageModelService.getLandingPage(param.getLandingPageId());

        String value = param.getValue();

        LandingPageTemplateParam templateParam = templateParamModelService.getParam(param.getTemplateParamId());

        if (templateParam.getType() == LandingPageTemplateParam.Type.MEDIA) {
            value = null;
        }

        return paramService.save(landingPage, templateParam, value);
    }

    @RequestMapping(value = "/{uid}/media", method = RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity<FileModel> updateFile(
            MultipartHttpServletRequest req,
            @PathVariable String uid,
            @RequestParam(value="upload_date", required=false) Long uploadDate) {

        logApiRequest(req, "POST /admin/sales/landing-page-params/" + uid + "/media");

        LandingPageParam param = paramModelService.getLandingPageParam(uid);

        Long uploadTime = null;

        if (uploadDate != null) {
            uploadTime = new Date().getTime() - uploadDate;
        }

        File file = getUploadedFile(req, getUser(), uploadTime);

        param = paramService.updateFile(param, file);

        file = fileService.fetchById(param.getFileId());

        return created(fileModelService.toModel(file));
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
                case "landingPageUid":
                    LandingPage landingPage = landingPageModelService.getLandingPage(util.getStringValue(value));
                    result.put(prefix + "landingPageId", landingPage == null ? -1L : landingPage.getId());
                    break;

                case "templateParamUid":
                    LandingPageTemplateParam templateParam = templateParamModelService.getParam(util.getStringValue(value));
                    result.put(prefix + "templateParamId", templateParam == null ? -1L : templateParam.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

