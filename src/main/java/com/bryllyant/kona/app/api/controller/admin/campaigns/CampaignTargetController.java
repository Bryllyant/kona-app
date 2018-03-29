package com.bryllyant.kona.app.api.controller.admin.campaigns;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignTargetModel;
import com.bryllyant.kona.app.api.service.CampaignChannelModelService;
import com.bryllyant.kona.app.api.service.CampaignGroupModelService;
import com.bryllyant.kona.app.api.service.CampaignModelService;
import com.bryllyant.kona.app.api.service.CampaignTargetModelService;
import com.bryllyant.kona.app.api.service.LandingPageModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignTargetService;
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
@RequestMapping("/api/admin/campaigns/targets")
public class CampaignTargetController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CampaignTargetController.class);

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<CampaignTargetModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/campaigns/targets");

        logger.debug("CampaignTargetController: raw query: " + query);

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

        logger.debug("CampaignTargetController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = campaignTargetService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, campaignTargetModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<CampaignTargetModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/campaigns/targets/" + uid);

        CampaignTarget campaignTarget = campaignTargetModelService.getCampaignTarget(uid);

        return ok(campaignTargetModelService.toModel(campaignTarget));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampaignTargetModel> create(
            HttpServletRequest req,
            @RequestBody CampaignTargetModel model
    ) {
        logApiRequest(req, "POST /admin/campaigns/targets");

        CampaignTarget campaignTarget = new CampaignTarget();

        campaignTarget = saveObject(req, campaignTarget, model);

        return created(campaignTargetModelService.toModel(campaignTarget));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<CampaignTargetModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody CampaignTargetModel model
    ) {
        logApiRequest(req, "PUT /admin/campaigns/targets/" + uid);

        CampaignTarget campaignTarget = campaignTargetModelService.getCampaignTarget(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        campaignTarget = saveObject(req, campaignTarget, model);

        return ok(campaignTargetModelService.toModel(campaignTarget));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<CampaignTargetModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/campaigns/targets/" + uid);

        CampaignTarget campaignTarget = campaignTargetModelService.getCampaignTarget(uid);

        campaignTargetService.remove(campaignTarget);

        return ok(CampaignTargetModel.create(campaignTarget.getUid()));
    }


    public CampaignTarget saveObject(
            HttpServletRequest req,
            CampaignTarget campaignTarget,
            CampaignTargetModel model
    ) {
        logger.debug("mapToObject called for campaignTarget: " + campaignTarget);

        if (model.getUid() == null && model.getCampaign() == null) {
            throw new ValidationException("campaign property must be set");
        }

        campaignTarget = campaignTargetModelService.mergeEntity(campaignTarget, model);

        if (campaignTarget.getId() == null && model.getEnabled() == null) {
            campaignTarget.setEnabled(true);
        }

        if (campaignTarget.getId() == null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(campaignTarget.getChannelId());

            if (channel.getTargetType() != campaignTarget.getType()) {
                throw new ValidationException("Channel target type and target type must be the same."
                        + "\nchannel: " + channel
                        + "\ntarget: " + campaignTarget);
            }

            campaignTarget = campaignTargetService.create(channel, campaignTarget);
        } else {
            campaignTarget = campaignTargetService.update(campaignTarget);
        }

        return campaignTarget;
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
                case "campaignUid":
                    Campaign campaign = campaignModelService.getCampaign(util.getStringValue(value));
                    result.put(prefix + "campaignId", campaign == null ? -1L : campaign.getId());
                    break;

                case "groupUid":
                    CampaignGroup group = campaignGroupModelService.getCampaignGroup(util.getStringValue(value));
                    result.put(prefix + "groupId", group == null ? -1L : group.getId());
                    break;

                case "channelUid":
                    CampaignChannel channel = campaignChannelModelService.getCampaignChannel(util.getStringValue(value));
                    result.put(prefix + "channelId", channel == null ? -1L : channel.getId());
                    break;

                case "landingPageUid":
                    LandingPage page = landingPageModelService.getLandingPage(util.getStringValue(value));
                    result.put(prefix + "landingPageId", page == null ? -1L : page.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

