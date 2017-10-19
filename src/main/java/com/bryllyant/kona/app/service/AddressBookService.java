package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AddressBook;
import com.bryllyant.kona.remote.service.KService;

public interface AddressBookService extends KService, KAddressBookService<AddressBook> {
	public static final String SERVICE_PATH = "rpc/AddressBookService";
	
}
