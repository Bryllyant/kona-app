package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.SmsExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Sms, SmsExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    long countByExample(SmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByExample(SmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insert(Sms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insertSelective(Sms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    List<Sms> selectByExample(SmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    Sms selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Sms record, @Param("example") SmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExample(@Param("record") Sms record, @Param("example") SmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKeySelective(Sms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__sms
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKey(Sms record);
}