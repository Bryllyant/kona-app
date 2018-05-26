package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.PromoCodeExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromoCodeMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PromoCode, PromoCodeExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(PromoCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(PromoCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(PromoCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(PromoCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<PromoCode> selectByExample(PromoCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    PromoCode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PromoCode record, @Param("example") PromoCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") PromoCode record, @Param("example") PromoCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(PromoCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo_code
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(PromoCode record);
}