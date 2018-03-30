package com.bryllyant.kona.app.service;

import java.util.List;

import com.amazonaws.services.sqs.model.Message;
import com.bryllyant.kona.remote.service.KService;


public interface KQueueService extends KService {
	public String createQueue(String queue) throws KQueueException;
	
	public void deleteQueue(String queue) throws KQueueException;
	
    public Integer getQueueSize(String queue) throws KQueueException;
    
	public List<String> listQueues() throws KQueueException;
	
	public void sendMessage(String queue, String message) throws KQueueException;
	
	public List<Message> receiveMessages(String queue) throws KQueueException;
	
	public List<Message> fetchMessages(String queue, Integer maxCount) throws KQueueException;
	
	public void deleteMessage(String queue, Message message) throws KQueueException;
}
