package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractInvoiceItemService<
        INVOICE_ITEM extends KInvoiceItem,
        INVOICE_ITEM_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<INVOICE_ITEM, INVOICE_ITEM_EXAMPLE>,
        INVOICE extends KInvoice,
        CART_ITEM extends KCartItem>
        extends KAbstractService<INVOICE_ITEM,INVOICE_ITEM_EXAMPLE,MAPPER>
        implements KInvoiceItemService<INVOICE_ITEM,INVOICE,CART_ITEM> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractInvoiceItemService.class);


    protected abstract INVOICE_ITEM getNewObject();

    @Override
    public void validate(INVOICE_ITEM invoiceItem) {
        if (invoiceItem.getCreatedDate() == null) {
            invoiceItem.setCreatedDate(new Date());
        }

        if (invoiceItem.getUid() == null) {
            invoiceItem.setUid(uuid());
        }

        invoiceItem.setUpdatedDate(new Date());
    }

    @Override
    public INVOICE_ITEM fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<INVOICE_ITEM> fetchByInvoiceId(Long invoiceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
        return fetchByCriteria(filter);
    }


    @Override
    public INVOICE_ITEM createInvoiceItem(INVOICE invoice, CART_ITEM cartItem) {
        INVOICE_ITEM item = getNewObject();
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
    public List<INVOICE_ITEM> getInvoiceItemList(INVOICE invoice) {
        if (invoice == null || invoice.getId() == null) return null;
        return fetchByInvoiceId(invoice.getId());
    }


    @Override
    public void updateInvoiceItem(INVOICE_ITEM item) {
        if (item == null) return;
        update(item);
    }
}
