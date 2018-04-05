/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface InvoiceItemService extends KService, KEntityService<InvoiceItem> {
	String SERVICE_PATH = "rpc/InvoiceItemService";

    List<InvoiceItem> fetchByInvoiceId(Long invoiceId);

    List<InvoiceItem> getInvoiceItemList(Invoice invoice);

    InvoiceItem createInvoiceItem(Invoice invoice, CartItem cartItem);

    void updateInvoiceItem(InvoiceItem item);
	
}
