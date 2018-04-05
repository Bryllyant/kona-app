package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRoleExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthRoleMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AuthRole, AuthRoleExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    long countByExample(AuthRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByExample(AuthRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insert(AuthRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insertSelective(AuthRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    List<AuthRole> selectByExample(AuthRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    AuthRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExample(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKeySelective(AuthRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_role
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKey(AuthRole record);
}