package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailGroupMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    long countByExample(EmailGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int deleteByExample(EmailGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int insert(EmailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int insertSelective(EmailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    List<EmailGroup> selectByExample(EmailGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    EmailGroup selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByExampleSelective(@Param("record") EmailGroup record, @Param("example") EmailGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByExample(@Param("record") EmailGroup record, @Param("example") EmailGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByPrimaryKeySelective(EmailGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByPrimaryKey(EmailGroup record);
}