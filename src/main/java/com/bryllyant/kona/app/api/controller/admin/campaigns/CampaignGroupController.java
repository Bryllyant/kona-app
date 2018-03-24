package com.bryllyant.kona.app.api.controller.admin.campaigns;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.service.CampaignGroupModelService;
import com.bryllyant.kona.app.api.service.CampaignModelService;
import com.bryllyant.kona.app.api.service.PartnerModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.service.CampaignGroupService;
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
@RequestMapping("/api/admin/campaigns/groups")
public class CampaignGroupController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CampaignGroupController.class);

    @Autowired
    private CampaignGroupService campaignGroupService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private PartnerModelService partnerModelService;

    @Autowired
    private ApiUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<CampaignGroupModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/campaigns/groups");

        logger.debug("CampaignGroupController: raw query: " + query);

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

        logger.debug("CampaignGroupController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = campaignGroupService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, campaignGroupModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<CampaignGroupModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/campaigns/groups/" + uid);

        CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(uid);

        return ok(campaignGroupModelService.toModel(campaignGroup));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampaignGroupModel> create(
            HttpServletRequest req,
            @RequestBody CampaignGroupModel model
    ) {
        logApiRequest(req, "POST /admin/campaigns/groups");

        CampaignGroup campaignGroup = new CampaignGroup();

        campaignGroup = saveObject(req, campaignGroup, model);

        return created(campaignGroupModelService.toModel(campaignGroup));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<CampaignGroupModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody CampaignGroupModel model
    ) {
        logApiRequest(req, "PUT /admin/campaigns/groups/" + uid);

        CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        campaignGroup = saveObject(req, campaignGroup, model);

        return ok(campaignGroupModelService.toModel(campaignGroup));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<CampaignGroupModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/campaigns/groups/" + uid);

        CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(uid);

        campaignGroupService.remove(campaignGroup);

        //return ok(campaignGroupModelService.toModel(campaignGroup));
        return ok(CampaignGroupModel.create(campaignGroup.getUid()));
    }


    public CampaignGroup saveObject(
            HttpServletRequest req,
            CampaignGroup campaignGroup,
            CampaignGroupModel model
    ) {
        logger.debug("mapToObject called for campaignGroup: " + campaignGroup);

        if (model.getUid() == null && model.getCampaign() == null) {
            throw new ValidationException("campaign property must be set");
        }

        campaignGroup = campaignGroupModelService.mergeEntity(campaignGroup, model);

        if (campaignGroup.getId() == null && model.getEnabled() == null) {
            campaignGroup.setEnabled(true);
        }

        if (campaignGroup.getId() == null) {
            Campaign campaign = campaignModelService.getCampaign(campaignGroup.getCampaignId());
            campaignGroup = campaignGroupService.create(campaign, campaignGroup);
        } else {
            campaignGroup = campaignGroupService.update(campaignGroup);
        }

        return campaignGroup;
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

                case "partnerUid":
                    Partner partner = partnerModelService.getPartner(util.getStringValue(value));
                    result.put(prefix + "partnerId", partner == null ? -1L : partner.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

