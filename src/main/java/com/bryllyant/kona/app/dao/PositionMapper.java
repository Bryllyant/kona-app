package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.PositionExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositionMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Position, PositionExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    long countByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insert(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int insertSelective(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    List<Position> selectByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    Position selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Position record, @Param("example") PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByExample(@Param("record") Position record, @Param("example") PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKeySelective(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__position
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    int updateByPrimaryKey(Position record);

    int updateCoords(Long objectId);

    List<Position> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
