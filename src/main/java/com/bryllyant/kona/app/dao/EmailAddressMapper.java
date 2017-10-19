package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAddressExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmailAddressMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<EmailAddress> selectByExample(EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    EmailAddress selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") EmailAddress record, @Param("example") EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") EmailAddress record, @Param("example") EmailAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(EmailAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_address
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(EmailAddress record);

    List<EmailAddress> fetchRandom(
        @Param("count") Long count, 
        @Param("sourceList") List<String> sourceList,
        @Param("excludeGroupIds") List<Long> groupIds
    );
}           
