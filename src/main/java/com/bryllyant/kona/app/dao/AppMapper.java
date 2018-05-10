package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<App, AppExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    long countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    App selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKey(App record);
}