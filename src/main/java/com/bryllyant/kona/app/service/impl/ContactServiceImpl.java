package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ContactMapper;
import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.app.entity.ContactExample;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.ContactService;
import com.bryllyant.kona.app.service.KAbstractContactService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(ContactService.SERVICE_PATH)
public class ContactServiceImpl
		extends KAbstractContactService<
		        Contact,
		        ContactExample,
		        User,
		        File,
		        Media,
				Place>
		implements ContactService {
	
	private static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactMapper contactDao;
	
	@Autowired
	UserService userService;

	@Autowired
	MediaService mediaService;

	@Autowired
    PlaceService placeService;

	@Override @SuppressWarnings("unchecked")
	protected ContactMapper getDao() {
		return contactDao;
	}
	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
	
    @Override @SuppressWarnings("unchecked")
    protected MediaService getMediaService() {
        return mediaService;
    }

	@Override @SuppressWarnings("unchecked")
	protected PlaceService getPlaceService() {
		return placeService;
	}


	@Override
	protected Contact getNewObject() {
		return new Contact();
	}
	

//	@Override
//	protected void updateCoords(Long contactId) {
//	    getDao().updateCoords(contactId);
//	}
	

	 @Override
    protected ContactExample getEntityExampleObject() { return new ContactExample(); }

	

//    @Override
//    public List<Contact> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
//        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
//    }
}
