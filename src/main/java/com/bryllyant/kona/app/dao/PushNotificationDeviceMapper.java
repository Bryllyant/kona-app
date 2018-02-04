package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationDeviceExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushNotificationDeviceMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    long countByExample(PushNotificationDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByExample(PushNotificationDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insert(PushNotificationDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insertSelective(PushNotificationDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    List<PushNotificationDevice> selectByExample(PushNotificationDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    PushNotificationDevice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") PushNotificationDevice record, @Param("example") PushNotificationDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExample(@Param("record") PushNotificationDevice record, @Param("example") PushNotificationDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKeySelective(PushNotificationDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKey(PushNotificationDevice record);

    List<PushNotificationDevice> fetchByUserIds(
            @Param("userIds") List<Long> userIdList,
            @Param("sandbox") boolean sandbox
    );
}           
