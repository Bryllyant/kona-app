package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.entity.UserDeviceExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDeviceMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<UserDevice, UserDeviceExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    long countByExample(UserDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByExample(UserDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insert(UserDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insertSelective(UserDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<UserDevice> selectByExample(UserDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    UserDevice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") UserDevice record, @Param("example") UserDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExample(@Param("record") UserDevice record, @Param("example") UserDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeySelective(UserDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKey(UserDevice record);
}