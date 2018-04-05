package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParamExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandingPageTemplateParamMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<LandingPageTemplateParam, LandingPageTemplateParamExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    long countByExample(LandingPageTemplateParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByExample(LandingPageTemplateParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insert(LandingPageTemplateParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insertSelective(LandingPageTemplateParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    List<LandingPageTemplateParam> selectByExample(LandingPageTemplateParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    LandingPageTemplateParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExampleSelective(@Param("record") LandingPageTemplateParam record, @Param("example") LandingPageTemplateParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExample(@Param("record") LandingPageTemplateParam record, @Param("example") LandingPageTemplateParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKeySelective(LandingPageTemplateParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKey(LandingPageTemplateParam record);
}