package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Email, EmailExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    long countByExample(EmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByExample(EmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insert(Email record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insertSelective(Email record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    List<Email> selectByExample(EmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    Email selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Email record, @Param("example") EmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExample(@Param("record") Email record, @Param("example") EmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKeySelective(Email record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKey(Email record);
}