/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.sales;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.KPaymentStatus;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.Product;
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

    // ----------------------------------------------------------------------
    
    protected final Map<String,Object> toMap(Product product) {
        if (product == null) return null;
        
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("uid", product.getUid());
        result.put("slug", product.getSlug());
        result.put("name", product.getName());
        result.put("display_order", product.getDisplayOrder());
        result.put("description", product.getDescription());
        result.put("price", product.getPrice());
        result.put("setup_fee", product.getSetupFee());
        result.put("subscription", product.isSubscription());
        result.put("subscription_days", product.getSubscriptionDays());

        return result;
    }

    // ----------------------------------------------------------------------
    
    protected final List<Map<String,Object>> toProductMapList(List<Product> products) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        
        for (Product product : products) {
            mapList.add(toMap(product));
        }
        
        return mapList;
    }

  // ----------------------------------------------------------------------
    
    protected final Map<String,Object> toMap(Payment payment) {
        if (payment == null) return null;
        
        KPaymentStatus status = KPaymentStatus.getInstance(payment.getStatusId());

        String invoiceNo = null;

        Invoice invoice = invoiceService.fetchById(payment.getInvoiceId());

        if (invoice != null) {
            invoiceNo = invoice.getInvoiceNo();
        }
        
        
        Map<String,Object> result = new HashMap<>();
        result.put("uid", payment.getUid());
        result.put("status", status.name().toLowerCase());
        result.put("invoice_no", invoiceNo);
        result.put("amount", payment.getAmount());
        //result.put("processor_ref", payment.getProcessorRef());
        result.put("payment_date", payment.getPaidDate());
        return result;
    }

    // ----------------------------------------------------------------------
    
    protected final List<Map<String,Object>> toPaymentMapList(List<Payment> payments) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        
        for (Payment payment : payments) {
            mapList.add(toMap(payment));
        }
        
        return mapList;
    }

}
