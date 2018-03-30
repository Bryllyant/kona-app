package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KInvoiceItemService<
        INVOICE_ITEM extends KInvoiceItem,
        INVOICE extends KInvoice,
        CART_ITEM extends KCartItem>
        extends KService, KEntityService<INVOICE_ITEM> {

    INVOICE_ITEM fetchByUid(String uid);

    List<INVOICE_ITEM> fetchByInvoiceId(Long invoiceId);

    List<INVOICE_ITEM> getInvoiceItemList(INVOICE invoice);

    INVOICE_ITEM createInvoiceItem(INVOICE invoice, CART_ITEM cartItem);

    void updateInvoiceItem(INVOICE_ITEM item);

}
