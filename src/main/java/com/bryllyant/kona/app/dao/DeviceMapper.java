package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.DeviceExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Device, DeviceExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    long countByExample(DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int deleteByExample(DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int insert(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int insertSelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    List<Device> selectByExample(DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    Device selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int updateByPrimaryKeySelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__device
     *
     * @mbg.generated Wed Apr 11 12:22:57 EDT 2018
     */
    int updateByPrimaryKey(Device record);
}