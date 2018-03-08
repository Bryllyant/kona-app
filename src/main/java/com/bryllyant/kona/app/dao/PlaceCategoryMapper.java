package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PlaceCategory;
import com.bryllyant.kona.app.entity.PlaceCategoryExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaceCategoryMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PlaceCategory, PlaceCategoryExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    long countByExample(PlaceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int deleteByExample(PlaceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int insert(PlaceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int insertSelective(PlaceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    List<PlaceCategory> selectByExample(PlaceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    PlaceCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int updateByExampleSelective(@Param("record") PlaceCategory record, @Param("example") PlaceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int updateByExample(@Param("record") PlaceCategory record, @Param("example") PlaceCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int updateByPrimaryKeySelective(PlaceCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_category
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    int updateByPrimaryKey(PlaceCategory record);
}