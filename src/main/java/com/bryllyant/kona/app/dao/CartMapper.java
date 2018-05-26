package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Cart, CartExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    long countByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int insertSelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    List<Cart> selectByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    Cart selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__cart
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    int updateByPrimaryKey(Cart record);
}