/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService extends KService, KEntityService<Invoice> {
	String SERVICE_PATH = "rpc/InvoiceService";

    List<Invoice> fetchByUserId(Long userId);

    List<Invoice> fetchAllOpen();

    List<Invoice> fetchOpenByAccountId(Long accountId);

    Invoice fetchByInvoiceNo(String invoiceNo);

    Invoice createInvoice(Cart cart);

    Invoice createInvoice(Cart cart, List<CartItem> itemList);

    Invoice createInvoice(Long accountId, List<InvoiceItem> itemList);

    Invoice createInvoice(Long accountId, String productSku, String description);

    void updateInvoice(Invoice invoice);

    void closeInvoice(
            Invoice invoice,
            boolean paid,
            BigDecimal amount,
            String paymentRef,
            String cardLast4,
            String notes
    );
	
}
