package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AddressBookMapper;
import com.bryllyant.kona.app.entity.AddressBook;
import com.bryllyant.kona.app.entity.AddressBookExample;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AddressBookService;
import com.bryllyant.kona.app.service.KAbstractAddressBookService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AddressBookService.SERVICE_PATH)
public class AddressBookServiceImpl 
		extends KAbstractAddressBookService<
		        AddressBook,
		        AddressBookExample,
		        User,
		        File,
		        Media> 
		implements AddressBookService {
	
	private static Logger logger = LoggerFactory.getLogger(AddressBookServiceImpl.class);

	// ----------------------------------------------------------------------------

	@Autowired
	private AddressBookMapper addressBookDao;
	
	@Autowired
	UserService userService;

	@Autowired
	MediaService mediaService;

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AddressBookMapper getDao() {
		return addressBookDao;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
	
   // ----------------------------------------------------------------------------
    
    @Override @SuppressWarnings("unchecked")
    protected MediaService getMediaService() {
        return mediaService;
    }
	
	// ----------------------------------------------------------------------------
	
	@Override 
	protected AddressBook getNewObject() {
		return new AddressBook();
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	protected void updateCoords(Long addressBookId) {
	    getDao().updateCoords(addressBookId);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	protected AddressBookExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		AddressBookExample example = new AddressBookExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
		example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
		return example;
	}
	
	// ----------------------------------------------------------------------------

    @Override 
    public List<AddressBook> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
    }
}
