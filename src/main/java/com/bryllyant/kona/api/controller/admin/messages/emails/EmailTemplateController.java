package com.bryllyant.kona.api.controller.admin.messages.emails;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.message.EmailTemplateModel;
import com.bryllyant.kona.api.service.EmailTemplateModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailTemplateService;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.AppUtil;
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
@RequestMapping("/api/admin/messages/emails/templates")
public class EmailTemplateController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailTemplateController.class);

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private EmailTemplateModelService emailTemplateModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailTemplateModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/templates");

        logger.debug("EmailTemplateController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        if (sortOrder == null) {
            sortOrder = new String[]{
                "createdDate"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("EmailTemplateController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = emailTemplateService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailTemplateModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<EmailTemplateModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/messages/emails/templates/" + uid);

        EmailTemplate emailTemplate = emailTemplateModelService.getEntity(uid);

        return ok(emailTemplateModelService.toModel(emailTemplate));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailTemplateModel> create(
            HttpServletRequest req,
            @RequestBody EmailTemplateModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/templates");

        EmailTemplate emailTemplate = new EmailTemplate();

        emailTemplate = saveObject(req, emailTemplate, model);



        return created(emailTemplateModelService.toModel(emailTemplate));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<EmailTemplateModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailTemplateModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/templates/" + uid);

        EmailTemplate emailTemplate = emailTemplateModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        emailTemplate = saveObject(req, emailTemplate, model);

        return ok(emailTemplateModelService.toModel(emailTemplate));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<EmailTemplateModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/messages/emails/templates/" + uid);

        EmailTemplate emailTemplate = emailTemplateModelService.getEntity(uid);

        emailTemplateService.remove(emailTemplate);

        //return ok(emailTemplateModelService.toModel(emailTemplate));
        return ok(EmailTemplateModel.create(emailTemplate.getUid()));
    }


    public EmailTemplate saveObject(
            HttpServletRequest req,
            EmailTemplate emailTemplate,
            EmailTemplateModel model
    ) {
        logger.debug("mapToObject called for emailTemplate: " + emailTemplate);

        if (model.getName() == null) {
            throw new ValidationException("name property must be set");
        }

        emailTemplate = emailTemplateModelService.mergeEntity(emailTemplate, model);

        if (emailTemplate.getId() == null) {
            User user = getUser();
            emailTemplate.setOwnerId(user.getId());
        }

        return emailTemplateService.save(emailTemplate);
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

