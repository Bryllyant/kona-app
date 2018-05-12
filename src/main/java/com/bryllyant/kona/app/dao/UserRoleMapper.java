package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.app.entity.UserRoleExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<UserRole, UserRoleExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    long countByExample(UserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByExample(UserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insertSelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    List<UserRole> selectByExample(UserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_role
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKey(UserRole record);
}