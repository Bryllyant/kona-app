package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.InvoiceItemExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceItemMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<InvoiceItem, InvoiceItemExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    long countByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insert(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insertSelective(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    List<InvoiceItem> selectByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    InvoiceItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") InvoiceItem record, @Param("example") InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExample(@Param("record") InvoiceItem record, @Param("example") InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKeySelective(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKey(InvoiceItem record);
}