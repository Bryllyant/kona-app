package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.app.entity.RemoteServiceExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemoteServiceMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<RemoteService, RemoteServiceExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    long countByExample(RemoteServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int deleteByExample(RemoteServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int insert(RemoteService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int insertSelective(RemoteService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    List<RemoteService> selectByExample(RemoteServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    RemoteService selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int updateByExampleSelective(@Param("record") RemoteService record, @Param("example") RemoteServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int updateByExample(@Param("record") RemoteService record, @Param("example") RemoteServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int updateByPrimaryKeySelective(RemoteService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__remote_service
     *
     * @mbg.generated Thu Mar 08 08:48:59 EST 2018
     */
    int updateByPrimaryKey(RemoteService record);
}