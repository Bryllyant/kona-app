/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.bryllyant.kona.app.dao.PushProviderMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.BasePush.Platform;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.PushProviderExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PushException;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(PushProviderService.SERVICE_PATH)
public class PushProviderServiceImpl
		extends KAbstractService<PushProvider,PushProviderExample,PushProviderMapper>
        implements PushProviderService {
	
	private static Logger logger = LoggerFactory.getLogger(PushProviderServiceImpl.class);

    private static final Map<Platform,Map<String, MessageAttributeValue>> attributesMap = new HashMap<>();

    static {
        attributesMap.put(Platform.ADM, null);
        attributesMap.put(Platform.GCM, null);
        attributesMap.put(Platform.APNS, null);
        attributesMap.put(Platform.APNS_SANDBOX, null);
        attributesMap.put(Platform.BAIDU, addBaiduAttributes());
        attributesMap.put(Platform.WNS, addWNSAttributes());
        attributesMap.put(Platform.MPNS, addMPNSAttributes());
    }

    @Autowired
    private KConfig config;

	@Autowired
	private PushProviderMapper mapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private PushProviderService pushProviderService;

	@Override @SuppressWarnings("unchecked")
	protected PushProviderMapper getMapper() {
		return mapper;
	}
	

    private AmazonSNSClient client = null;


    protected AmazonSNSClient getClient() {
        if (client == null) {
            String accessKey = config.getString("aws.sns.push.accessKey");
            String secretKey = config.getString("aws.sns.push.secretKey");
            String region = config.getString("aws.sns.push.region");

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

            // Instantiate an Amazon SQS client, which will make the service call with the supplied AWS credentials.
            client = new AmazonSNSClient(credentials);

            Region REGION = null;

            region = region.trim().toLowerCase();

            switch (region) {
                case "us-east-1":
                    REGION = Region.getRegion(Regions.US_EAST_1);
                    break;
                case "us-west-2":
                    REGION = Region.getRegion(Regions.US_WEST_2);
                    break;
            }


            client.setRegion(REGION);
        }

        return client;
    }

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }


    @Override @Transactional
    public void remove(PushProvider pushProvider) {
        String endpoint = pushProvider.getEndpoint();

        super.remove(pushProvider);

        if (endpoint != null) {
            deleteApplicationEndpoint(endpoint);
        }
    }

    @Override
    public void validate(PushProvider pushProvider) {
        if (pushProvider.getCreatedDate() == null) {
            pushProvider.setCreatedDate(new Date());
        }

        if (pushProvider.getUid() == null) {
            pushProvider.setUid(uuid());
        }

        pushProvider.setUpdatedDate(new Date());

        if (pushProvider.getEndpoint() == null
                && pushProvider.getServerSecret() != null
                && pushProvider.getPlatform() != null) {
            String endpoint = createEndpoint(pushProvider, true);

            pushProvider.setEndpoint(endpoint);
        }
    }


    @Override
    public PushProvider fetchByPlatformAndSandbox(Platform platform, boolean sandbox) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("platform", platform);
        filter.put("sandbox", sandbox);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override @Transactional
    public PushProvider createIos(byte[] p12CertificateAndKeyFile, String p12Password, boolean sandbox) {
        try {
            String[] result = KEncryptUtil.decodeP12Certificate(p12CertificateAndKeyFile, p12Password);

            String serverKey = result[0];
            String serverSecret = result[1];

            return create("ios", serverKey, serverSecret, sandbox);

        } catch (IOException e) {
            throw new PushException(e);
        }
    }

    @Override @Transactional
    public PushProvider create(String osName, String serverKey, String serverSecret, boolean sandbox) {
        Platform platform = getPlatform(osName, sandbox);

        if (platform == null) {
            throw new PushException("OS is not supported for push notification: " + osName);
        }

        PushProvider provider = new PushProvider();

        provider.setPlatform(platform);
        provider.setServerKey(serverKey);
        provider.setServerSecret(serverSecret);
        provider.setSandbox(sandbox);

        return add(provider);
    }


    @Override @Transactional
    public String createEndpoint(PushProvider provider, boolean deleteIfExists) {
        if (provider.getServerSecret() == null) {
            throw new PushException("PushServerSecret must be set");
        }

        App app = appService.getSystemApp();

        // ok, everything looks good so far.  if we have an existing endpoint, delete it first
        if (provider.getEndpoint() != null && deleteIfExists) {
            deleteApplicationEndpoint(provider.getEndpoint());
        }

        String principal = provider.getServerKey();

        String credential = provider.getServerSecret();

        String endpoint = createApplicationEndpoint(provider.getPlatform(), app.getName(), principal, credential);

        if (endpoint == null) {
            throw new PushException("Unable to create endpoint for pushProviderId: " + provider.getId());
        }

        return endpoint;
    }


    @Override
    public String createIOSApplicationEndpoint(
            String applicationName,
            byte[] p12CertificateAndKeyFile,
            String password,
            boolean sandbox
    ) throws PushException {

        if (applicationName == null) {
            throw new NullPointerException("Invalid application name: " + applicationName);
        }

        try {
            String[] result = KEncryptUtil.decodeP12Certificate(p12CertificateAndKeyFile, password);

            String principal = result[0];
            String credential = result[1];

            Platform platform = Platform.APNS;

            if (sandbox) {
                platform = Platform.APNS_SANDBOX;
            }

            return createApplicationEndpoint(platform, applicationName, principal, credential);
        } catch (IOException e) {
            throw new PushException(e);
        }
    }


    /*
     * iOS:
     *   principal:  certificate - This should be in pem format with \n at the end of each line.
     *   credential:  privateKey - This should be in pem format with \n at the end of each line.
     *
     * android:
     *   principal:  empty string ""
     *   credential: serverAPIKey
     */
    @Override
    public String createApplicationEndpoint(Platform platform, String applicationName,
                                            String principal, String credential) {
        if (applicationName == null) {
            throw new NullPointerException("Invalid application name: " + applicationName);
        }

        // normalize application names
        applicationName = KInflector.getInstance().slug(applicationName);

        CreatePlatformApplicationRequest platformApplicationRequest = new CreatePlatformApplicationRequest();

        Map<String, String> attributes = new HashMap<>();

        attributes.put("PlatformPrincipal", principal);
        attributes.put("PlatformCredential", credential);
        platformApplicationRequest.setAttributes(attributes);
        platformApplicationRequest.setName(applicationName);
        platformApplicationRequest.setPlatform(platform.name());

        return getClient().createPlatformApplication(platformApplicationRequest).getPlatformApplicationArn();
    }


    @Override
    public String createDeviceEndpoint(Platform platform, String applicationArn,  String deviceToken, String customData) {
        CreatePlatformEndpointRequest platformEndpointRequest = new CreatePlatformEndpointRequest();

        platformEndpointRequest.setCustomUserData(customData);

        String token = deviceToken;
        String userId = null;

        Map<String, String> endpointAttributes = new HashMap<>();
        endpointAttributes.put("Enabled", "true");

        if (platform == Platform.BAIDU) {
            String[] tokenBits = deviceToken.split("\\|");
            token = tokenBits[0];
            userId = tokenBits[1];

            endpointAttributes.put("UserId", userId);
            endpointAttributes.put("ChannelId", token);
        }

        platformEndpointRequest.setToken(token);
        platformEndpointRequest.setAttributes(endpointAttributes);
        platformEndpointRequest.setPlatformApplicationArn(applicationArn);

        return getClient().createPlatformEndpoint(platformEndpointRequest).getEndpointArn();
    }

    @Override
    public void deleteApplicationEndpoint(String applicationArn) {
        DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn(applicationArn);
        getClient().deletePlatformApplication(request);
    }


    @Override
    public void deleteDeviceEndpoint(String deviceEndpoint) {
        DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn(deviceEndpoint);
        getClient().deleteEndpoint(request);
    }

    private Map<String, MessageAttributeValue> getValidAttributes(Map<String, MessageAttributeValue> attributes) {
        Map<String, MessageAttributeValue> validAttributes = new HashMap<String, MessageAttributeValue>();

        if (attributes == null) return validAttributes;

        for (Map.Entry<String, MessageAttributeValue> entry : attributes.entrySet()) {
            if (!isBlank(entry.getValue().getStringValue())) {
                validAttributes.put(entry.getKey(), entry.getValue());
            }
        }

        return validAttributes;
    }

    private boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        if (s.length() < 1) {
            return true;
        }

        return false;
    }

    private boolean isBlank(String s) {
        if (isEmpty(s)) {
            return true;
        }

        if (isEmpty(s.trim())) {
            return true;
        }

        return false;
    }


    @Override
    public String publish(Platform platform, String endpointArn, String message) {
        return publish(platform, endpointArn, message, null);
    }


    @Override
    public String publish(Platform platform, String endpointArn, String message, Map<String,Object> data) {
        Map<String,Object> rawMessage = null;
        switch (platform) {
            case GCM:
                rawMessage = buildAndroidMessage(message, data);
                break;
            case APNS:
            case APNS_SANDBOX:
                rawMessage = buildAppleMessage(message, data, 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid platform: " + platform);
        }
        return publish(platform, endpointArn, rawMessage);
    }


    @Override
    public String publish(Platform platform, String endpointArn, Map<String,Object> rawMessage) {
        PublishRequest publishRequest = new PublishRequest();

        publishRequest.setMessageStructure("json");

        // For direct publish to mobile end points, topicArn is not relevant.
        publishRequest.setTargetArn(endpointArn);

        Map<String, MessageAttributeValue> attributes = getValidAttributes(attributesMap.get(platform));

        if (attributes != null && !attributes.isEmpty()) {
            publishRequest.setMessageAttributes(attributes);
        }


        Map<String, String> messageMap = new HashMap<>();

        messageMap.put(platform.name(), KJsonUtil.toJson(rawMessage));

        String message = KJsonUtil.toJson(messageMap);

        publishRequest.setMessage(message);

        logMessage(message, attributes);

        return getClient().publish(publishRequest).getMessageId();
    }


    private void logMessage(String message, Map<String, MessageAttributeValue> attributes) {
        // Display the message that will be sent to the endpoint/
        logger.debug("{Message Body: " + message + "}");

        if (attributes != null && !attributes.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            builder.append("{Message Attributes: ");

            for (Map.Entry<String, MessageAttributeValue> entry : attributes.entrySet()) {
                builder.append(
                        "(\""
                                + entry.getKey()
                                + "\": \""
                                + entry.getValue().getStringValue()
                                + "\"),"
                );
            }

            builder.deleteCharAt(builder.length() - 1);

            builder.append("}");

            logger.debug(builder.toString());
        }
    }


    private Map<String,Object> buildAppleMessage(String message, Map<String,Object> data, Integer badgeCount) {
        Map<String, Object> appleMessageMap = new HashMap<String, Object>();
        Map<String, Object> appMessageMap = new HashMap<String, Object>();

        appMessageMap.put("alert", message);
        appMessageMap.put("badge", badgeCount);
        appMessageMap.put("sound", "default");

        appleMessageMap.put("aps", appMessageMap);

        if (data != null) {
            for (String key : data.keySet()) {
                appleMessageMap.put(key, data.get(key));
            }
        }

        return appleMessageMap;
    }


    private Map<String, Object> buildAndroidMessage(String message, Map<String,Object> data) {
        //Map<String, String> payload = new HashMap<String, String>();
        //payload.put("message", message);

        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put("message", message);


        // https://support.urbanairship.com/entries/71447343-GCM-Delay-while-idle-time-to-live-and-collapse-key
        Map<String, Object> androidMessageMap = new HashMap<>();

        androidMessageMap.put("data", data);
        androidMessageMap.put("collapse_key", "blync"); // only the last message will be delivered to the device when it comes back online
        androidMessageMap.put("delay_while_idle", false); // false, send to device even if 'idle'
        androidMessageMap.put("time_to_live", 86400); // expire message after this many secs if the device is offline
        androidMessageMap.put("dry_run", false); // set to true to validate message but not send it to device

        return androidMessageMap;
    }


    private static Map<String, MessageAttributeValue> addBaiduAttributes() {
        Map<String, MessageAttributeValue> attributes = new HashMap<>();

        attributes.put("AWS.SNS.MOBILE.BAIDU.DeployStatus",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("1"));

        attributes.put("AWS.SNS.MOBILE.BAIDU.MessageKey",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("default-channel-msg-key"));

        attributes.put("AWS.SNS.MOBILE.BAIDU.MessageType",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("0"));

        return attributes;
    }

    private static Map<String, MessageAttributeValue> addWNSAttributes() {
        Map<String, MessageAttributeValue> attributes = new HashMap<>();

        attributes.put("AWS.SNS.MOBILE.WNS.CachePolicy",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("cache"));

        attributes.put("AWS.SNS.MOBILE.WNS.Type",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("wns/badge"));

        return attributes;
    }

    private static Map<String, MessageAttributeValue> addMPNSAttributes() {
        Map<String, MessageAttributeValue> attributes = new HashMap<>();

        attributes.put("AWS.SNS.MOBILE.MPNS.Type",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("token")); // This attribute is required.

        attributes.put("AWS.SNS.MOBILE.MPNS.NotificationClass",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("realtime")); // This attribute is required.

        return attributes;
    }



    // support ios and android only for now
    public Platform getPlatform(String osName, boolean sandbox) {

        if (osName == null) return null;

        osName = osName.trim().toLowerCase();

        Platform platform = null;

        switch (osName) {
            case "ios":
                platform = Platform.APNS;

                if (sandbox) {
                    platform = Platform.APNS_SANDBOX;
                }
                break;

            case "android":
                platform = Platform.GCM;
                break;

            default:
                logger.error("Unsupported platform: " + osName);
                platform = null;
                break;
        }

        return platform;
    }
}
