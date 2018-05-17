package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.ApiVersionExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiVersionMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<ApiVersion, ApiVersionExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    long countByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insert(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int insertSelective(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    List<ApiVersion> selectByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    ApiVersion selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") ApiVersion record, @Param("example") ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByExample(@Param("record") ApiVersion record, @Param("example") ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKeySelective(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    int updateByPrimaryKey(ApiVersion record);
}