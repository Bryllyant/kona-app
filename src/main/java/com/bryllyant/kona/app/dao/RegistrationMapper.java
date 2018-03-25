package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.RegistrationExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegistrationMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Registration, RegistrationExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(RegistrationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(RegistrationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(Registration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(Registration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<Registration> selectByExample(RegistrationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    Registration selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Registration record, @Param("example") RegistrationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") Registration record, @Param("example") RegistrationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(Registration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(Registration record);

    int updateCoords(Long objectId);

    List<Registration> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
