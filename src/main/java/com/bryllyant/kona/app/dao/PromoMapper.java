package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.PromoExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromoMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Promo, PromoExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    long countByExample(PromoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByExample(PromoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insert(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insertSelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    List<Promo> selectByExample(PromoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    Promo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Promo record, @Param("example") PromoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExample(@Param("record") Promo record, @Param("example") PromoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKeySelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__promo
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKey(Promo record);
}