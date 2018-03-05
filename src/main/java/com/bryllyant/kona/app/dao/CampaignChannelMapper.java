package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignChannelExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignChannelMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    long countByExample(CampaignChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByExample(CampaignChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insert(CampaignChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insertSelective(CampaignChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    List<CampaignChannel> selectByExample(CampaignChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    CampaignChannel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExampleSelective(@Param("record") CampaignChannel record, @Param("example") CampaignChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExample(@Param("record") CampaignChannel record, @Param("example") CampaignChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKeySelective(CampaignChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKey(CampaignChannel record);
}