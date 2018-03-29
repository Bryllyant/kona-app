package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AppWebhook;
import com.bryllyant.kona.app.entity.AppWebhookExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppWebhookMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AppWebhook, AppWebhookExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    long countByExample(AppWebhookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int deleteByExample(AppWebhookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int insert(AppWebhook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int insertSelective(AppWebhook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    List<AppWebhook> selectByExample(AppWebhookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    AppWebhook selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AppWebhook record, @Param("example") AppWebhookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByExample(@Param("record") AppWebhook record, @Param("example") AppWebhookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByPrimaryKeySelective(AppWebhook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_webhook
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    int updateByPrimaryKey(AppWebhook record);
}