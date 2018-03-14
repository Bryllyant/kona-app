package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.ProductCategory;
import com.bryllyant.kona.app.entity.ProductCategoryExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCategoryMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<ProductCategory, ProductCategoryExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    long countByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insert(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insertSelective(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    List<ProductCategory> selectByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    ProductCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKeySelective(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_category
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKey(ProductCategory record);
}