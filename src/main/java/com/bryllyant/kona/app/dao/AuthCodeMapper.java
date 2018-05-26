package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.AuthCodeExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthCodeMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AuthCode, AuthCodeExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(AuthCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(AuthCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(AuthCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(AuthCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<AuthCode> selectByExample(AuthCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    AuthCode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AuthCode record, @Param("example") AuthCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") AuthCode record, @Param("example") AuthCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(AuthCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(AuthCode record);
}