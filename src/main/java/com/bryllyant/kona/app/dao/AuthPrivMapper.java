package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthPrivExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthPrivMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AuthPriv, AuthPrivExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    long countByExample(AuthPrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int deleteByExample(AuthPrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int insert(AuthPriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int insertSelective(AuthPriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    List<AuthPriv> selectByExample(AuthPrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    AuthPriv selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AuthPriv record, @Param("example") AuthPrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int updateByExample(@Param("record") AuthPriv record, @Param("example") AuthPrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int updateByPrimaryKeySelective(AuthPriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_priv
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    int updateByPrimaryKey(AuthPriv record);
}