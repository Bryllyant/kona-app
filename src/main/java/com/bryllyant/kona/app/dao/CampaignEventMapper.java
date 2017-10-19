package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CampaignEvent;
import com.bryllyant.kona.app.entity.CampaignEventExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CampaignEventMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(CampaignEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(CampaignEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(CampaignEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(CampaignEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<CampaignEvent> selectByExample(CampaignEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    CampaignEvent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") CampaignEvent record, @Param("example") CampaignEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") CampaignEvent record, @Param("example") CampaignEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(CampaignEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_event
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(CampaignEvent record);

    int updateCoords(Long objectId);

    List<CampaignEvent> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
}           
