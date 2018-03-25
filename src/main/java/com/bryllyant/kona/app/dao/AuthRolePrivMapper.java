package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AuthRolePriv;
import com.bryllyant.kona.app.entity.AuthRolePrivExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthRolePrivMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AuthRolePriv, AuthRolePrivExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(AuthRolePrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(AuthRolePrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(AuthRolePriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(AuthRolePriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<AuthRolePriv> selectByExample(AuthRolePrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    AuthRolePriv selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AuthRolePriv record, @Param("example") AuthRolePrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") AuthRolePriv record, @Param("example") AuthRolePrivExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(AuthRolePriv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(AuthRolePriv record);
}