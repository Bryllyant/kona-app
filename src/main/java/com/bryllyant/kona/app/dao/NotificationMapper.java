package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.app.entity.NotificationExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotificationMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Notification, NotificationExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<Notification> selectByExampleWithBLOBs(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    Notification selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(Notification record);
}