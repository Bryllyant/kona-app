package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.EmailEventExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailEventMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<EmailEvent, EmailEventExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    long countByExample(EmailEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int deleteByExample(EmailEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int insert(EmailEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int insertSelective(EmailEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    List<EmailEvent> selectByExample(EmailEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    EmailEvent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByExampleSelective(@Param("record") EmailEvent record, @Param("example") EmailEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByExample(@Param("record") EmailEvent record, @Param("example") EmailEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByPrimaryKeySelective(EmailEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_event
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByPrimaryKey(EmailEvent record);
}