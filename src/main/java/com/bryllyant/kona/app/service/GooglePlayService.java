/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;

public interface GooglePlayService extends KService {
	String SERVICE_PATH = "rpc/GooglePlayService";

    Purchase getSubscription(String packageName, String productId, String token) throws IOException;

    Purchase getSubscription(Long productId, String token) throws IOException;

    void cancelSubscription(String packageName, String productId, String token) throws IOException;

    void cancelSubscription(Long productId, String token) throws IOException;

    Purchase getSubscription(Long accountId, Long productId) throws IOException;

    void cancelSubscription(Long accountId, Long productId) throws IOException;
	
}
