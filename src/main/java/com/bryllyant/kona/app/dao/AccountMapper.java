package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.AccountExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Account, AccountExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    long countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    Account selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__account
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByPrimaryKey(Account record);
}