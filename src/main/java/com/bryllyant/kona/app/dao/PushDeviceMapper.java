package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.PushDeviceExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushDeviceMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PushDevice, PushDeviceExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    long countByExample(PushDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByExample(PushDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insert(PushDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insertSelective(PushDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<PushDevice> selectByExample(PushDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    PushDevice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PushDevice record, @Param("example") PushDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExample(@Param("record") PushDevice record, @Param("example") PushDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeySelective(PushDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKey(PushDevice record);
}