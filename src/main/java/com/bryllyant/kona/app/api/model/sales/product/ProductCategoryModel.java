package com.bryllyant.kona.app.api.model.sales.product;

import com.bryllyant.kona.app.entity.ProductCategory;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class ProductCategoryModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private ProductCategoryModel parent;
    private String name;
    private String slug;
    private String description;
    private Integer displayOrder;
    private Date createdDate;
    private Date updatedDate;

    public static ProductCategoryModel from(ProductCategory category) {
        ProductCategoryModel model = new ProductCategoryModel();
        model.setUid(category.getUid());
        model.setName(category.getName());
        return model;
    }

    public static ProductCategoryModel create(String uid) {
        ProductCategoryModel model = new ProductCategoryModel();
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

    public ProductCategoryModel getParent() {
        return parent;
    }

    public void setParent(ProductCategoryModel parent) {
        this.set("parent", parent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.set("displayOrder", displayOrder);
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
