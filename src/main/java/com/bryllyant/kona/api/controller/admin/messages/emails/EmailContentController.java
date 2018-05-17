package com.bryllyant.kona.api.controller.admin.messages.emails;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.service.EmailContentModelService;
import com.bryllyant.kona.api.service.EmailTemplateModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailContentService;
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
@RequestMapping("/api/admin/messages/emails/content")
public class EmailContentController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailContentController.class);

    @Autowired
    private EmailContentService emailContentService;

    @Autowired
    private EmailContentModelService emailContentModelService;

    @Autowired
    private EmailTemplateModelService emailTemplateModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailContentModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/content");

        logger.debug("EmailContentController: raw query: " + query);

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

        logger.debug("EmailContentController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = emailContentService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailContentModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<EmailContentModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/messages/emails/content/" + uid);

        EmailContent emailContent = emailContentModelService.getEntity(uid);

        return ok(emailContentModelService.toModel(emailContent));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailContentModel> create(
            HttpServletRequest req,
            @RequestBody EmailContentModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/content");

        EmailContent emailContent = new EmailContent();

        emailContent = saveObject(req, emailContent, model);



        return created(emailContentModelService.toModel(emailContent));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<EmailContentModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailContentModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/content/" + uid);

        EmailContent emailContent = emailContentModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        emailContent = saveObject(req, emailContent, model);

        return ok(emailContentModelService.toModel(emailContent));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<EmailContentModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/messages/emails/content/" + uid);

        EmailContent emailContent = emailContentModelService.getEntity(uid);

        emailContentService.remove(emailContent);

        //return ok(emailContentModelService.toModel(emailContent));
        return ok(EmailContentModel.create(emailContent.getUid()));
    }


    public EmailContent saveObject(
            HttpServletRequest req,
            EmailContent emailContent,
            EmailContentModel model
    ) {
        logger.debug("mapToObject called for emailContent: " + emailContent);

        if (model.getName() == null) {
            throw new ValidationException("name property must be set");
        }

        emailContent = emailContentModelService.mergeEntity(emailContent, model);

        if (emailContent.getId() == null) {
            User user = getUser();
            emailContent.setOwnerId(user.getId());
        }

        return emailContentService.save(emailContent);
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

                case "templateUid":
                    EmailTemplate template = emailTemplateModelService.getEntity(util.getStringValue(value));
                    result.put(prefix + "templateId", template == null ? -1L : template.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

