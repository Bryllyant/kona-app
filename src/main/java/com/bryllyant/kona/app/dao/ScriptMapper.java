package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.entity.ScriptExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Script, ScriptExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    long countByExample(ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByExample(ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insert(Script record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insertSelective(Script record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<Script> selectByExampleWithBLOBs(ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<Script> selectByExample(ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    Script selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Script record, @Param("example") ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Script record, @Param("example") ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExample(@Param("record") Script record, @Param("example") ScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeySelective(Script record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(Script record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__script
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKey(Script record);
}