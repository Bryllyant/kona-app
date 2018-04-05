package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ContactService extends KService, KEntityService<Contact> {
	String SERVICE_PATH = "rpc/ContactService";

    Contact create(Long ownerId, String mobileNumber, String email, String firstName, String lastName);

    List<Contact> fetchByEmail(Long ownerId, String email);

    List<Contact> fetchByMobileNumber(Long ownerId, String mobileNumber);

    List<Contact> fetchByExample(Contact example);

    List<Contact> fetchByOwnerId(Long ownerId, boolean uninvitedOnly);

    List<Contact> saveBatch(List<Contact> list);

    Contact updatePhoto(Contact addressBook, Long photoId, String urlPath, String thumbnailUrlPath);

    Contact updatePhoto(Contact addressBook, byte[] data, String contentType) throws IOException;

    Contact removePhoto(Contact addressBook);

    List<Contact> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
