package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.AppUserExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUserMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AppUser, AppUserExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(AppUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(AppUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<AppUser> selectByExample(AppUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    AppUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AppUser record, @Param("example") AppUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") AppUser record, @Param("example") AppUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_user
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(AppUser record);

    int updateCoords(Long objectId);

    List<AppUser> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
