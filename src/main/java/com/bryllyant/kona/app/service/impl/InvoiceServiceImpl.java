/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.InvoiceMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceExample;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Currency;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.sequence.flake.KFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(InvoiceService.SERVICE_PATH)
public class InvoiceServiceImpl 
		extends KAbstractService<Invoice,InvoiceExample,InvoiceMapper>
		implements InvoiceService {
	
	private static Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    
    @Autowired
    private InvoiceMapper invoiceMapper;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private ProductSkuService productSkuService;
    
    @Autowired
    private AccountService accountService;
    

    @Override @SuppressWarnings("unchecked")
    protected InvoiceMapper getMapper() {
        return invoiceMapper;
    }
    
    @Override
    public void validate(Invoice invoice) {
        if (invoice.getCreatedDate() == null) {
            invoice.setCreatedDate(new Date());
        }

        if (invoice.getUid() == null) {
            invoice.setUid(uuid());
        }

        invoice.setUpdatedDate(new Date());
    }

    @Override
    public Invoice fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<Invoice> fetchByUserId(Long userId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<Invoice> fetchAllOpen() {
        return fetchOpenByAccountId(null);
    }

    @Override
    public List<Invoice> fetchOpenByAccountId(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("closed", false);

        if (accountId != null) {
            filter.put("accountId", accountId);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public Invoice fetchByInvoiceNo(String invoiceNo) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("invoiceNo", invoiceNo);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Invoice createInvoice(Cart cart, List<CartItem> itemList) {
        Date now = new Date();
        //String invoiceNo = sequence.getHexNo("invoiceNo", 9);
        String invoiceNo = KFlake.getId();

        Invoice invoice = new Invoice();
        invoice.setUserId(cart.getUserId());
        invoice.setAccountId(cart.getAccountId());
        invoice.setCurrencyId(cart.getCurrencyId());
        invoice.setInvoiceNo(invoiceNo);
        invoice.setSubtotal(cart.getSubtotal());
        invoice.setTax(cart.getTax());
        invoice.setShipping(cart.getShipping());
        invoice.setDiscount(cart.getDiscount());
        invoice.setTotal(cart.getTotal());
        invoice.setAmountDue(cart.getTotal());
        invoice.setPaid(false);
        invoice.setClosed(false);
        invoice.setPaidDate(null);
        invoice.setDueDate(now);
        invoice.setInvoiceDate(now);
        invoice.setCreatedDate(now);

        invoice = add(invoice);
        Long invoiceId = invoice.getId();

        for (CartItem cartItem : itemList) {
            // sanity check
            if (!cart.getId().equals(cartItem.getCartId())) {
                throw new IllegalStateException("Invalid CartItem cartId");
            }

            InvoiceItem item = new InvoiceItem();
            item.setInvoiceId(invoiceId);
            item.setProductSkuId(cartItem.getProductSkuId());
            item.setPromoId(cartItem.getPromoId());
            item.setDescription(cartItem.getDescription());
            item.setDiscountDescription(cartItem.getDiscountDescription());
            item.setQuantity(cartItem.getQuantity());
            item.setUnitPrice(cartItem.getUnitPrice());
            item.setSetupFee(cartItem.getSetupFee());
            item.setSubtotal(cartItem.getSubtotal());
            item.setDiscount(cartItem.getDiscount());
            item.setTotal(cartItem.getTotal());
            item.setCreatedDate(now);
            item.setSubscriptionStartDate(cartItem.getSubscriptionStartDate());
            item.setSubscriptionEndDate(cartItem.getSubscriptionEndDate());
            item = invoiceItemService.add(item);
        }

        return invoice;
    }



    @Override
    public Invoice createInvoice(Long accountId, List<InvoiceItem> itemList) {
        Date now = new Date();
        //String invoiceNo = sequence.getHexNo("invoiceNo", 9);
        String invoiceNo = KFlake.getId();

        Account account = accountService.fetchById(accountId);

        BigDecimal zero = new BigDecimal(0);

        // quick sanity check before creating invoice
        for (InvoiceItem item : itemList) {
            if (item.getId() != null || item.getInvoiceId() != null) {
                throw new IllegalArgumentException("Invalid InvoiceItem object: id and/or invoiceId are already defined");
            }
        }

        // first create an empty invoice
        Invoice invoice = new Invoice();
        invoice.setUserId(account.getOwnerId());
        invoice.setAccountId(account.getId());
        invoice.setCurrencyId(Currency.USD.getId());
        invoice.setInvoiceNo(invoiceNo);
        invoice.setSubtotal(zero);
        invoice.setTax(zero);
        invoice.setShipping(zero);
        invoice.setDiscount(zero);
        invoice.setTotal(zero);
        invoice.setAmountDue(zero);
        invoice.setNotes(null);
        invoice.setPaid(false);
        invoice.setClosed(false);
        invoice.setPaidDate(null);
        invoice.setDueDate(now);
        invoice.setInvoiceDate(now);
        invoice.setCreatedDate(now);

        invoice = add(invoice);
        Long invoiceId = invoice.getId();

        BigDecimal total = new BigDecimal(0);

        for (InvoiceItem item : itemList) {
            item.setInvoiceId(invoiceId);
            item.setCreatedDate(now);

            item = invoiceItemService.add(item);

            total = total.add(item.getTotal());
        }

        invoice.setSubtotal(total);
        invoice.setTotal(total);
        invoice.setAmountDue(total);
        invoice = update(invoice);

        return invoice;
    }



    @Override
    public Invoice createInvoice(Long accountId, String sku, String description) {

        ProductSku productSku = productSkuService.fetchBySku(sku);

        if (productSku == null) {
            throw new IllegalArgumentException("Invalid product sku: " + sku);
        }

        if (description == null) {
            description = productSku.getDescription();
        }

        List<InvoiceItem> items = new ArrayList<>();
        BigDecimal zero = new BigDecimal(0);

        InvoiceItem item = new InvoiceItem();
        item.setProductSkuId(productSku.getId());
        item.setQuantity(1);
        item.setUnitPrice(productSku.getPrice());
        item.setSubtotal(productSku.getPrice());
        item.setDiscount(zero);
        item.setSetupFee(zero);
        item.setTotal(productSku.getPrice());
        item.setDescription(description);
        item.setCreatedDate(new Date());

        items.add(item);

        Invoice invoice = createInvoice(accountId, items);

        return invoice;
    }



    @Override
    public void updateInvoice(Invoice invoice) {
        if (invoice == null) return;
        update(invoice);
    }



    @Override
    public Invoice createInvoice(Cart cart) {
        List<CartItem> itemList = cartItemService.getCartItemList(cart);
        Invoice invoice = createInvoice(cart, itemList);

        // check out the cart before creating an invoice
        if (!cart.isCheckedOut()) {
            cart.setCheckedOutDate(new Date());
            cart.setCheckedOut(true);
        }
        cart.setInvoiceId(invoice.getId());
        cart.setInvoiced(true);
        cart.setInvoicedDate(new Date());
        cartService.update(cart);

        return invoice;
    }



    @Override
    public void closeInvoice(
            Invoice invoice,
            boolean paid,
            BigDecimal amount,
            String paymentRef,
            String cardLast4,
            String notes
    ) {
        Date now = new Date();
        Date paidDate = null;
        if (paid) paidDate = now;

        invoice.setClosed(true);
        invoice.setClosedDate(now);
        invoice.setPaymentRef(paymentRef);
        invoice.setPaymentCardLast4(cardLast4);
        invoice.setPaid(paid);
        invoice.setPaidDate(paidDate);
        invoice.setAmountPaid(amount);

        String currentNotes = invoice.getNotes();
        if (currentNotes != null) {
            currentNotes += "\n" + notes;
        } else {
            currentNotes = notes;
        }

        invoice.setNotes(notes);

        update(invoice);
    }
}
