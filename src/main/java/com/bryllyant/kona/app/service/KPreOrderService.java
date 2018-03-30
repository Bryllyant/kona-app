package com.bryllyant.kona.app.service;

import java.util.List;
import java.util.Map;

import com.bryllyant.kona.app.entity.KPreOrder;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KPreOrderService<PRE_ORDER extends KPreOrder>
        extends KService, KEntityService<PRE_ORDER> {
            
    public static final String SERVICE_PATH = "rpc/KPreOrderService";

	public PRE_ORDER fetchByUid(String uid);
    
	public List<PRE_ORDER> fetchByAppId(Long appId);
	
	public PRE_ORDER create(PRE_ORDER preOrder, Map<String,Object> metadata, boolean processPayment, boolean sendReceipt);
}
