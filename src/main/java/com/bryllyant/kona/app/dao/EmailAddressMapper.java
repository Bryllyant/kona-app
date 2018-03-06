package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAddressExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailAddressMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<EmailAddress, EmailAddressExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<EmailAddress> selectByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    EmailAddress selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") EmailAddress record, @Param("example") EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") EmailAddress record, @Param("example") EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(EmailAddress record);

    List<EmailAddress> fetchRandom(
        @Param("count") Long count, 
        @Param("sourceList") List<String> sourceList,
        @Param("excludeGroupIds") List<Long> groupIds
    );
}           
