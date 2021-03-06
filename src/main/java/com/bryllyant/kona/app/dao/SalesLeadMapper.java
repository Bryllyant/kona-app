package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.SalesLeadExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalesLeadMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<SalesLead, SalesLeadExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    long countByExample(SalesLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByExample(SalesLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insert(SalesLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insertSelective(SalesLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<SalesLead> selectByExample(SalesLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    SalesLead selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") SalesLead record, @Param("example") SalesLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExample(@Param("record") SalesLead record, @Param("example") SalesLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeySelective(SalesLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKey(SalesLead record);
}