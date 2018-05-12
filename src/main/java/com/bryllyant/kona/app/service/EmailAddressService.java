/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface EmailAddressService extends KService, KEntityService<EmailAddress> {
	String SERVICE_PATH = "rpc/EmailAddressService";

    EmailAddress fetchByEmail(String email);

    List<Map<String,Object>> fetchSources(boolean nocache);

    List<EmailAddress> fetchAll(Boolean scrubbed, Boolean enabled);

    List<Long> fetchAllIds(Boolean scrubbed, Boolean enabled);

    List<EmailAddress> fetchRandom(Long count, List<String> sourceList, List<String> excludeGroupSlugList);

    void scrub(String source, Long startId, Long endId, Date startDate, Date endDate, boolean tryConnectMX);

    boolean scrub(EmailAddress address, boolean force, boolean tryConnectMX);

    boolean isValid(EmailAddress address, boolean forceScrub);

    void importCSV(String csvFile, String errorFile, Map<String, Object> recordMap,
                   String source, boolean scrubbed, boolean skipFirstRecord) throws IOException;
	
}
