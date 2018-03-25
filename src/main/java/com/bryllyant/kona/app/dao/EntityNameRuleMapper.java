package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EntityNameRule;
import com.bryllyant.kona.app.entity.EntityNameRuleExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntityNameRuleMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<EntityNameRule, EntityNameRuleExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(EntityNameRuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(EntityNameRuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(EntityNameRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(EntityNameRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<EntityNameRule> selectByExample(EntityNameRuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    EntityNameRule selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") EntityNameRule record, @Param("example") EntityNameRuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") EntityNameRule record, @Param("example") EntityNameRuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(EntityNameRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__entity_name_rule
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(EntityNameRule record);
}