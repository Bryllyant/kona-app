/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface PushProviderService extends KService, KEntityService<PushProvider> {
	String SERVICE_PATH = "rpc/PushProviderService";

    @Transactional
    PushProvider createIos(byte[] p12CertificateAndKeyFile, String password, boolean sandbox);

    @Transactional
    PushProvider create(String osName, String serverKey, String serverSecret, boolean sandbox);

    @Transactional
    String createEndpoint(PushProvider pushProvider, boolean deleteIfExists);

    PushProvider fetchByPlatformAndSandbox(Push.Platform platform, boolean sandbox);

    String createIOSApplicationEndpoint(
            String applicationName,
            byte[] p12CertificateAndKeyFile,
            String password,
            boolean sandbox
    ) throws PushException;


    String createApplicationEndpoint(
            Push.Platform platform,
            String applicationName,
            String principal,
            String credential
    );

    void deleteApplicationEndpoint(String applicationArn);

    String createDeviceEndpoint(
            Push.Platform platform,
            String applicationArn,
            String deviceToken,
            String customData
    );

    void deleteDeviceEndpoint(String deviceEndpoint);

    String publish(Push.Platform platform, String endpointArn, String message);

    String publish(Push.Platform platform, String endpointArn, String message, Map<String, Object> data);

    String publish(Push.Platform platform, String endpointArn, Map<String,Object> rawMessage);

    Push.Platform getPlatform(String osName, boolean sandbox);
}
