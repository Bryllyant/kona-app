package com.bryllyant.kona.api.controller.admin.messages.emails;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.api.service.EmailGroupModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailGroupService;
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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/admin/messages/emails/groups")
public class EmailGroupController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailGroupController.class);

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailGroupModelService emailGroupModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailGroupModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/groups");

        logger.debug("EmailGroupController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

//        if (sortOrder == null) {
//            sortOrder = new String[]{
//                "createdDate"
//            };
//        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("EmailGroupController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = emailGroupService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailGroupModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<EmailGroupModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/messages/emails/groups/" + uid);

        EmailGroup emailGroup = emailGroupModelService.getEntity(uid);

        return ok(emailGroupModelService.toModel(emailGroup));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailGroupModel> create(
            HttpServletRequest req,
            @RequestParam(value="options", required=false) String opts,
            @RequestBody EmailGroupModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/groups");


        Map<String,Object> options = super.toFilterCriteria(opts); // returns keys in camelCase

        logger.debug("create called: options: " + KJsonUtil.toJson(options));

        Long maxCount = util.getLongValue(options.get("maxCount"));
        List<String> sourceList = (List<String>)(options.get("sources"));
        List<String> excludeGroupList = (List<String>)(options.get("excludeGroups"));
        Boolean forceScrub = util.getBooleanValue(options.get("forceScrub"), true);

        logger.debug("create called: maxCount: {}", maxCount);
        logger.debug("create called: sourceList: {}", KJsonUtil.toJson(sourceList));
        logger.debug("create called: excludeGroupList: {}", KJsonUtil.toJson(excludeGroupList));
        logger.debug("create called: forceScrub: {}", forceScrub);

        EmailGroup emailGroup = emailGroupService.create(model.getName(), maxCount, sourceList, excludeGroupList, forceScrub);

        return created(emailGroupModelService.toModel(emailGroup));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<EmailGroupModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailGroupModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/groups/" + uid);

        EmailGroup emailGroup = emailGroupModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        emailGroup = saveObject(req, emailGroup, model);

        return ok(emailGroupModelService.toModel(emailGroup));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<EmailGroupModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/messages/emails/groups/" + uid);

        EmailGroup emailGroup = emailGroupModelService.getEntity(uid);

        emailGroupService.remove(emailGroup);

        //return ok(emailGroupModelService.toModel(emailGroup));
        return ok(EmailGroupModel.create(emailGroup.getUid()));
    }


    public EmailGroup saveObject(
            HttpServletRequest req,
            EmailGroup emailGroup,
            EmailGroupModel model
    ) {
        logger.debug("mapToObject called for emailGroup: " + emailGroup);

        if (model.getName() == null) {
            throw new ValidationException("name property must be set");
        }

        emailGroup = emailGroupModelService.mergeEntity(emailGroup, model);

        return emailGroupService.save(emailGroup);
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
                case "userUid":
                    User user = userModelService.getUser(util.getStringValue(value));
                    result.put(prefix + "userId", user == null ? -1L : user.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

