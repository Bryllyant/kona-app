package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignAnalyticsExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignAnalyticsMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<CampaignAnalytics, CampaignAnalyticsExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(CampaignAnalyticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(CampaignAnalyticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(CampaignAnalytics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(CampaignAnalytics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<CampaignAnalytics> selectByExample(CampaignAnalyticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    CampaignAnalytics selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") CampaignAnalytics record, @Param("example") CampaignAnalyticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") CampaignAnalytics record, @Param("example") CampaignAnalyticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(CampaignAnalytics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(CampaignAnalytics record);

    int updateCoords(Long objectId);

    List<CampaignAnalytics> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
