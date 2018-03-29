package com.bryllyant.kona.app.api.controller.admin.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.api.service.PartnerModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.service.PartnerService;
import com.bryllyant.kona.app.service.SystemService;
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
@RequestMapping("/api/admin/sales/partners")
public class PartnerController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PartnerController.class);

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerModelService partnerModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<PartnerModel>> search(
            HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/sales/partners");

        logger.debug("PartnerController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        if (sortOrder == null) {
            sortOrder = new String[]{
                "name"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("PartnerController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = partnerService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, partnerModelService.toModelList(result));

        return okList(resultSet);
    }


    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<PartnerModel> get(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "GET /admin/sales/partners/" + uid);

        Partner partner = partnerModelService.getPartner(uid);

        return ok(partnerModelService.toModel(partner));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PartnerModel> create(
            HttpServletRequest req,
            @RequestBody PartnerModel model
    ) {
        logApiRequest(req, "POST /admin/sales/partners");

        Partner partner = new Partner();

        partner = saveObject(req, partner, model);

        return created(partnerModelService.toModel(partner));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<PartnerModel> update(
            HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody PartnerModel model
    ) {
        logApiRequest(req, "PUT /admin/sales/partners/" + uid);

        Partner partner = partnerModelService.getPartner(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        partner = saveObject(req, partner, model);

        return ok(partnerModelService.toModel(partner));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<PartnerModel> remove(
            HttpServletRequest req,
            @PathVariable String uid
    ) {
        logApiRequest(req, "DELETE /admin/sales/partners/" + uid);

        Partner partner = partnerModelService.getPartner(uid);

        partnerService.remove(partner);

        //return ok(partnerModelService.toModel(partner));
        return ok(PartnerModel.create(partner.getUid(), null));
    }


    public Partner saveObject(
            HttpServletRequest req,
            Partner partner,
            PartnerModel model
    ) {
        logger.debug("mapToObject called for partner: " + partner);

        partner = partnerModelService.mergeEntity(partner, model);

        if (partner.getId() == null && model.getEnabled() == null) {
            partner.setEnabled(true);
        }

        return partnerService.save(partner);
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
                case "parentUid":
                    Partner parent = partnerModelService.getPartner(util.getStringValue(value));
                    result.put(prefix + "parentId", parent == null ? -1L : parent.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    }

}

