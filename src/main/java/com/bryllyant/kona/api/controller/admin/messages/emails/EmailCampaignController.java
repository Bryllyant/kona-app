package com.bryllyant.kona.api.controller.admin.messages.emails;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.message.EmailCampaignModel;
import com.bryllyant.kona.api.model.message.EmailModel;
import com.bryllyant.kona.api.service.CampaignChannelModelService;
import com.bryllyant.kona.api.service.CampaignGroupModelService;
import com.bryllyant.kona.api.service.CampaignModelService;
import com.bryllyant.kona.api.service.EmailCampaignModelService;
import com.bryllyant.kona.api.service.EmailContentModelService;
import com.bryllyant.kona.api.service.EmailGroupModelService;
import com.bryllyant.kona.api.service.EmailModelService;
import com.bryllyant.kona.api.service.EmailTemplateModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailCampaignService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.SystemException;
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
@RequestMapping("/api/admin/messages/emails/campaigns")
public class EmailCampaignController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(EmailCampaignController.class);

    @Autowired
    private EmailCampaignService emailCampaignService;

    @Autowired
    private EmailModelService emailModelService;

    @Autowired
    private EmailCampaignModelService emailCampaignModelService;

    @Autowired
    private EmailTemplateModelService emailTemplateModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private EmailGroupModelService emailGroupModelService;

    @Autowired
    private EmailContentModelService emailContentModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<EmailCampaignModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/messages/emails/campaigns");

        logger.debug("EmailCampaignController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        if (sortOrder == null) {
            sortOrder = new String[]{
                "createdDate DESC"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("EmailCampaignController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = emailCampaignService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, emailCampaignModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<EmailCampaignModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/messages/emails/campaigns/" + uid);

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        return ok(emailCampaignModelService.toModel(emailCampaign));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailCampaignModel> create(
            HttpServletRequest req,
            @RequestBody EmailCampaignModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/campaigns");

        EmailCampaign emailCampaign = new EmailCampaign();

        emailCampaign = saveObject(req, emailCampaign, model);

        return created(emailCampaignModelService.toModel(emailCampaign));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<EmailCampaignModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailCampaignModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/campaigns/" + uid);

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        emailCampaign = saveObject(req, emailCampaign, model);

        return ok(emailCampaignModelService.toModel(emailCampaign));
    }

    @RequestMapping(value = "/{uid}/test", method=RequestMethod.POST)
    public ResponseEntity<EmailModel> sendTestEmail(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody EmailModel model
    ) {
        logApiRequest(req, "POST /admin/messages/emails/campaigns/" + uid + "/test");

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        if (model.getToAddress() == null) {
            throw new BadRequestException("toAddress must be specified");
        }

        if (!KValidator.isEmail(model.getToAddress())) {
            throw new ValidationException("Invalid email address: " + model.getToAddress());
        }

        Email email = emailCampaignService.sendTestEmail(emailCampaign, model.getToAddress());

        if (email == null) {
            throw new SystemException("Error sending email to: " + model.getToAddress());
        }

        return ok(emailModelService.toModel(email));
    }

    @RequestMapping(value = "/{uid}/start", method=RequestMethod.PUT)
    public ResponseEntity<EmailCampaignModel> startCampaign(
            HttpServletRequest req,
            @RequestParam(value="throttle_time", required=false) Long throttleTime,
            @RequestParam(value="force", required=false) Boolean force,
            @PathVariable String uid,
            @RequestBody EmailCampaignModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/campaigns/" + uid + "/start");

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        if (throttleTime == null) {
            throttleTime = 500L;
        }

        if (force == null) {
            force = false;
        }

        emailCampaign = emailCampaignService.start(emailCampaign, throttleTime, force);

        return ok(emailCampaignModelService.toModel(emailCampaign));
    }

    @RequestMapping(value = "/{uid}/stats", method=RequestMethod.PUT)
    public ResponseEntity<EmailCampaignModel> updateStats(
            HttpServletRequest req,
            @RequestParam(value="process_notifications", required=false) Boolean processNotifications,
            @PathVariable String uid,
            @RequestBody EmailCampaignModel model
    ) {
        logApiRequest(req, "PUT /admin/messages/emails/campaigns/" + uid + "/stats");

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        if (processNotifications == null) {
            processNotifications = false;
        }

        emailCampaign = emailCampaignService.updateStats(emailCampaign, processNotifications);

        return ok(emailCampaignModelService.toModel(emailCampaign));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<EmailCampaignModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/messages/emails/campaigns/" + uid);

        EmailCampaign emailCampaign = emailCampaignModelService.getEntity(uid);

        emailCampaignService.remove(emailCampaign);

        //return ok(emailCampaignModelService.toModel(emailCampaign));
        return ok(EmailCampaignModel.create(emailCampaign.getUid()));
    }


    public EmailCampaign saveObject(
            HttpServletRequest req,
            EmailCampaign emailCampaign,
            EmailCampaignModel model
    ) {
        logger.debug("mapToObject called for emailCampaign: " + emailCampaign);

        if (model.getName() == null) {
            throw new ValidationException("name property must be set");
        }

        emailCampaign = emailCampaignModelService.mergeEntity(emailCampaign, model);

        if (emailCampaign.getId() == null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(emailCampaign.getCampaignChannelId());
            EmailGroup group = emailGroupModelService.getEntity(emailCampaign.getEmailGroupId());
            EmailContent content = emailContentModelService.getEntity(emailCampaign.getEmailContentId());

            emailCampaign = emailCampaignService.create(
                    getUser(),
                    emailCampaign.getName(),
                    channel,
                    group,
                    content,
                    emailCampaign.getFromAddress(),
                    emailCampaign.getReplyTo(),
                    emailCampaign.getSubject()
            );
        } else {
            emailCampaign = emailCampaignService.save(emailCampaign);
        }

        return emailCampaign;
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

                case "campaignUid":
                    Campaign campaign = campaignModelService.getCampaign(util.getStringValue(value));
                    result.put(prefix + "campaignId", campaign == null ? -1L : campaign.getId());
                    break;

                case "campaignGroupUid":
                    CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(util.getStringValue(value));
                    result.put(prefix + "campaignGroupId", campaignGroup == null ? -1L : campaignGroup.getId());
                    break;

                case "campaignChannelUid":
                    CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(util.getStringValue(value));
                    result.put(prefix + "campaignChannelId", campaignChannel == null ? -1L : campaignChannel.getId());
                    break;

                case "emailGroupUid":
                    EmailGroup emailGroup = emailGroupModelService.getEntity(util.getStringValue(value));
                    result.put(prefix + "emailGroupId", emailGroup == null ? -1L : emailGroup.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

