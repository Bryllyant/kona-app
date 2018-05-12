package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignGroupExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignGroupMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<CampaignGroup, CampaignGroupExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    long countByExample(CampaignGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByExample(CampaignGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insert(CampaignGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insertSelective(CampaignGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    List<CampaignGroup> selectByExample(CampaignGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    CampaignGroup selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExampleSelective(@Param("record") CampaignGroup record, @Param("example") CampaignGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExample(@Param("record") CampaignGroup record, @Param("example") CampaignGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKeySelective(CampaignGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_group
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKey(CampaignGroup record);
}