package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.AppConfigExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppConfigMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<AppConfig> selectByExample(AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    AppConfig selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") AppConfig record, @Param("example") AppConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(AppConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_config
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(AppConfig record);
}