package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailContentExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailContentMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<EmailContent, EmailContentExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(EmailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(EmailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<EmailContent> selectByExampleWithBLOBs(EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<EmailContent> selectByExample(EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    EmailContent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") EmailContent record, @Param("example") EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") EmailContent record, @Param("example") EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") EmailContent record, @Param("example") EmailContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(EmailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeyWithBLOBs(EmailContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_content
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(EmailContent record);
}