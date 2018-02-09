package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    long countByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int deleteByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int insert(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int insertSelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    List<Payment> selectByExampleWithBLOBs(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    List<Payment> selectByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    Payment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByExampleSelective(@Param("record") Payment record, @Param("example") PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Payment record, @Param("example") PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByExample(@Param("record") Payment record, @Param("example") PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByPrimaryKeySelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByPrimaryKeyWithBLOBs(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__payment
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByPrimaryKey(Payment record);

    int updateCoords(Long objectId);

    List<Payment> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
