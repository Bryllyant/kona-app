package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KPayment;
import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;

import java.util.Date;
import java.util.List;

public interface KPaymentService<
            PAYMENT extends KPayment, 
            PAYMENT_ACCOUNT extends KPaymentAccount, 
            INVOICE extends KInvoice>
        extends KService, KEntityService<PAYMENT> {


    List<PAYMENT> fetchByInvoiceId(Long invoiceId);


    PAYMENT fetchByProcessRef(String processorRef);


    PAYMENT createPayment(
            PAYMENT.Type type,
            PAYMENT.Status status,
            String cardToken,
            PAYMENT_ACCOUNT paymentAccount,
            INVOICE invoice,
            String error,
            KServiceClient client
    );


    PAYMENT createPayment(
            PAYMENT.Type type,
            String cardToken,
            PAYMENT_ACCOUNT paymentAccount,
            INVOICE invoice,
            KCharge chargeItem,
            KServiceClient client
    );


    PAYMENT createPayment(
            PAYMENT.Type type,
            String cardToken,
            PAYMENT_ACCOUNT paymentAccount,
            INVOICE invoice,
            KPaymentException ex,
            KServiceClient client
    );


    PAYMENT createPayment(
            PAYMENT.Type type,
            PAYMENT.Status status,
            String cardToken,
            PAYMENT_ACCOUNT paymentAccount,
            INVOICE invoice,
            KCharge chargeItem,
            String paymentError,
            KServiceClient client
    );
    

    List<PAYMENT> fetchProximate(
            Double latitude, 
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );

}
