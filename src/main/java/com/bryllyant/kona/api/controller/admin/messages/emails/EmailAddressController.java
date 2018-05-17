package com.bryllyant.kona.api.controller.admin.messages.emails;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.service.EmailAddressModelService;
import com.bryllyant.kona.api.service.EmailGroupModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailException;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/admin/messages/emails/addresses")
public class EmailAddressController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailAddressController.class);

    @Autowired
    private EmailAddressService emailAddressService;

    @Autowired
    private EmailGroupAddressService emailGroupAddressService;

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailAddressModelService emailAddressModelService;

    @Autowired
    private EmailGroupModelService emailGroupModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailAddressModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/addresses");

        logger.debug("EmailAddressController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

//        if (sortOrder == null) {
//            sortOrder = new String[]{
//                "source",
//                "createdDate DESC"
//            };
//        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("EmailAddressController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = emailAddressService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailAddressModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/groups/{uid}", method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailAddressModel>> fetchGroupAddresses(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/addresses/groups/" + uid);

        EmailGroup group = emailGroupModelService.getEntity(uid);

        logger.debug("EmailAddressController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("EmailAddressController: filter: " + KJsonUtil.toJson(filter));

        KResultList result =
                (KResultList<EmailAddress>) emailGroupAddressService
                .fetchByGroupId(group.getId(), offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailAddressModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/sources", method=RequestMethod.GET)
    public ResponseEntity<List<Map<String,Object>>> getSources(
            HttpServletRequest req,
            @RequestParam(value="nocache", required=false) Boolean nocache
    ) {
        logApiRequest(req, "GET /admin/messages/emails/addresses/sources");

        if (nocache == null) {
            nocache = false;
        }

        List<Map<String,Object>> sources = emailAddressService.fetchSources(nocache);

        return new ResponseEntity<>(sources, HttpStatus.OK);
    }

    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<EmailAddressModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/messages/emails/addresses/" + uid);

        EmailAddress emailAddress = emailAddressModelService.getEntity(uid);

        return ok(emailAddressModelService.toModel(emailAddress));
    }


    @RequestMapping(value="/groups/{uid}", method = RequestMethod.POST)
    public ResponseEntity<EmailAddressModel> addGroupAddress(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestParam(value="force_scrub", required=false) Boolean forceScrub,
            @RequestBody EmailAddressModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/addresses/groups/" + uid);

        EmailGroup group = emailGroupModelService.getEntity(uid);

        EmailAddress emailAddress = new EmailAddress();

        emailAddress = saveObject(req, emailAddress, model);

        if (forceScrub == null) {
            forceScrub = true;
        }

        try {
            emailGroupService.addGroupAddress(group, emailAddress, forceScrub);
        } catch (EmailException e) {
            throw new BadRequestException("Invalid email address: " + emailAddress);
        }

        return created(emailAddressModelService.toModel(emailAddress));
    }

    @RequestMapping(value="/groups/{groupUid}/{addressUid}", method = RequestMethod.DELETE)
    public ResponseEntity<EmailAddressModel> removeGroupAddress(
            HttpServletRequest req,
            @PathVariable String groupUid,
            @PathVariable String addressUid
    ) {
        logApiRequest(req, "POST /admin/messages/emails/addresses/groups/" + groupUid + "/" + addressUid);

        EmailGroup group = emailGroupModelService.getEntity(groupUid);

        EmailAddress address = emailAddressModelService.getEntity(addressUid);

            emailGroupService.removeGroupAddress(group, address);

        return ok(EmailAddressModel.create(address.getUid()));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailAddressModel> create(
            HttpServletRequest req,
            @RequestBody EmailAddressModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/addresses");

        EmailAddress emailAddress = new EmailAddress();

        emailAddress = saveObject(req, emailAddress, model);

        return created(emailAddressModelService.toModel(emailAddress));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<EmailAddressModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailAddressModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/addresses/" + uid);

        EmailAddress emailAddress = emailAddressModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        emailAddress = saveObject(req, emailAddress, model);

        return ok(emailAddressModelService.toModel(emailAddress));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<EmailAddressModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/messages/emails/addresses/" + uid);

        EmailAddress emailAddress = emailAddressModelService.getEntity(uid);

        emailAddressService.remove(emailAddress);

        //return ok(emailAddressModelService.toModel(emailAddress));
        return ok(EmailAddressModel.create(emailAddress.getUid()));
    }


    public EmailAddress saveObject(
            HttpServletRequest req,
            EmailAddress emailAddress,
            EmailAddressModel model
    ) {
        logger.debug("saveObject called for emailAddress: " + emailAddress);

        if (model.getEmail() == null) {
            throw new ValidationException("email property must be set");
        }

        emailAddress = emailAddressModelService.mergeEntity(emailAddress, model);

        if (emailAddress.getId() == null && model.getEnabled() == null) {
            emailAddress.setEnabled(true);

        }

        if (emailAddress.getId() == null) {
            emailAddress = emailAddressService.create(emailAddress);
        } else {
            emailAddress = emailAddressService.save(emailAddress);
        }

        return emailAddress;
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

