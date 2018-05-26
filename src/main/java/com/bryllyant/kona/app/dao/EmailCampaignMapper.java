package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailCampaignExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailCampaignMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<EmailCampaign, EmailCampaignExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(EmailCampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(EmailCampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(EmailCampaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(EmailCampaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<EmailCampaign> selectByExample(EmailCampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    EmailCampaign selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") EmailCampaign record, @Param("example") EmailCampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") EmailCampaign record, @Param("example") EmailCampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(EmailCampaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_campaign
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(EmailCampaign record);
}