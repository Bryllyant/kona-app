package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.AppCredsExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppCredsMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<AppCreds, AppCredsExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    long countByExample(AppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int deleteByExample(AppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int insert(AppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int insertSelective(AppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    List<AppCreds> selectByExample(AppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    AppCreds selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByExampleSelective(@Param("record") AppCreds record, @Param("example") AppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByExample(@Param("record") AppCreds record, @Param("example") AppCredsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByPrimaryKeySelective(AppCreds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_creds
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByPrimaryKey(AppCreds record);
}