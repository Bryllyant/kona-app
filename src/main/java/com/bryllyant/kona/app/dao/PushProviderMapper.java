package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.PushProviderExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushProviderMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PushProvider, PushProviderExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    long countByExample(PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int deleteByExample(PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int insert(PushProvider record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int insertSelective(PushProvider record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    List<PushProvider> selectByExampleWithBLOBs(PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    List<PushProvider> selectByExample(PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    PushProvider selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PushProvider record, @Param("example") PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") PushProvider record, @Param("example") PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByExample(@Param("record") PushProvider record, @Param("example") PushProviderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByPrimaryKeySelective(PushProvider record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(PushProvider record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_provider
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    int updateByPrimaryKey(PushProvider record);
}