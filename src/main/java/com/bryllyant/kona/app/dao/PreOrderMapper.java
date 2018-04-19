package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.PreOrder;
import com.bryllyant.kona.app.entity.PreOrderExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreOrderMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<PreOrder, PreOrderExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    long countByExample(PreOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int deleteByExample(PreOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int insert(PreOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int insertSelective(PreOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    List<PreOrder> selectByExample(PreOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    PreOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int updateByExampleSelective(@Param("record") PreOrder record, @Param("example") PreOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int updateByExample(@Param("record") PreOrder record, @Param("example") PreOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int updateByPrimaryKeySelective(PreOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__pre_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    int updateByPrimaryKey(PreOrder record);
}