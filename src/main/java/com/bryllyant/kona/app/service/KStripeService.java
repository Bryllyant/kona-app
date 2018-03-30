package com.bryllyant.kona.app.service;

import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.stripe.entity.KCard;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.stripe.entity.KStripeException;

import java.math.BigDecimal;
import java.util.Map;

public interface KStripeService 
		extends KService, com.bryllyant.kona.stripe.service.KStripeService {

    String addCustomer(Long userId);

    void deleteCustomer(Long userId);
    
    void updateCustomer(Long userId);
    
    KCharge chargeCustomer(Long userId, BigDecimal amount,
            String description, String receiptEmail,
            Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException;

    KCard addPrimaryCard(Long userId, KCard card);
    
    KCard addPrimaryCard(Long userId, String cardToken);
    
    KCard updatePrimaryCard(Long userId, KCard card);
    
    KCard updatePrimaryCard(Long userId, String cardToken);
    
    KCard getPrimaryCard(Long userId);
    
    //KCard getPrimaryCard(ACCOUNT account);
    
    String getPrimaryCardLast4(Long userId);

    String updateStripeUid(Long userId, String cardToken);
}
