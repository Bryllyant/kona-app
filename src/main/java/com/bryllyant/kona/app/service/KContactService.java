/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KContact;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface KContactService<CONTACT extends KContact> extends KService, KEntityService<CONTACT> {

    CONTACT create(Long ownerId, String mobileNumber, String email, String firstName, String lastName);

    List<CONTACT> fetchByEmail(Long ownerId, String email);

    List<CONTACT> fetchByMobileNumber(Long ownerId, String mobileNumber);

    List<CONTACT> fetchByExample(CONTACT example);

    List<CONTACT> fetchByOwnerId(Long ownerId, boolean uninvitedOnly);

    List<CONTACT> saveBatch(List<CONTACT> list);

    CONTACT updatePhoto(CONTACT addressBook, Long photoId, String urlPath, String thumbnailUrlPath);

    CONTACT updatePhoto(CONTACT addressBook, byte[] data, String contentType) throws IOException;

    CONTACT removePhoto(CONTACT addressBook);

    List<CONTACT> fetchProximate(
            Double latitude,
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );
}
