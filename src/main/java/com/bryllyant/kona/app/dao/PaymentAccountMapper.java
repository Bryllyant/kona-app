package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.PaymentAccountExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentAccountMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PaymentAccount, PaymentAccountExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    long countByExample(PaymentAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByExample(PaymentAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insert(PaymentAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int insertSelective(PaymentAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    List<PaymentAccount> selectByExample(PaymentAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    PaymentAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PaymentAccount record, @Param("example") PaymentAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByExample(@Param("record") PaymentAccount record, @Param("example") PaymentAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKeySelective(PaymentAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment_account
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    int updateByPrimaryKey(PaymentAccount record);
}