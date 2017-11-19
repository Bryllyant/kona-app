package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.PlaceExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaceMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    long countByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int deleteByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int insert(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int insertSelective(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    List<Place> selectByExampleWithBLOBs(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    List<Place> selectByExample(PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    Place selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByExampleSelective(@Param("record") Place record, @Param("example") PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Place record, @Param("example") PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByExample(@Param("record") Place record, @Param("example") PlaceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByPrimaryKeySelective(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByPrimaryKeyWithBLOBs(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    int updateByPrimaryKey(Place record);

    int updateCoords(Long objectId);

    List<Place> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
}           
