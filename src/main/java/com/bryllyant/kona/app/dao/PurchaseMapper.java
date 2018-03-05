package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.PurchaseExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurchaseMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    long countByExample(PurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByExample(PurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insert(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int insertSelective(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    List<Purchase> selectByExample(PurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    Purchase selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExampleSelective(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByExample(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKeySelective(Purchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__purchase
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    int updateByPrimaryKey(Purchase record);
}