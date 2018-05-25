package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.ProductSkuExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSkuMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<ProductSku, ProductSkuExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    long countByExample(ProductSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int deleteByExample(ProductSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int insert(ProductSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int insertSelective(ProductSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    List<ProductSku> selectByExample(ProductSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    ProductSku selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByExampleSelective(@Param("record") ProductSku record, @Param("example") ProductSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByExample(@Param("record") ProductSku record, @Param("example") ProductSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByPrimaryKeySelective(ProductSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__product_sku
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByPrimaryKey(ProductSku record);
}