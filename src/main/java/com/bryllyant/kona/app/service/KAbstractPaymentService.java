package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.app.entity.KPayment;
import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPaymentService<PAYMENT extends KPayment, PAYMENT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<PAYMENT, PAYMENT_EXAMPLE>,
        PAYMENT_ACCOUNT extends KPaymentAccount,
        INVOICE extends KInvoice,
        INVOICE_ITEM extends KInvoiceItem,
        CART extends KCart,
        CART_ITEM extends KCartItem>
        extends KAbstractService<PAYMENT,PAYMENT_EXAMPLE,MAPPER>
        implements KPaymentService<PAYMENT,PAYMENT_ACCOUNT,INVOICE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPaymentService.class);

    protected abstract PAYMENT getNewPaymentObject();

    protected abstract void updateCoords(Long paymentId);

    protected abstract <S extends KInvoiceService<INVOICE,INVOICE_ITEM,CART,CART_ITEM>> S getInvoiceService();

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override @Transactional
    public PAYMENT add(PAYMENT payment) {
        payment = super.add(payment);
        updateCoords(payment.getId());
        return payment;
    }


    @Override @Transactional
    public PAYMENT update(PAYMENT payment) {
        payment = super.update(payment);
        updateCoords(payment.getId());
        return payment;
    }


    protected BigDecimal toBigDecimal(Integer cents) {
        if (cents == null) return null;
        BigDecimal result = new BigDecimal(cents);
        result = result.divide(new BigDecimal(100));
        return result;
    }


    @Override
    public void validate(PAYMENT payment) {
        if (payment.getCreatedDate() == null) {
            payment.setCreatedDate(new Date());
        }

        if (payment.getUid() == null) {
            payment.setUid(uuid());
        }

        payment.setUpdatedDate(new Date());
    }



    @Override
    public List<PAYMENT> fetchByInvoiceId(Long invoiceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public PAYMENT fetchByProcessRef(String processorRef) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("processorRef", processorRef);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PAYMENT createPayment(PAYMENT.Type type, PAYMENT.Status status,
                                 String cardToken, PAYMENT_ACCOUNT paymentAccount, INVOICE invoice, String error,
                                 KServiceClient client) {
        return createPayment(type, status, cardToken, paymentAccount, invoice, null, error, client);
    }



    @Override
    public PAYMENT createPayment(PAYMENT.Type type, String cardToken, PAYMENT_ACCOUNT paymentAccount,
                                 INVOICE invoice, KCharge chargeItem, KServiceClient client) {

        return createPayment(type, PAYMENT.Status.SUCCESS,
                cardToken, paymentAccount, invoice, chargeItem, null, client);
    }



    @Override
    public PAYMENT createPayment(PAYMENT.Type type, String cardToken, PAYMENT_ACCOUNT paymentAccount,
                                 INVOICE invoice, KPaymentException ex, KServiceClient client) {

        String error = ex.getMessage();

        return createPayment(type, PAYMENT.Status.PROCESSOR_ERROR,
                cardToken, paymentAccount, invoice, null, error, client);
    }



    @Override
    public PAYMENT createPayment(PAYMENT.Type type, PAYMENT.Status status,
                                 String cardToken, PAYMENT_ACCOUNT paymentAccount, INVOICE invoice,
                                 KCharge chargeItem, String paymentError, KServiceClient client) {

        PAYMENT payment = getNewPaymentObject();
        
        /*
        String accessToken = client.getAccessToken();
        String sessionId = client.getSessionId();
        
        String accessToken = getSessionAccessToken();
        if (accessToken != null) {
            if (!getAuthService().isAccessTokenValid(accessToken)) {
            	accessToken = null;
            }
        }
        String sessionId = getSessionId();
        */

        boolean paid = false;
        boolean failed = true;
        Date now = new Date();

        String paymentRef = null;
        String cardLast4 = null;
        BigDecimal amountPaid = null;
        BigDecimal fee = null;

        if (chargeItem != null) {
            paymentRef = chargeItem.getId();

            cardLast4 = chargeItem.getCard().getLast4();

            amountPaid = toBigDecimal(chargeItem.getAmount());

            fee = toBigDecimal(chargeItem.getFee());

            if (paymentError == null) {
                paymentError = chargeItem.getFailureMessage();
            }
        }


        if (chargeItem != null && chargeItem.isPaid()) {
            paid = true;
            failed = false;
            /*
            invoice.setClosed(true);
            invoice.setClosedDate(now);
        	invoice.setPaymentRef(paymentRef);
            invoice.setPaymentCardLast4(cardLast4);
            invoice.setPaid(paid);
            invoice.setPaidDate(now);
            invoice.setAmountPaid(amountPaid);
            invoiceService.update(invoice);
            */
            getInvoiceService().closeInvoice(invoice, true, amountPaid, paymentRef, cardLast4, null);

            payment.setPaidDate(now);
        } else {
            payment.setFailedDate(now);

        }

        payment.setType(type);
        payment.setStatus(status);
        payment.setCurrencyId(invoice.getCurrencyId());
        payment.setUserId(invoice.getUserId());
        payment.setAccountId(invoice.getAccountId());
        payment.setInvoiceId(invoice.getId());

        if (client != null) {
            payment.setTokenId(client.getTokenId());
            payment.setHostname(client.getHostname());
            payment.setUserAgent(client.getUserAgent());
            payment.setLatitude(client.getLatitude());
            payment.setLongitude(client.getLongitude());
        }

        if (paymentAccount != null) {
            payment.setPaymentAccountId(paymentAccount.getId());
        }

        payment.setCardToken(cardToken);
        payment.setCardLast4(cardLast4);
        payment.setAmount(amountPaid);
        payment.setProcessorRef(paymentRef);
        payment.setProcessorError(paymentError);
        payment.setProcessorFee(fee);
        payment.setPaid(paid);
        payment.setFailed(failed);
        payment.setCreatedDate(new Date());

        payment = add(payment);

        return payment;
    }
}
