package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KCampaignReply.Type;
import java.io.Serializable;

public class CampaignReply extends KBaseCampaignReply implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.type
     *
     * @return the value of kona__campaign_reply.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.type
     *
     * @param type the value for kona__campaign_reply.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}