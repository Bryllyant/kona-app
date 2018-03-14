package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KCampaign.Goal;
import com.bryllyant.kona.app.entity.KCampaign.KPI;
import java.io.Serializable;

public class Campaign extends KBaseCampaign implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.goal
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    private Goal goal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.kpi
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    private KPI kpi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.goal
     *
     * @return the value of kona__campaign.goal
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.goal
     *
     * @param goal the value for kona__campaign.goal
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.kpi
     *
     * @return the value of kona__campaign.kpi
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    public KPI getKpi() {
        return kpi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.kpi
     *
     * @param kpi the value for kona__campaign.kpi
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    public void setKpi(KPI kpi) {
        this.kpi = kpi;
    }
}