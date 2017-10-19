package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.RemoteServiceAppCreds;
import com.bryllyant.kona.app.entity.RemoteServiceAppCredsExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RemoteServiceAppCredsMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<RemoteServiceAppCreds> selectByExample(RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    RemoteServiceAppCreds selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") RemoteServiceAppCreds record, @Param("example") RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") RemoteServiceAppCreds record, @Param("example") RemoteServiceAppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(RemoteServiceAppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_app_creds
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(RemoteServiceAppCreds record);
}