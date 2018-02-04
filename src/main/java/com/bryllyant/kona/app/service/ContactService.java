package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.remote.service.KService;

public interface ContactService extends KService, KContactService<Contact> {
	public static final String SERVICE_PATH = "rpc/ContactService";
	
}
