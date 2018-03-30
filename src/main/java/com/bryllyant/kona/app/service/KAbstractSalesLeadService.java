package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.app.entity.KSalesLead;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractSalesLeadService<
        SALES_LEAD extends KSalesLead,
        SALES_LEAD_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<SALES_LEAD, SALES_LEAD_EXAMPLE>,
        USER extends KUser,
        CAMPAIGN_ANALYTICS extends KCampaignAnalytics>
        extends KAbstractService<SALES_LEAD,SALES_LEAD_EXAMPLE,MAPPER>
		implements KSalesLeadService<SALES_LEAD> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractSalesLeadService.class);
    
	protected abstract void sendNotification(SALES_LEAD lead);

    protected abstract <S extends KUserService<USER>> S getUserService();
    protected abstract <S extends KCampaignAnalyticsService<CAMPAIGN_ANALYTICS>> S getCampaignAnalyticsService();

	@Override
	public void validate(SALES_LEAD salesLead) {
		if (salesLead.getCreatedDate() == null) {
			salesLead.setCreatedDate(new Date());
		}

		if (salesLead.getUid() == null) {
			salesLead.setUid(uuid());
		}
	}


    @Override @Transactional
    public SALES_LEAD create(SALES_LEAD salesLead, KServiceClient client) {
	    // TODO: validate email and mobileNumber

	    if (salesLead.getUserId() == null) {
	        USER user = null;

	        if (salesLead.getEmail() != null) {
	            user = getUserService().fetchByEmail(salesLead.getEmail());
            }

            if (user == null && salesLead.getMobileNumber() != null) {
	            user = getUserService().fetchByMobileNumber(salesLead.getMobileNumber());
            }

            if (user != null) {
	            salesLead.setUserId(user.getId());
            }
        }

        String action = "subscribe";
        String category = KCampaign.Goal.LEAD_GEN.name().toLowerCase();
        String label = null;
        Double value = null;

        CAMPAIGN_ANALYTICS analytics = getCampaignAnalyticsService().create(
                client,
                salesLead.getTargetId(),
                action,
                category,
                label,
                value,
                salesLead.isCampaignConversion(),
                salesLead.getUserId(),
                salesLead.getEmail(),
                salesLead.getMobileNumber()
        );

        salesLead.setAnalyticsId(analytics.getId());

        salesLead = add(salesLead);

        analytics.setLabel(salesLead.getUid());
        getCampaignAnalyticsService().save(analytics);

        sendNotification(salesLead);

        return salesLead;
    }

    @Override
    public SALES_LEAD fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<SALES_LEAD> fetchByReferredById(Long referredById) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("referredById", referredById);
		return fetchByCriteria(filter);
	}

    @Override
    public List<SALES_LEAD> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }


}

