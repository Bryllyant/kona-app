package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.InvoiceItemExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceItemMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    long countByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int deleteByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int insert(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int insertSelective(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    List<InvoiceItem> selectByExample(InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    InvoiceItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByExampleSelective(@Param("record") InvoiceItem record, @Param("example") InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByExample(@Param("record") InvoiceItem record, @Param("example") InvoiceItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByPrimaryKeySelective(InvoiceItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice_item
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByPrimaryKey(InvoiceItem record);
}