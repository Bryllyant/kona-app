package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.entity.UserAuthExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<UserAuth, UserAuthExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<UserAuth> selectByExample(UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    UserAuth selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(UserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(UserAuth record);
}