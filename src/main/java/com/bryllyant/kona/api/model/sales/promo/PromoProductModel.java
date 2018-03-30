package com.bryllyant.kona.api.model.sales.promo;

import com.bryllyant.kona.api.model.sales.product.ProductCategoryModel;
import com.bryllyant.kona.api.model.sales.product.ProductModel;
import com.bryllyant.kona.api.model.sales.product.ProductSkuModel;
import com.bryllyant.kona.api.model.sales.product.ProductCategoryModel;
import com.bryllyant.kona.api.model.sales.product.ProductModel;
import com.bryllyant.kona.api.model.sales.product.ProductSkuModel;
import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PromoProductModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private PromoModel promo;
    private ProductModel product;
    private ProductCategoryModel productCategory;
    private ProductSkuModel productSku;
    private Date createdDate;
    private Date updatedDate;

    public static PromoProductModel from(PromoProduct pp) {
        PromoProductModel model = new PromoProductModel();
        model.setUid(pp.getUid());
        return model;
    }

    public static PromoProductModel create(String uid) {
        PromoProductModel model = new PromoProductModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public PromoModel getPromo() {
        return promo;
    }

    public void setPromo(PromoModel promo) {
        this.set("promo", promo);
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.set("product", product);
    }

    public ProductCategoryModel getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryModel productCategory) {
        this.set("productCategory", productCategory);
    }

    public ProductSkuModel getProductSku() {
        return productSku;
    }

    public void setProductSku(ProductSkuModel productSku) {
        this.set("productSku", productSku);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
