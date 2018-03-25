package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.PlaceExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaceMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Place, PlaceExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<Place> selectByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    Place selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Place record, @Param("example") PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") Place record, @Param("example") PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(Place record);

    int updateCoords(Long objectId);

    List<Place> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
