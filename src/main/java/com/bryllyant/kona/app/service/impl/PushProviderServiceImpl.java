/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.PushProviderMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.PushProviderExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractPushProviderService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushProviderService.SERVICE_PATH)
public class PushProviderServiceImpl
		extends KAbstractPushProviderService<PushProvider,PushProviderExample,App>
		implements PushProviderService {
	
	private static Logger logger = LoggerFactory.getLogger(PushProviderServiceImpl.class);

	@Autowired
	private PushProviderMapper mapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private PushProviderService pushProviderService;

	@Override
	protected PushProvider getNewObject() {
	    return new PushProvider();
    }

	@Override @SuppressWarnings("unchecked")
	protected PushProviderMapper getDao() {
		return mapper;
	}
	

	@Override @SuppressWarnings("unchecked")
	protected AppService getAppService() {
		return appService;
	}
	

    @Autowired
    private KConfig config;

    private AmazonSNSClient client = null;

    @Override
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
    protected PushProviderExample getEntityExampleObject() { return new PushProviderExample(); }


}
