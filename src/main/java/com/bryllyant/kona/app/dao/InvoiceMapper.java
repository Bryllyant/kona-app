package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    long countByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    List<Invoice> selectByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    Invoice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invoice
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKey(Invoice record);
}