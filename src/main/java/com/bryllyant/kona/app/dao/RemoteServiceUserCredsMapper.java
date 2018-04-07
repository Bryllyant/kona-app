package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.RemoteServiceUserCreds;
import com.bryllyant.kona.app.entity.RemoteServiceUserCredsExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemoteServiceUserCredsMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<RemoteServiceUserCreds, RemoteServiceUserCredsExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    long countByExample(RemoteServiceUserCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int deleteByExample(RemoteServiceUserCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int insert(RemoteServiceUserCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int insertSelective(RemoteServiceUserCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    List<RemoteServiceUserCreds> selectByExample(RemoteServiceUserCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    RemoteServiceUserCreds selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int updateByExampleSelective(@Param("record") RemoteServiceUserCreds record, @Param("example") RemoteServiceUserCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int updateByExample(@Param("record") RemoteServiceUserCreds record, @Param("example") RemoteServiceUserCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int updateByPrimaryKeySelective(RemoteServiceUserCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Fri Apr 06 16:42:00 EDT 2018
     */
    int updateByPrimaryKey(RemoteServiceUserCreds record);
}