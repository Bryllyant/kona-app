package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface KEmailAddressService<EMAIL_ADDRESS extends KEmailAddress>
        extends KService, KEntityService<EMAIL_ADDRESS> {

    EMAIL_ADDRESS fetchByEmail(String email);

    List<EMAIL_ADDRESS> fetchAll(Boolean scrubbed, Boolean enabled);

    List<Long> fetchAllIds(Boolean scrubbed, Boolean enabled);

    List<EMAIL_ADDRESS> fetchRandom(Long count, List<String> sourceList, List<String> excludeGroupSlugList);

    void scrub(String source, Long startId, Long endId, Date startDate, Date endDate);

    boolean scrub(EMAIL_ADDRESS address, boolean force);

    boolean isValid(EMAIL_ADDRESS address);

    void importCSV(String csvFile, String errorFile, Map<String, Object> recordMap,
                          String source, boolean scrubbed, boolean skipFirstRecord) throws IOException;
}
