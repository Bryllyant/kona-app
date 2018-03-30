/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.service.KAbstractQueueService;
import com.bryllyant.kona.app.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(QueueService.SERVICE_PATH)
public class QueueServiceImpl extends KAbstractQueueService implements QueueService {
	
	private static Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);
	
	@Autowired
	private KConfig config;
	
    private AmazonSQSClient client = null;

	@Override
	protected AmazonSQSClient getClient() {
        if (client == null) {
            String accessKey = config.getString("aws.sqs.accessKey");
            String secretKey = config.getString("aws.sqs.secretKey");
            String region = config.getString("aws.sqs.region");

             AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

             // Instantiate an Amazon SQS client, which will make the service call with the supplied AWS credentials.
             client = new AmazonSQSClient(credentials);

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
	
}
