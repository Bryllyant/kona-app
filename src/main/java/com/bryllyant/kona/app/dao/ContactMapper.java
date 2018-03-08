package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.app.entity.ContactExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Contact, ContactExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    long countByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    List<Contact> selectByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    Contact selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExample(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__contact
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKey(Contact record);
}