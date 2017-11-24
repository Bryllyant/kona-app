package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AppLegal;
import com.bryllyant.kona.app.entity.AppLegalExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppLegalMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    long countByExample(AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByExample(AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insert(AppLegal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insertSelective(AppLegal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    List<AppLegal> selectByExampleWithBLOBs(AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    List<AppLegal> selectByExample(AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    AppLegal selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExampleSelective(@Param("record") AppLegal record, @Param("example") AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") AppLegal record, @Param("example") AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExample(@Param("record") AppLegal record, @Param("example") AppLegalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKeySelective(AppLegal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKeyWithBLOBs(AppLegal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__app_legal
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKey(AppLegal record);
}