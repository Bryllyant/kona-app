package com.bryllyant.kona.api.model.sales.promo;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PromoCodeModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private PromoModel promo;
    private CampaignChannelModel campaignChannel;
    private String promoCode;
    private Date createdDate;
    private Date updatedDate;

    public static PromoCodeModel from(PromoCode code) {
        PromoCodeModel model = new PromoCodeModel();
        model.setUid(code.getUid());
        model.setPromoCode(code.getPromoCode());
        return model;
    }

    public static PromoCodeModel create(String uid, String promoCode, String campaignChannelUid) {
        PromoCodeModel model = new PromoCodeModel();
        model.setUid(uid);
        model.setPromoCode(promoCode);

        CampaignChannelModel channel = CampaignChannelModel.create(campaignChannelUid);
        model.setCampaignChannel(channel);

        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public PromoModel getPromo() {
        return promo;
    }

    public void setPromo(PromoModel promo) {
        this.set("promo", promo);
    }

    public CampaignChannelModel getCampaignChannel() {
        return campaignChannel;
    }

    public void setCampaignChannel(CampaignChannelModel campaignChannel) {
        this.set("campaignChannel", campaignChannel);
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.set("promoCode", promoCode);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
