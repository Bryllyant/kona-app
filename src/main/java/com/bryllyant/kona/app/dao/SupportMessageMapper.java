package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.app.entity.SupportMessageExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupportMessageMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<SupportMessage, SupportMessageExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    long countByExample(SupportMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByExample(SupportMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insert(SupportMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insertSelective(SupportMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    List<SupportMessage> selectByExample(SupportMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    SupportMessage selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") SupportMessage record, @Param("example") SupportMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExample(@Param("record") SupportMessage record, @Param("example") SupportMessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKeySelective(SupportMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__support_message
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKey(SupportMessage record);
}