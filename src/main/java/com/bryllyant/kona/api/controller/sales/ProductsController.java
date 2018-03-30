/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.controller.sales;

import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Sales Controller.
 */
@RestController
@RequestMapping("/api/sales/products")
public class ProductsController extends SalesController {
	private static Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private ProductSkuService productSkuService;
	
    @Autowired
    private SystemService system;
	

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Map<String,Object>>> fetchProducts(HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /sales/products");

        logger.debug("ProductsController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        if (filter == null) {
            filter = new HashMap<>();
        }
        
        filter.put("appId", system.getSystemApp().getId());

        logger.debug("ProductsController: filter: " + KJsonUtil.toJson(filter, 1000));
        

        //String[] sortOrder = { "displayOrder" };
        String[] sortOrder = null;

        boolean distinct = false;
        
        if (offset == null) {
            offset = 0;
        }
        
        if (limit == null) {
            limit = 99999;
        }

        return ok(toProductSkuMapList(
                productSkuService.fetchByCriteria(offset, limit, sortOrder, filter, distinct)));
    }
}
