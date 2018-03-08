package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.MediaExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MediaMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Media, MediaExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    long countByExample(MediaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByExample(MediaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insert(Media record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insertSelective(Media record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    List<Media> selectByExample(MediaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    Media selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Media record, @Param("example") MediaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExample(@Param("record") Media record, @Param("example") MediaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKeySelective(Media record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKey(Media record);

    int updateCoords(Long objectId);

    List<Media> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
