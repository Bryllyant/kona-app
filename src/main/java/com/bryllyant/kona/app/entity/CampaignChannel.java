package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KCampaignChannel.Type;
import java.io.Serializable;

public class CampaignChannel extends KBaseCampaignChannel implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_channel.type
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_channel.type
     *
     * @return the value of kona__campaign_channel.type
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_channel.type
     *
     * @param type the value for kona__campaign_channel.type
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}