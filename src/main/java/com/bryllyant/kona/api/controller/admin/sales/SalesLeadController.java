package com.bryllyant.kona.api.controller.admin.sales;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.service.CampaignModelService;
import com.bryllyant.kona.api.service.CampaignTargetModelService;
import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.sales.saleslead.SalesLeadModel;
import com.bryllyant.kona.api.service.CampaignAnalyticsModelService;
import com.bryllyant.kona.api.service.CampaignChannelModelService;
import com.bryllyant.kona.api.service.CampaignGroupModelService;
import com.bryllyant.kona.api.service.CampaignModelService;
import com.bryllyant.kona.api.service.CampaignTargetModelService;
import com.bryllyant.kona.api.service.SalesLeadModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
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
@RequestMapping("/api/admin/sales/leads")
public class SalesLeadController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SalesLeadController.class);

    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private SalesLeadModelService salesLeadModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;

    @Autowired
    private CampaignAnalyticsModelService campaignAnalyticsModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<SalesLeadModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/leads");

        logger.debug("SalesLeadController: raw query: " + query);

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

        logger.debug("SalesLeadController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = salesLeadService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, salesLeadModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<SalesLeadModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/leads/" + uid);

        SalesLead lead = salesLeadModelService.getSalesLead(uid);

        return ok(salesLeadModelService.toModel(lead));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SalesLeadModel> create(
            HttpServletRequest req,
            @RequestBody SalesLeadModel model
    ) {
        logApiRequest(req, "POST /admin/sales/leads");

        SalesLead lead = new SalesLead();

        lead = saveObject(req, lead, model);

        return created(salesLeadModelService.toModel(lead));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<SalesLeadModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody SalesLeadModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/leads/" + uid);

        SalesLead lead = salesLeadModelService.getSalesLead(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        lead = saveObject(req, lead, model);

        return ok(salesLeadModelService.toModel(lead));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<SalesLeadModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/leads/" + uid);

        SalesLead lead = salesLeadModelService.getSalesLead(uid);

        salesLeadService.remove(lead);

        return ok(SalesLeadModel.create(lead.getUid()));
    }


    public SalesLead saveObject(
            HttpServletRequest req,
            SalesLead lead,
            SalesLeadModel model
    ) {
        logger.debug("mapToObject called for lead: " + lead);

        lead = salesLeadModelService.mergeEntity(lead, model);

        if (lead.getId() == null) {
            KServiceClient client = getServiceClient(req);
            lead = salesLeadService.create(lead, client);
        } else  {
            lead = salesLeadService.save(lead);
        }

        return lead;
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

                case "targetUid":
                    CampaignTarget target = campaignTargetModelService.getCampaignTarget(util.getStringValue(value));
                    result.put(prefix + "targetId", target == null ? -1L : target.getId());
                    break;

                case "analyticsUid":
                    CampaignAnalytics analytics = campaignAnalyticsModelService.getCampaignAnalytics(util.getStringValue(value));
                    result.put(prefix + "analyticsId", analytics == null ? -1L : analytics.getId());
                    break;

                case "referredByUid":
                    User referredBy = userModelService.getUser(util.getStringValue(value));
                    result.put(prefix + "referredById", referredBy == null ? -1L : referredBy.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

