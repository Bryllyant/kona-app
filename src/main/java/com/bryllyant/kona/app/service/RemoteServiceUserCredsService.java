/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.RemoteServiceUserCreds;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface RemoteServiceUserCredsService extends KService, KEntityService<RemoteServiceUserCreds> {
	String SERVICE_PATH = "rpc/RemoteServiceUserCredsService";

    List<RemoteServiceUserCreds> fetchByAccountId(Long accountId);

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
    List<RemoteServiceUserCreds> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName);

    RemoteServiceUserCreds fetchByAccountIdAndSlug(Long accountId, String slug);

    RemoteServiceUserCreds create(RemoteServiceUserCreds remoteServiceUserCreds);
}
