package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPaymentAccountService<
        PAYMENT_ACCOUNT extends KPaymentAccount,
        PAYMENT_ACCOUNT_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PAYMENT_ACCOUNT, PAYMENT_ACCOUNT_EXAMPLE>,
        USER extends KUser>
        extends KAbstractService<PAYMENT_ACCOUNT, PAYMENT_ACCOUNT_EXAMPLE,MAPPER>
        implements KPaymentAccountService<PAYMENT_ACCOUNT, USER> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPaymentAccountService.class);

    protected abstract PAYMENT_ACCOUNT getNewObject();

    protected abstract <S extends KUserService<USER>> S getUserService();

    @Override
    public void validate(PAYMENT_ACCOUNT paymentAccount) {
        if (paymentAccount.getCreatedDate() == null) {
            paymentAccount.setCreatedDate(new Date());
        }

        if (paymentAccount.getUid() == null) {
            paymentAccount.setUid(uuid());
        }

        if (paymentAccount.isDefaultAccount()) {
            unsetDefaultAccount(paymentAccount);
        }

        if (paymentAccount.getName() != null) {
            String slug = KInflector.getInstance().slug(paymentAccount.getName());
            paymentAccount.setSlug(slug);
        }
    }



    private void unsetDefaultAccount(PAYMENT_ACCOUNT current) {
        PAYMENT_ACCOUNT paymentAccount = fetchDefault(current.getAccountId());

        if (paymentAccount != null) {
            if (current.getId() == null || !current.getId().equals(paymentAccount.getId())) {
                paymentAccount.setDefaultAccount(false);
                getMapper().updateByPrimaryKey(paymentAccount);
            }
        }
    }



    @Override
    public PAYMENT_ACCOUNT fetchBySlug(Long accountId, String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter();
        filter.put("accountId", accountId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PAYMENT_ACCOUNT fetchDefault(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter();
        filter.put("accountId", accountId);
        filter.put("defaultAccount", true);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<PAYMENT_ACCOUNT> fetchByAccountId(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public PAYMENT_ACCOUNT fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PAYMENT_ACCOUNT fetchByProviderCustomerId(String providerCustomerId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("providerCustomerId", providerCustomerId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PAYMENT_ACCOUNT addStripeAccount(Long userId, String stripeUid, String cardLast4, boolean defaultAccount) {
        PAYMENT_ACCOUNT paymentAccount = fetchByProviderCustomerId(stripeUid);

        USER user = getUserService().fetchById(userId);

        // if we have an existing account for this stripeUid then just update the cardInfo

        if (paymentAccount != null) {
            // sanity checks
            if (!paymentAccount.getProviderName().equals("stripe")) {
                throw new IllegalStateException("Payment account is not 'stripe'");
            }

            if (!paymentAccount.getType().equals(PAYMENT_ACCOUNT.Type.CREDIT_CARD)) {
                throw new IllegalStateException("Payment account type is not 'Credit Card'");
            }
        }


        if (paymentAccount == null) {
            paymentAccount = getNewObject();
            paymentAccount.setAccountId(user.getAccountId());
            paymentAccount.setType(PAYMENT_ACCOUNT.Type.CREDIT_CARD);
            paymentAccount.setProviderName("stripe");
        }

        paymentAccount.setOwnerId(userId);
        paymentAccount.setProviderCustomerId(stripeUid);
        paymentAccount.setCardLast4(cardLast4);
        paymentAccount.setDefaultAccount(defaultAccount);

        return save(paymentAccount);
    }

}
