/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.CommerceService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.SalesLeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Sales Controller.
 */
public class SalesController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SalesController.class);
    
    /*
    */
    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PromoService promoService;

    @Autowired
    private CommerceService commerceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;


    
    protected Map<String,Object> toMap(ProductSku productSku) {
        if (productSku == null) return null;
        
        Map<String,Object> result = new HashMap<>();
        result.put("uid", productSku.getUid());
        result.put("sku", productSku.getSku());
        result.put("name", productSku.getName());
        result.put("display_order", productSku.getDisplayOrder());
        result.put("description", productSku.getDescription());
        result.put("price", productSku.getPrice());
        result.put("setup_fee", productSku.getSetupFee());
        result.put("subscription", productSku.isSubscription());
        result.put("subscription_days", productSku.getSubscriptionDays());

        return result;
    }


    
    protected List<Map<String,Object>> toProductSkuMapList(List<ProductSku> productSkus) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        
        for (ProductSku productSku : productSkus) {
            mapList.add(toMap(productSku));
        }
        
        return mapList;
    }


    
    protected Map<String,Object> toMap(Payment payment) {
        if (payment == null) return null;
        
        String invoiceNo = null;

        Invoice invoice = invoiceService.fetchById(payment.getInvoiceId());

        if (invoice != null) {
            invoiceNo = invoice.getInvoiceNo();
        }
        
        
        Map<String,Object> result = new HashMap<>();
        result.put("uid", payment.getUid());
        result.put("status", payment.getStatus());
        result.put("invoice_no", invoiceNo);
        result.put("amount", payment.getAmount());
        //result.put("processor_ref", payment.getProcessorRef());
        result.put("payment_date", payment.getPaidDate());
        return result;
    }


    
    protected List<Map<String,Object>> toPaymentMapList(List<Payment> payments) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        
        for (Payment payment : payments) {
            mapList.add(toMap(payment));
        }
        
        return mapList;
    }

}
