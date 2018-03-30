package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;


public interface KGooglePlayService<PURCHASE extends KPurchase> extends KService {

	PURCHASE getSubscription(String packageName, String productId, String token) throws IOException;

	PURCHASE getSubscription(Long productId, String token) throws IOException;
    
    void cancelSubscription(String packageName, String productId, String token) throws IOException;
    
	void cancelSubscription(Long productId, String token) throws IOException;
	
	PURCHASE getSubscription(Long accountId, Long productId) throws IOException;

	void cancelSubscription(Long accountId, Long productId) throws IOException;

}
