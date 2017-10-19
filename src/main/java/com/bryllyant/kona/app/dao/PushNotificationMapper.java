package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PushNotification;
import com.bryllyant.kona.app.entity.PushNotificationExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PushNotificationMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(PushNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(PushNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(PushNotification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(PushNotification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<PushNotification> selectByExample(PushNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    PushNotification selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") PushNotification record, @Param("example") PushNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") PushNotification record, @Param("example") PushNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(PushNotification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(PushNotification record);
}