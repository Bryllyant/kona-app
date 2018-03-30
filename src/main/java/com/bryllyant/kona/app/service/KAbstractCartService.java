package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KCurrency;
import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractCartService<
        CART extends KCart,
        CART_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CART, CART_EXAMPLE>,
        CART_ITEM extends KCartItem,
        USER extends KUser,
        TOKEN extends KToken>
        extends KAbstractService<CART,CART_EXAMPLE,MAPPER>
        implements KCartService<CART> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractCartService.class);

    protected abstract CART getNewObject();

    protected abstract <S extends KCartItemService<CART_ITEM,CART>> S getCartItemService();

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KTokenService<TOKEN>> S getTokenService();


    @Override
    public void validate(CART cart) {
        if (cart.getCreatedDate() == null) {
            cart.setCreatedDate(new Date());
        }

        if (cart.getUid() == null) {
            cart.setUid(uuid());
        }

        cart.setUpdatedDate(new Date());
    }

    @Override
    public CART fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CART> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    @Override
    public CART fetchActiveByTokenId(Long tokenId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("tokenId", tokenId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CART fetchActiveByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("userId", userId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public CART getActiveCartByUserId(Long userId) {
        if (userId == null) return null;
        return fetchActiveByUserId(userId);
    }


    @Override
    public CART getCart(Long userId) {
        return getCart(userId, null);
    }


    @Override
    public CART getCart(KServiceClient client) {
        return getCart(null, client);
    }



    @Override
    public CART getCart(Long userId, KServiceClient client) {
        CART cart = null;
        TOKEN token = null;

        if (client != null) {
            Long tokenId = client.getTokenId();

            if (tokenId != null) {
                token = getTokenService().fetchById(tokenId);
            }
        }

        if (userId != null) {
            cart = getActiveCartByUserId(userId);
        }


        if (userId != null && token != null && token.getUserId() != null) {
            if (!token.getUserId().equals(userId)) {
                throw new IllegalStateException("getCart(): userId and token userId mismatch:"
                        + "\nuserId: " + userId
                        + "\ntoken: " + KJsonUtil.toJson(token));
            }
        }

        if (userId == null && token != null) {
            userId = token.getUserId();
        }

        // first see if we have a cart for this token key
        if (cart == null && token != null) {
            cart = fetchActiveByTokenId(token.getId());

            if (cart != null) {
                if (userId != null &&
                        (cart.getUserId() == null || !cart.getUserId().equals(userId))) {
                    expireCart(cart);
                    cart = null;
                }
            }
        }

        // if we don't have a cart, create one
        if (cart == null) {
            cart = createCart(userId, client);
        }

        logger.debug("getCart(): cart:\n" + KJsonUtil.toJson(cart));
        return cart;
    }



    @Override
    public CART createCart(Long userId, KServiceClient client) {
        Long tokenId = null;
        Long accountId = null;

        if (client != null) {
            tokenId = client.getTokenId();
            accountId = client.getAccountId();

            if (userId == null) {
                userId = client.getUserId();
            }

            if (userId != null && client.getUserId() != null && !client.getUserId().equals(userId)) {
                throw new KServiceException("Error: createCart: userId mismatch");
            }
        }

        if (userId != null) {
            USER user = getUserService().fetchById(userId);
            accountId = user.getAccountId();
        }


        CART cart = getNewObject();
        cart.setUserId(userId);
        cart.setAccountId(accountId);
        cart.setCurrencyId(KCurrency.USD.getId());
        cart.setTokenId(tokenId);
        cart.setSubtotal(new BigDecimal(0));
        cart.setDiscount(new BigDecimal(0));
        cart.setShipping(new BigDecimal(0));
        cart.setTax(new BigDecimal(0));
        cart.setTotal(new BigDecimal(0));
        cart.setCheckedOut(false);
        cart.setInvoiced(false);
        cart.setCreatedDate(new Date());

        cart = add(cart);

        return cart;
    }

    @Override
    public void updateCart(CART cart) {
        if (cart == null) return;
        update(cart);
    }

    @Override
    public void expireCart(Long cartId) {
        if (cartId == null) return;
        CART cart = fetchById(cartId);
        expireCart(cart);
    }


    @Override
    public void expireCart(CART cart) {
        if (cart == null) return;
        cart.setExpired(true);
        cart.setExpiredDate(new Date());
        update(cart);
    }

    @Override
    public CART checkout(CART cart) {
        if (cart == null) return null;
        cart.setCheckedOutDate(new Date());
        cart.setCheckedOut(true);
        update(cart);
        return cart;
    }

    @Override
    public CART updateCartTotals(CART cart) {
        BigDecimal subtotal = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);

        List<CART_ITEM> itemList = getCartItemService().getCartItemList(cart);

        for (CART_ITEM item : itemList) {
            if (item.getTotal() != null) {
                total = total.add(item.getTotal());
            }

            if (item.getSubtotal() != null) {
                subtotal = subtotal.add(item.getSubtotal());
            }

            if (item.getDiscount() != null) {
                discount = discount.add(item.getDiscount());
            }

        }

        cart.setSubtotal(subtotal);
        cart.setDiscount(discount);
        cart.setTotal(total);

        update(cart);

        return cart;
    }
}
