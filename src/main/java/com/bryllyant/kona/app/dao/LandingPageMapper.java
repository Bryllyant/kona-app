package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandingPageMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<LandingPage, LandingPageExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    long countByExample(LandingPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int deleteByExample(LandingPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int insert(LandingPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int insertSelective(LandingPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    List<LandingPage> selectByExample(LandingPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    LandingPage selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByExampleSelective(@Param("record") LandingPage record, @Param("example") LandingPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByExample(@Param("record") LandingPage record, @Param("example") LandingPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByPrimaryKeySelective(LandingPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByPrimaryKey(LandingPage record);
}