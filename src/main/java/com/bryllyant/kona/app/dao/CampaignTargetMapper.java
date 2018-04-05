package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.CampaignTargetExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignTargetMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<CampaignTarget, CampaignTargetExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    long countByExample(CampaignTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByExample(CampaignTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insert(CampaignTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insertSelective(CampaignTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    List<CampaignTarget> selectByExample(CampaignTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    CampaignTarget selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExampleSelective(@Param("record") CampaignTarget record, @Param("example") CampaignTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExample(@Param("record") CampaignTarget record, @Param("example") CampaignTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKeySelective(CampaignTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKey(CampaignTarget record);
}