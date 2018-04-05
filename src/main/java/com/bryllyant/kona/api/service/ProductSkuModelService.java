package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.sales.product.ProductModel;
import com.bryllyant.kona.api.model.sales.product.ProductSkuModel;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSkuModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(ProductSkuModelService.class);

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private AppUtil util;


    public ProductSku getProductSku(String uid) {
        ProductSku productSku = productSkuService.fetchByUid(uid);

        if (productSku == null) {
            throw new NotFoundException("ProductSku not found for uid: " + uid);
        }

        return productSku;
    }

    public ProductSku getProductSku(Long productSkuId) {
        ProductSku productSku = productSkuService.fetchById(productSkuId);

        if (productSku == null) {
            throw new NotFoundException("ProductSku not found for id: " + productSkuId);
        }

        return productSku;
    }


    public ProductSku getProductSku(ProductSkuModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("ProductSku not found for model: " + model);
        }

        return getProductSku(uid);
    }


    public ProductSkuModel toModel(ProductSku productSku, String... includeKeys) {
        if (productSku == null) return null;

        ProductSkuModel model = new ProductSkuModel();

        model.fromBean(productSku);

        if (productSku.getProductId() != null) {
            Product product = productModelService.getProduct(productSku.getProductId());
            model.setProduct(ProductModel.create(product.getUid()));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    public List<ProductSkuModel> toModelList(List<ProductSku> productSkus, String... includeKeys) {
        List<ProductSkuModel> modelList = new ArrayList<>();

        for (ProductSku productSku : productSkus) {
            modelList.add(toModel(productSku, includeKeys));
        }

        return modelList;
    }


    public ProductSku toEntity(ProductSkuModel model) {
        ProductSku productSku = new ProductSku();

        return mergeEntity(productSku, model);
    }


    public ProductSku mergeEntity(ProductSku productSku, ProductSkuModel model) {
        logger.debug("toEntity called for model: " + model);

        util.copyModelToObject(model, productSku);

        for (String key : model.initializedKeys()) {

            switch (key) {
                case "product":
                    Product product = productModelService.getProduct(model.getProduct());
                    productSku.setProductId(product.getId());
                    break;
            }
        }

        return productSku;
    }
    

   
}
