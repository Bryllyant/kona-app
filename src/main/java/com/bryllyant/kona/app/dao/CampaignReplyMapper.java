package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignReplyMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<CampaignReply, CampaignReplyExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    long countByExample(CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByExample(CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insert(CampaignReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insertSelective(CampaignReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    List<CampaignReply> selectByExampleWithBLOBs(CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    List<CampaignReply> selectByExample(CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    CampaignReply selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExampleSelective(@Param("record") CampaignReply record, @Param("example") CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") CampaignReply record, @Param("example") CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExample(@Param("record") CampaignReply record, @Param("example") CampaignReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKeySelective(CampaignReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(CampaignReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKey(CampaignReply record);
}