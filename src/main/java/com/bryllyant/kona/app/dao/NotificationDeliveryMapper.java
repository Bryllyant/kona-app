package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.app.entity.NotificationDeliveryExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotificationDeliveryMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<NotificationDelivery, NotificationDeliveryExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    long countByExample(NotificationDeliveryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int deleteByExample(NotificationDeliveryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int insert(NotificationDelivery record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int insertSelective(NotificationDelivery record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    List<NotificationDelivery> selectByExample(NotificationDeliveryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    NotificationDelivery selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByExampleSelective(@Param("record") NotificationDelivery record, @Param("example") NotificationDeliveryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByExample(@Param("record") NotificationDelivery record, @Param("example") NotificationDeliveryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByPrimaryKeySelective(NotificationDelivery record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Fri Apr 06 16:41:59 EDT 2018
     */
    int updateByPrimaryKey(NotificationDelivery record);
}