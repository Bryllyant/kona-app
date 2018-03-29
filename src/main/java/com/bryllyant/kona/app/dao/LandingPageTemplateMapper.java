package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandingPageTemplateMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<LandingPageTemplate, LandingPageTemplateExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    long countByExample(LandingPageTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByExample(LandingPageTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insert(LandingPageTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insertSelective(LandingPageTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    List<LandingPageTemplate> selectByExample(LandingPageTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    LandingPageTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") LandingPageTemplate record, @Param("example") LandingPageTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExample(@Param("record") LandingPageTemplate record, @Param("example") LandingPageTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKeySelective(LandingPageTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKey(LandingPageTemplate record);
}