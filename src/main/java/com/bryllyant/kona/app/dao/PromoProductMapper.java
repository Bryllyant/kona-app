package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.app.entity.PromoProductExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromoProductMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PromoProduct, PromoProductExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(PromoProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(PromoProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(PromoProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(PromoProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<PromoProduct> selectByExample(PromoProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    PromoProduct selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PromoProduct record, @Param("example") PromoProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") PromoProduct record, @Param("example") PromoProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(PromoProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_product
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(PromoProduct record);
}