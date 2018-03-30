package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KSupportMessage;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;

public interface KSupportMessageService<SUPPORT_MESSAGE extends KSupportMessage> extends KService, KEntityService<SUPPORT_MESSAGE> {
    
    SUPPORT_MESSAGE fetchByUid(String uid);

    List<SUPPORT_MESSAGE> fetchByUserId(Long userId);

    List<SUPPORT_MESSAGE> fetchByEmail(String email);

    List<SUPPORT_MESSAGE> fetchByMobileNumber(String mobileNumber);
    
    SUPPORT_MESSAGE send(SUPPORT_MESSAGE message);

    SUPPORT_MESSAGE send(KServiceClient client, String name, String email, String mobileNumber, String message);
}
