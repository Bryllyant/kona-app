package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.ProductExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Product, ProductExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    long countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    Product selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    int updateByPrimaryKey(Product record);
}