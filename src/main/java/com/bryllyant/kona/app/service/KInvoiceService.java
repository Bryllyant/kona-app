package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.math.BigDecimal;
import java.util.List;

public interface KInvoiceService<INVOICE extends KInvoice,
        INVOICE_ITEM extends KInvoiceItem,
        CART extends KCart,
        CART_ITEM extends KCartItem>
        extends KService, KEntityService<INVOICE> {

    INVOICE fetchByUid(String uid);

    List<INVOICE> fetchByUserId(Long userId);

    List<INVOICE> fetchAllOpen();

    List<INVOICE> fetchOpenByAccountId(Long accountId);

    INVOICE fetchByInvoiceNo(String invoiceNo);

    INVOICE createInvoice(CART cart);

    INVOICE createInvoice(CART cart, List<CART_ITEM> itemList);

    INVOICE createInvoice(Long accountId, List<INVOICE_ITEM> itemList);

    INVOICE createInvoice(Long accountId, String productSku, String description);

    void updateInvoice(INVOICE invoice);

    void closeInvoice(
            INVOICE invoice,
            boolean paid,
            BigDecimal amount,
            String paymentRef,
            String cardLast4,
            String notes
    );
}
