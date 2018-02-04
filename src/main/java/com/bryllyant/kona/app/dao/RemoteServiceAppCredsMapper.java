package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.RemoteServiceAppCreds;
import com.bryllyant.kona.app.entity.RemoteServiceAppCredsExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemoteServiceAppCredsMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    long countByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insert(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insertSelective(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    List<RemoteServiceAppCreds> selectByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    RemoteServiceAppCreds selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") RemoteServiceAppCreds record, @Param("example") RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExample(@Param("record") RemoteServiceAppCreds record, @Param("example") RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKeySelective(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKey(RemoteServiceAppCreds record);
}