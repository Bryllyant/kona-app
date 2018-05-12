package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Setting;
import com.bryllyant.kona.app.entity.SettingExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettingMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Setting, SettingExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    long countByExample(SettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByExample(SettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insert(Setting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int insertSelective(Setting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    List<Setting> selectByExample(SettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    Setting selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Setting record, @Param("example") SettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByExample(@Param("record") Setting record, @Param("example") SettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKeySelective(Setting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    int updateByPrimaryKey(Setting record);
}