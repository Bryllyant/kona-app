package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.CartItemExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartItemMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<CartItem, CartItemExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    long countByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int deleteByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int insert(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int insertSelective(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    List<CartItem> selectByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    CartItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByExampleSelective(@Param("record") CartItem record, @Param("example") CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByExample(@Param("record") CartItem record, @Param("example") CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByPrimaryKeySelective(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart_item
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    int updateByPrimaryKey(CartItem record);
}