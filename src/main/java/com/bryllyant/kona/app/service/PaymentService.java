/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;

import java.util.Date;
import java.util.List;

public interface PaymentService extends KService, KEntityService<Payment> {
	String SERVICE_PATH = "rpc/PaymentService";

    List<Payment> fetchByInvoiceId(Long invoiceId);


    Payment fetchByProcessRef(String processorRef);


    Payment createPayment(
            Payment.Type type,
            Payment.Status status,
            String cardToken,
            PaymentAccount paymentAccount,
            Invoice invoice,
            String error,
            KServiceClient client
    );


    Payment createPayment(
            Payment.Type type,
            String cardToken,
            PaymentAccount paymentAccount,
            Invoice invoice,
            KCharge chargeItem,
            KServiceClient client
    );


    Payment createPayment(
            Payment.Type type,
            String cardToken,
            PaymentAccount paymentAccount,
            Invoice invoice,
            PaymentException ex,
            KServiceClient client
    );


    Payment createPayment(
            Payment.Type type,
            Payment.Status status,
            String cardToken,
            PaymentAccount paymentAccount,
            Invoice invoice,
            KCharge chargeItem,
            String paymentError,
            KServiceClient client
    );


    List<Payment> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );


}
