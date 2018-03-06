package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.AppConfigExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppConfigMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AppConfig, AppConfigExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<AppConfig> selectByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    AppConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(AppConfig record);
}