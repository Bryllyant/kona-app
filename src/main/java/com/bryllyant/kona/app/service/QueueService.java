/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.amazonaws.services.sqs.model.Message;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface QueueService extends KService {
	String SERVICE_PATH = "rpc/QueueService";

    String createQueue(String queue) throws QueueException;

    void deleteQueue(String queue) throws QueueException;

    Integer getQueueSize(String queue) throws QueueException;

    List<String> listQueues() throws QueueException;

    void sendMessage(String queue, String message) throws QueueException;

    List<Message> receiveMessages(String queue) throws QueueException;

    List<Message> fetchMessages(String queue, Integer maxCount) throws QueueException;

    void deleteMessage(String queue, Message message) throws QueueException;
}
