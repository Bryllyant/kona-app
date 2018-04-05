/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.bryllyant.kona.app.service.QueueException;
import com.bryllyant.kona.app.service.QueueService;
import com.bryllyant.kona.config.KConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(QueueService.SERVICE_PATH)
public class QueueServiceImpl implements QueueService {
	
	private static Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    private final Map<String,String> queueMap = new HashMap<>();
	
	@Autowired
	private KConfig config;
	
    private AmazonSQSClient client = null;

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


    private String getQueueUrl(String queueName) {
        String url = queueMap.get(queueName);

        if (url == null) {
            GetQueueUrlResult result = getClient().getQueueUrl(queueName);
            url = result.getQueueUrl();
            queueMap.put(queueName, url);
        }

        return url;
    }


    @Override
    public String createQueue(String queueName) throws QueueException {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);

        String queueUrl = getClient().createQueue(createQueueRequest).getQueueUrl();

        logger.debug("queuerUrl for queueName: " + queueName + "  url: " + queueUrl);

        return queueUrl;
    }


    @Override
    public void deleteQueue(String queueName) throws QueueException {
        getClient().deleteQueue(new DeleteQueueRequest(getQueueUrl(queueName)));
    }


    @Override
    public List<String> listQueues() throws QueueException {
        return getClient().listQueues().getQueueUrls();
    }


    @Override
    public void sendMessage(String queueName, String message) throws QueueException {
        getClient().sendMessage(new SendMessageRequest(getQueueUrl(queueName), message));
    }


    @Override
    public List<Message> receiveMessages(String queueName) throws QueueException {
        String queueUrl = getQueueUrl(queueName);

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);

        // max is 10 messages per request
        receiveMessageRequest.setMaxNumberOfMessages(10);

        // long polling: wait up to 20secs for a response. helps eliminate false negatives
        // by polling all sqs servers and not just a random sample that may not have any
        // messages stored on them.
        // http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-long-polling.html
        receiveMessageRequest.setWaitTimeSeconds(20);

        List<Message> messages = getClient().receiveMessage(receiveMessageRequest).getMessages();

        return messages;
    }


    @Override
    public List<Message> fetchMessages(String queueName, Integer maxCount) throws QueueException {
        Integer size = getQueueSize(queueName);

        if (maxCount == 0) return null;

        if (maxCount < 0) {
            maxCount = size;
        }

        logger.debug("fetchAllMessage: queue size for queue: " + queueName + "  size: " + size);

        List<Message> result = receiveMessages(queueName);

        while (result.size() < maxCount) {
            try { Thread.sleep(2500); } catch (InterruptedException e) {}
            result.addAll(receiveMessages(queueName));
        }

        logger.debug("fetchAllMessage: fetched results for queue: " + queueName + "  size: " + result.size());

        return result;
    }


    @Override
    public void deleteMessage(String queueName, Message message) throws QueueException {
        String queueUrl = getQueueUrl(queueName);

        String handle = message.getReceiptHandle();

        logger.debug("deleting message for queue: " + queueUrl + "  handle: " + handle);

        getClient().deleteMessage(new DeleteMessageRequest(queueUrl, handle));
    }


    @Override
    public Integer getQueueSize(String queueName) throws QueueException {
        String queueUrl = getQueueUrl(queueName);

        List<String> attributes = new ArrayList<String>();

        attributes.add("All");

        GetQueueAttributesResult result = getClient().getQueueAttributes(queueUrl, attributes);

        Map<String,String> map = result.getAttributes();

        Integer size = Integer.parseInt(map.get("ApproximateNumberOfMessages"));

        return size;
    }
	
}
