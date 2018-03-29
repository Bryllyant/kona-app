package com.bryllyant.kona.app.api.controller.admin.campaigns;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.service.CampaignChannelModelService;
import com.bryllyant.kona.app.api.service.CampaignGroupModelService;
import com.bryllyant.kona.app.api.service.CampaignModelService;
import com.bryllyant.kona.app.api.service.LandingPageModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignGroupService;
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
@RequestMapping("/api/admin/campaigns/channels")
public class CampaignChannelController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CampaignChannelController.class);

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignGroupService campaignGroupService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<CampaignChannelModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/campaigns/channels");

        logger.debug("CampaignChannelController: raw query: " + query);

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

        logger.debug("CampaignChannelController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = campaignChannelService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, campaignChannelModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<CampaignChannelModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/campaigns/channels/" + uid);

        CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(uid);

        return ok(campaignChannelModelService.toModel(campaignChannel));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampaignChannelModel> create(
            HttpServletRequest req,
            @RequestBody CampaignChannelModel model
    ) {
        logApiRequest(req, "POST /admin/campaigns/channels");

        CampaignChannel campaignChannel = new CampaignChannel();

        campaignChannel = saveObject(req, campaignChannel, model);

        return created(campaignChannelModelService.toModel(campaignChannel));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<CampaignChannelModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody CampaignChannelModel model
    ) {
        logApiRequest(req, "PUT /admin/campaigns/channels/" + uid);

        CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        campaignChannel = saveObject(req, campaignChannel, model);

        return ok(campaignChannelModelService.toModel(campaignChannel));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<CampaignChannelModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/campaigns/channels/" + uid);

        CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(uid);

        campaignChannelService.remove(campaignChannel);

        //return ok(campaignChannelModelService.toModel(campaignChannel));
        return ok(CampaignChannelModel.create(campaignChannel.getUid()));
    }


    public CampaignChannel saveObject(
            HttpServletRequest req,
            CampaignChannel campaignChannel,
            CampaignChannelModel model
    ) {
        logger.debug("mapToObject called for campaignChannel: " + campaignChannel);

        if (model.getUid() == null && model.getCampaign() == null) {
            throw new ValidationException("campaign property must be set");
        }

        campaignChannel = campaignChannelModelService.mergeEntity(campaignChannel, model);

        if (campaignChannel.getId() == null && model.getEnabled() == null) {
            campaignChannel.setEnabled(true);
        }

        if (campaignChannel.getId() == null) {
            CampaignGroup group = campaignGroupModelService.getCampaignGroup(campaignChannel.getGroupId());
            campaignChannel = campaignChannelService.create(group, campaignChannel);
        } else {
            campaignChannel = campaignChannelService.update(campaignChannel);
        }

        return campaignChannel;
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

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

