package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.product.ProductModel;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(ProductModelService.class);
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private AppModelService appModelService;

    @Autowired
    private AppUtil util;



    public Product getProduct(String uid) {
        Product product = productService.fetchByUid(uid);

        if (product == null) {
            throw new NotFoundException("Product not found for uid: " + uid);
        }

        return product;
    }



    public Product getProduct(Long productId) {
        Product product = productService.fetchById(productId);

        if (product == null) {
            throw new NotFoundException("Product not found for id: " + productId);
        }

        return product;
    }
    
    


    public Product getProduct(ProductModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Product not found for model: " + model);
        }

        return getProduct(uid);
    }
   


    public ProductModel toModel(Product product, String... includeKeys) {
        if (product == null) return null;

        ProductModel model = new ProductModel();
        
        model.fromBean(product);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<ProductModel> toModelList(List<Product> products, String... includeKeys) {
        List<ProductModel> modelList = new ArrayList<>();

        for (Product product : products) {
            modelList.add(toModel(product, includeKeys));
        }

        return modelList;
    }



    public Product toEntity(ProductModel model) {
        Product product = new Product();

        return mergeEntity(product, model);
    }

    


    public Product mergeEntity(Product product, ProductModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, product);

        for (String key : model.initializedKeys()) {

            switch (key) {


            }

        }

        return product;
    }
    

   
}
