package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.ApiVersionExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiVersionMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    long countByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insert(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insertSelective(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    List<ApiVersion> selectByExample(ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    ApiVersion selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExampleSelective(@Param("record") ApiVersion record, @Param("example") ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExample(@Param("record") ApiVersion record, @Param("example") ApiVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKeySelective(ApiVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__api_version
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKey(ApiVersion record);
}