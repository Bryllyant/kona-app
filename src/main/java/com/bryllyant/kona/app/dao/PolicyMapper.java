package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.app.entity.PolicyExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PolicyMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Policy, PolicyExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<Policy> selectByExampleWithBLOBs(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<Policy> selectByExample(PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    Policy selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") Policy record, @Param("example") PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Policy record, @Param("example") PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") Policy record, @Param("example") PolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeyWithBLOBs(Policy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__policy
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(Policy record);
}