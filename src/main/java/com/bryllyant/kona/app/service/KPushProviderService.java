package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPush.Platform;
import com.bryllyant.kona.app.entity.KPushProvider;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface KPushProviderService<PUSH_PROVIDER extends KPushProvider> extends KService, KEntityService<PUSH_PROVIDER> {

    @Transactional
    PUSH_PROVIDER createIos(byte[] p12CertificateAndKeyFile, String password, boolean sandbox);

    @Transactional
    PUSH_PROVIDER create(String osName, String serverKey, String serverSecret, boolean sandbox);

    @Transactional
    String createEndpoint(PUSH_PROVIDER pushProvider, boolean deleteIfExists);

    PUSH_PROVIDER fetchByPlatformAndSandbox(Platform platform, boolean sandbox);

    String createIOSApplicationEndpoint(
            String applicationName,
            byte[] p12CertificateAndKeyFile,
            String password,
            boolean sandbox
    ) throws KPushException;


    String createApplicationEndpoint(
            Platform platform,
            String applicationName,
            String principal,
            String credential
    );

    void deleteApplicationEndpoint(String applicationArn);

    String createDeviceEndpoint(
            Platform platform,
            String applicationArn,
            String deviceToken,
            String customData
    );

    void deleteDeviceEndpoint(String deviceEndpoint);

    String publish(Platform platform, String endpointArn, String message);

    String publish(Platform platform, String endpointArn, String message, Map<String, Object> data);

    String publish(Platform platform, String endpointArn, Map<String,Object> rawMessage);

    Platform getPlatform(String osName, boolean sandbox);
}
