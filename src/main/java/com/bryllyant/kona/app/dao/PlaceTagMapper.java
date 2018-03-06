package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PlaceTag;
import com.bryllyant.kona.app.entity.PlaceTagExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaceTagMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PlaceTag, PlaceTagExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(PlaceTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(PlaceTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(PlaceTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(PlaceTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<PlaceTag> selectByExample(PlaceTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    PlaceTag selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") PlaceTag record, @Param("example") PlaceTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") PlaceTag record, @Param("example") PlaceTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(PlaceTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_tag
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(PlaceTag record);
}