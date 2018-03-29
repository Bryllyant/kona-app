package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.app.entity.ApiLogExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiLogMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<ApiLog, ApiLogExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    long countByExample(ApiLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int deleteByExample(ApiLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int insert(ApiLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int insertSelective(ApiLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    List<ApiLog> selectByExample(ApiLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    ApiLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByExampleSelective(@Param("record") ApiLog record, @Param("example") ApiLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByExample(@Param("record") ApiLog record, @Param("example") ApiLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByPrimaryKeySelective(ApiLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_log
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByPrimaryKey(ApiLog record);

    int updateCoords(Long objectId);

    List<ApiLog> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
