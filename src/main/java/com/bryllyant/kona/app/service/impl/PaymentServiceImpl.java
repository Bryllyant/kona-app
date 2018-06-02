/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PaymentMapper;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.PaymentExample;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PaymentException;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PaymentService.SERVICE_PATH)
public class PaymentServiceImpl 
		extends KAbstractService<Payment, PaymentExample, PaymentMapper>
		implements PaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    @Autowired
    private InvoiceService invoiceService;

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }

    @Override @SuppressWarnings("unchecked")
    protected PaymentMapper getMapper() {
        return paymentMapper;
    }


    protected void updateCoords(Long paymentId) {
        getMapper().updateCoords(paymentId);
    }

    @Override
    public List<Payment> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }


    @Override @Transactional
    public Payment add(Payment payment) {
        payment = super.add(payment);
        updateCoords(payment.getId());
        return payment;
    }


    @Override @Transactional
    public Payment update(Payment payment) {
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
    public void validate(Payment payment) {
        if (payment.getCreatedDate() == null) {
            payment.setCreatedDate(new Date());
        }

        if (payment.getUid() == null) {
            payment.setUid(uuid());
        }

        payment.setUpdatedDate(new Date());
    }



    @Override
    public List<Payment> fetchByInvoiceId(Long invoiceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
        return fetchByCriteria(filter);
    }



    @Override
    public Payment fetchByProcessRef(String processorRef) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("processorRef", processorRef);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }



    @Override
    public Payment createPayment(Payment.Type type, Payment.Status status,
                                 String cardToken, PaymentAccount paymentAccount, Invoice invoice, String error,
                                 KServiceClient client) {
        return createPayment(type, status, cardToken, paymentAccount, invoice, null, error, client);
    }



    @Override
    public Payment createPayment(Payment.Type type, String cardToken, PaymentAccount paymentAccount,
                                 Invoice invoice, KCharge chargeItem, KServiceClient client) {

        return createPayment(type, Payment.Status.SUCCESS,
                cardToken, paymentAccount, invoice, chargeItem, null, client);
    }



    @Override
    public Payment createPayment(Payment.Type type, String cardToken, PaymentAccount paymentAccount,
                                 Invoice invoice, PaymentException ex, KServiceClient client) {

        String error = ex.getMessage();

        return createPayment(type, Payment.Status.PROCESSOR_ERROR,
                cardToken, paymentAccount, invoice, null, error, client);
    }



    @Override
    public Payment createPayment(Payment.Type type, Payment.Status status,
                                 String cardToken, PaymentAccount paymentAccount, Invoice invoice,
                                 KCharge chargeItem, String paymentError, KServiceClient client) {

        Payment payment = new Payment();

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
            invoiceService.closeInvoice(invoice, true, amountPaid, paymentRef, cardLast4, null);

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
