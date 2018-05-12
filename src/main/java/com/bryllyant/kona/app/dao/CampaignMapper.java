package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Campaign, CampaignExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    long countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insert(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insertSelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    List<Campaign> selectByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    Campaign selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExample(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKeySelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKey(Campaign record);
}