/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KContact;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KMedia;
import com.bryllyant.kona.app.entity.KPlace;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.KClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class KAbstractContactService<CONTACT extends KContact, CONTACT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<CONTACT, CONTACT_EXAMPLE>,
										 USER extends KUser,
										 FILE extends KFile,
										 MEDIA extends KMedia,
										 PLACE extends KPlace>
		extends KAbstractService<CONTACT,CONTACT_EXAMPLE,MAPPER>
		implements KContactService<CONTACT> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractContactService.class);
    

    protected abstract CONTACT getNewObject();

    //protected abstract void updateCoords(Long contactId);

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KMediaService<MEDIA,USER,FILE>> S getMediaService();

    protected abstract <S extends KPlaceService<PLACE>> S getPlaceService();


    @Override 
    public CONTACT add(CONTACT contact) {
        validate(contact);
        
        // check if similar entry already exists
        CONTACT ab = null;
        
        Long ownerId = contact.getOwnerId();

        USER refUser = null;
        
        List<CONTACT> abList = null;
        
        // use mobile number as a unique identifier
        if (contact.getMobileNumber() != null) {
        	abList = fetchByMobileNumber(ownerId, contact.getMobileNumber());
        	
            if (abList != null && abList.size() > 0) {
            	/*
                ab = mergeAndUpdate(contact, ab);
                return ab;
                */
            	return abList.get(0);
            }
            
            refUser = getUserService().fetchByMobileNumber(contact.getMobileNumber());
        }

        // mobile is null, so check if email exists
        if (contact.getEmail() != null) {
        	abList = fetchByEmail(ownerId, contact.getEmail());
            if (abList != null && abList.size() > 0) {
            	/*
                ab = mergeAndUpdate(contact, ab);
                return ab;
                */
            	return abList.get(0);
            }
            
            if (refUser == null) {
            	refUser = getUserService().fetchByEmail(contact.getEmail());
            }
        }

        // email is null, so check first name, last name and address
        if (contact.getFirstName() != null 
        		&& contact.getLastName() != null 
                && contact.getPlaceId() != null) {
        	
		    Map<String,Object> filter = KMyBatisUtil.createFilter("firstName", contact.getFirstName());
		    filter.put("lastName", contact.getLastName());
		    filter.put("placeId", contact.getPlaceId());
		    
		    abList = fetchByCriteria(0, 99999, null, filter, false);
		    
            if (abList != null && abList.size() > 0) {
                ab = abList.get(0);
                Long abOwnerId = ab.getOwnerId();
                
                // if ownerIds don't match, then another user has the same contact in their address book
                if (ownerId.equals(abOwnerId)) {
                	return mergeAndUpdate(contact, ab);
                }
            }
        }
                
        if (refUser != null) {
            contact.setRefUserId(refUser.getId());
        }
        
        getMapper().insert(contact);
        
        //updateCoords(contact.getId());
        
        return contact;
    }
    

//    @Override @Transactional
//    public CONTACT update(CONTACT contact) {
//        contact = super.update(contact);
//        updateCoords(contact.getId());
//        return contact;
//    }


    @Override @Transactional
    public void remove(CONTACT contact) {
        if (contact.getPhotoId() != null) {
            getMediaService().removeById(contact.getPhotoId());
        }
                
        super.remove(contact);
    }
    

    @Override @Transactional
    public CONTACT updatePhoto(CONTACT contact, Long photoId, String urlPath, String thumbnailUrlPath) {
        // NOTE: photoId references UserMedia object

        contact.setPhotoId(photoId);
        contact.setPhotoUrl(urlPath);
        contact.setThumbnailUrl(thumbnailUrlPath);

        return update(contact);
    }
    

    @Override @Transactional
    public CONTACT removePhoto(CONTACT contact) {
        if (contact.getPhotoId() != null) {
            getMediaService().removeById(contact.getPhotoId());
        }

        contact.setPhotoId(null);
        contact.setPhotoUrl(null);
        contact.setThumbnailUrl(null);

        return update(contact);
    }
    

    public CONTACT updatePhoto(CONTACT contact, byte[] data, String contentType) throws IOException {

        USER user = getUserService().fetchById(contact.getOwnerId());
        
        String name = "addressbook-photo-" + contact.getUid();

        MEDIA media = getMediaService().add(user, name, data, contentType);
        
        return updatePhoto(contact, media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());
    }


    @Override
    // TODO: complete this validation for rest of fields
    public void validate(CONTACT contact) {
    	if (contact.getCreatedDate() == null) {
    		contact.setCreatedDate(new Date());
    	}
    	
    	contact.setUpdatedDate(new Date());
    	
    	if (contact.getUid() == null) {
    	    contact.setUid(uuid());
        }
    	
    	scrub(contact);
    }

    protected void scrub(CONTACT contact) {
    	String email = contact.getEmail();

    	if (email != null) {
    		if (KValidator.isEmail(email.trim())) {
    			email = null;
    		} else {
    			email = email.trim();
    		}
    	}

    	contact.setEmail(email);
    	
    	String mobileNumber = contact.getMobileNumber();

    	if (mobileNumber != null) {
    		if (!KValidator.isE164PhoneNumber(mobileNumber.trim())) {
    			mobileNumber = null;
    		} else {
    			mobileNumber = mobileNumber.trim();
    		}
    	}

    	contact.setMobileNumber(mobileNumber);
    	
    	String firstName = contact.getFirstName();
    	if (firstName != null) {
    		if (firstName.trim().length() == 0) {
    			firstName = null;
    		} else {
    			firstName = firstName.trim();
    		}
    	}
    	contact.setFirstName(firstName);
    	
    	String lastName = contact.getLastName();
    	if (lastName != null) {
    		if (lastName.trim().length() == 0) {
    			lastName = null;
    		} else {
    			lastName = lastName.trim();
    		}
    	}

    	contact.setLastName(lastName);
    }
    


    private CONTACT mergeAndUpdate(CONTACT newItem, CONTACT currentItem) {
        Long ownerId1 = newItem.getOwnerId();
        Long ownerId2 = currentItem.getOwnerId();
        
        if (ownerId1 == null || ownerId2 == null || !ownerId1.equals(ownerId2)) {
        	throw new KServiceException("Contact ownerId is null or does not match.");
        }
        
        scrub(currentItem);
        scrub(newItem);
        
        try {
            KClassUtil.copy(newItem, currentItem, true);
            return update(currentItem);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new KServiceException("Error mergeAndUpdate: " + e.getMessage(), e);
        }
    }


    @Override
    public List<CONTACT> fetchByEmail(Long ownerId, String email) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        filter.put("email", email);
		return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    @Override
	public List<CONTACT> fetchByMobileNumber(Long ownerId, String mobileNumber) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        filter.put("mobileNumber", mobileNumber);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    

    @Override
	public List<CONTACT> fetchByExample(CONTACT example) {
		Map<String,Object> filter = KClassUtil.toMap(example);
		return fetchByCriteria(0, 99999, null, filter, false);
	}


	@Override
	public List<CONTACT> fetchByOwnerId(Long ownerId, boolean uninvitedOnly) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
		
		List<CONTACT> list = fetchByCriteria(0, 99999, null, filter, false);
		
        if (!uninvitedOnly) return list;
        
        ArrayList<CONTACT> result = new ArrayList<CONTACT>();
        
        for (CONTACT contact : list) {
            if (contact.getInvitedDate() == null) {
            	result.add(contact);
            }
        }
        
        return result;
	}
    

	@Override
	public List<CONTACT> saveBatch(List<CONTACT> list) {
        ArrayList<CONTACT> result = new ArrayList<CONTACT>();
        
        for (CONTACT contact : list) {
            contact = add(contact);
            result.add(contact);
        }
        
        return result;
	}
    

	@Override
	public CONTACT create(Long ownerId, String mobileNumber, String email, String firstName, String lastName) {
		CONTACT contact = getNewObject();

		contact.setOwnerId(ownerId);
		contact.setMobileNumber(mobileNumber);
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);

		return add(contact);
	}


    @Override
    public List<CONTACT> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {

        Map<String, Object> filter = null;

        if (objectIdList != null && objectIdList.size() > 0) {
            filter = KMyBatisUtil.createFilter("|id", objectIdList);
        }

        List<CONTACT> all = fetchByCriteria(0, 99999, null, filter, false);

        Map<Long, List<CONTACT>> placeMap = new HashMap<>();

        for (CONTACT contact : all) {
            if (contact.getPlaceId() != null) {
                List<CONTACT> contacts = placeMap.get(contact.getPlaceId());

                if (contacts == null) {
                    contacts = new ArrayList<>();
                    placeMap.put(contact.getPlaceId(), contacts);
                }

                contacts.add(contact);
            }
        }


        List<PLACE> places = getPlaceService().fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                new ArrayList<>(placeMap.keySet())
        );

        Set<CONTACT> result = new HashSet<>();

        for (PLACE place : places) {
            List<CONTACT> contacts = placeMap.get(place.getId());

            if (contacts != null) {
                result.addAll(contacts);
            }

        }

        return new ArrayList<>(result);
    }
}
