/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.InvoiceItemMapper;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.InvoiceItemExample;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(InvoiceItemService.SERVICE_PATH)
public class InvoiceItemServiceImpl 
		extends KAbstractService<InvoiceItem,InvoiceItemExample,InvoiceItemMapper>
		implements InvoiceItemService {
	
	private static Logger logger = LoggerFactory.getLogger(InvoiceItemServiceImpl.class);
    
    @Autowired
    private InvoiceItemMapper invoiceItemMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected InvoiceItemMapper getMapper() {
        return invoiceItemMapper;
    }
    

    @Override
    public void validate(InvoiceItem invoiceItem) {
        if (invoiceItem.getCreatedDate() == null) {
            invoiceItem.setCreatedDate(new Date());
        }

        if (invoiceItem.getUid() == null) {
            invoiceItem.setUid(uuid());
        }

        invoiceItem.setUpdatedDate(new Date());
    }


    @Override
    public List<InvoiceItem> fetchByInvoiceId(Long invoiceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
        return fetchByCriteria(filter);
    }


    @Override
    public InvoiceItem createInvoiceItem(Invoice invoice, CartItem cartItem) {
        InvoiceItem item = new InvoiceItem();
        item.setInvoiceId(invoice.getId());
        item.setProductSkuId(cartItem.getProductSkuId());
        item.setDescription(cartItem.getDescription());
        item.setDiscountDescription(cartItem.getDiscountDescription());
        item.setQuantity(cartItem.getQuantity());
        item.setSubtotal(cartItem.getSubtotal());
        item.setTotal(cartItem.getTotal());
        item.setCreatedDate(invoice.getCreatedDate());
        item.setSubscriptionStartDate(cartItem.getSubscriptionStartDate());
        item.setSubscriptionEndDate(cartItem.getSubscriptionEndDate());
        item = add(item);
        return item;
    }

    @Override
    public List<InvoiceItem> getInvoiceItemList(Invoice invoice) {
        if (invoice == null || invoice.getId() == null) return null;
        return fetchByInvoiceId(invoice.getId());
    }


    @Override
    public void updateInvoiceItem(InvoiceItem item) {
        if (item == null) return;
        update(item);
    }
}
