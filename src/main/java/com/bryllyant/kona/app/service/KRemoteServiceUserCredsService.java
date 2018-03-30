/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRemoteServiceUserCreds;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS extends KRemoteServiceUserCreds> 
        extends KService, KEntityService<REMOTE_SERVICE_USER_CREDS> {

    List<REMOTE_SERVICE_USER_CREDS> fetchByAccountId(Long accountId);
    
    
    /**
     * Return the list of credentials that are associated with a remote service and screenName.
     * Generally this should return just one value but it's possible for a user to have multiple accounts
     * in our app and connect the same social media account to each local account.  In this case, the same
     * remote service and screenName will be associated with multiple credential records.
     * 
     * @param remoteServiceId
     * @param screenName
     * @return
     */
    List<REMOTE_SERVICE_USER_CREDS> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName);

    REMOTE_SERVICE_USER_CREDS fetchByAccountIdAndSlug(Long accountId, String slug);

    REMOTE_SERVICE_USER_CREDS fetchByUid(String uid);

    REMOTE_SERVICE_USER_CREDS create(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds);
}
