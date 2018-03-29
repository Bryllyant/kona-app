package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PlacePlan;
import com.bryllyant.kona.app.entity.PlacePlanExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlacePlanMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PlacePlan, PlacePlanExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    long countByExample(PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByExample(PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insert(PlacePlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insertSelective(PlacePlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    List<PlacePlan> selectByExampleWithBLOBs(PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    List<PlacePlan> selectByExample(PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    PlacePlan selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PlacePlan record, @Param("example") PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") PlacePlan record, @Param("example") PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExample(@Param("record") PlacePlan record, @Param("example") PlacePlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKeySelective(PlacePlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(PlacePlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__place_plan
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKey(PlacePlan record);
}