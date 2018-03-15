package com.bryllyant.kona.app.api.controller.admin.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.service.CampaignModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CampaignService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/admin/sales/campaigns")
public class CampaignController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<CampaignModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/campaigns");

        logger.debug("CampaignController: raw query: " + query);

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

        logger.debug("CampaignController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = campaignService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, campaignModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<CampaignModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/campaigns/" + uid);

        Campaign campaign = campaignModelService.getCampaign(uid);

        return ok(campaignModelService.toModel(campaign));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampaignModel> create(
            HttpServletRequest req,
            @RequestBody CampaignModel model
    ) {
        logApiRequest(req, "POST /admin/sales/campaigns");

        Campaign campaign = new Campaign();

        campaign = saveObject(req, campaign, model);

        return created(campaignModelService.toModel(campaign));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<CampaignModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody CampaignModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/campaigns/" + uid);

        Campaign campaign = campaignModelService.getCampaign(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        campaign = saveObject(req, campaign, model);

        return ok(campaignModelService.toModel(campaign));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<CampaignModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/campaigns/" + uid);

        Campaign campaign = campaignModelService.getCampaign(uid);

        campaignService.remove(campaign);

        //return ok(campaignModelService.toModel(campaign));
        return ok(CampaignModel.create(campaign.getUid()));
    }


    public Campaign saveObject(
            HttpServletRequest req,
            Campaign campaign,
            CampaignModel model
    ) {
        logger.debug("mapToObject called for campaign: " + campaign);

        campaign = campaignModelService.mergeEntity(campaign, model);

        if (campaign.getId() == null && model.getEnabled() == null) {
            campaign.setEnabled(true);
        }

        if (campaign.getId() == null) {
            campaign = campaignService.create(campaign);
        } else {
            campaign = campaignService.update(campaign);
        }

        return campaign;
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

