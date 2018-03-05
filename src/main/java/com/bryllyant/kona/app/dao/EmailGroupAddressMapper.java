package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddressExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailGroupAddressMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    long countByExample(EmailGroupAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByExample(EmailGroupAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insert(EmailGroupAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insertSelective(EmailGroupAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    List<EmailGroupAddress> selectByExample(EmailGroupAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    EmailGroupAddress selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExampleSelective(@Param("record") EmailGroupAddress record, @Param("example") EmailGroupAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExample(@Param("record") EmailGroupAddress record, @Param("example") EmailGroupAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKeySelective(EmailGroupAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_group_address
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKey(EmailGroupAddress record);
}