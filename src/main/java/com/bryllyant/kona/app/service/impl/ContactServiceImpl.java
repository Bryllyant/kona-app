package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ContactMapper;
import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.app.entity.ContactExample;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.ContactService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.KClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service(ContactService.SERVICE_PATH)
public class ContactServiceImpl
		extends KAbstractService<Contact, ContactExample, ContactMapper>
		implements ContactService {
	
	private static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactMapper contactMapper;
	
	@Autowired
	UserService userService;

	@Autowired
	MediaService mediaService;

	@Autowired
    PlaceService placeService;

	@Override @SuppressWarnings("unchecked")
	protected ContactMapper getMapper() {
		return contactMapper;
	}

    @Override
    public Contact add(Contact contact) {
        validate(contact);

        // check if similar entry already exists
        Contact ab = null;

        Long ownerId = contact.getOwnerId();

        User refUser = null;

        List<Contact> abList = null;

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

            refUser = userService.fetchByMobileNumber(contact.getMobileNumber());
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
                refUser = userService.fetchByEmail(contact.getEmail());
            }
        }

        // email is null, so check first name, last name and address
        if (contact.getFirstName() != null
                && contact.getLastName() != null
                && contact.getPlaceId() != null) {

            Map<String,Object> filter = KMyBatisUtil.createFilter("firstName", contact.getFirstName());
            filter.put("lastName", contact.getLastName());
            filter.put("placeId", contact.getPlaceId());

            abList = fetchByCriteria(filter);

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
//    public Contact update(Contact contact) {
//        contact = super.update(contact);
//        updateCoords(contact.getId());
//        return contact;
//    }


    @Override @Transactional
    public void remove(Contact contact) {
        if (contact.getPhotoId() != null) {
            mediaService.removeById(contact.getPhotoId());
        }

        super.remove(contact);
    }


    @Override @Transactional
    public Contact updatePhoto(Contact contact, Long photoId, String urlPath, String thumbnailUrlPath) {
        // NOTE: photoId references UserMedia object

        contact.setPhotoId(photoId);
        contact.setPhotoUrl(urlPath);
        contact.setThumbnailUrl(thumbnailUrlPath);

        return update(contact);
    }


    @Override @Transactional
    public Contact removePhoto(Contact contact) {
        if (contact.getPhotoId() != null) {
            mediaService.removeById(contact.getPhotoId());
        }

        contact.setPhotoId(null);
        contact.setPhotoUrl(null);
        contact.setThumbnailUrl(null);

        return update(contact);
    }


    public Contact updatePhoto(Contact contact, byte[] data, String contentType) throws IOException {

        User user = userService.fetchById(contact.getOwnerId());

        String name = "addressbook-photo-" + contact.getUid();

        Media media = mediaService.add(user, name, data, contentType);

        return updatePhoto(contact, media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());
    }


    @Override
    // TODO: complete this validation for rest of fields
    public void validate(Contact contact) {
        if (contact.getCreatedDate() == null) {
            contact.setCreatedDate(new Date());
        }

        contact.setUpdatedDate(new Date());

        if (contact.getUid() == null) {
            contact.setUid(uuid());
        }

        scrub(contact);
    }

    protected void scrub(Contact contact) {
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



    private Contact mergeAndUpdate(Contact newItem, Contact currentItem) {
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
    public List<Contact> fetchByEmail(Long ownerId, String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        filter.put("email", email);
        return fetchByCriteria(filter);
    }


    @Override
    public List<Contact> fetchByMobileNumber(Long ownerId, String mobileNumber) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        filter.put("mobileNumber", mobileNumber);
        return fetchByCriteria(filter);
    }


    @Override
    public List<Contact> fetchByExample(Contact example) {
        Map<String,Object> filter = KClassUtil.toMap(example);
        return fetchByCriteria(filter);
    }


    @Override
    public List<Contact> fetchByOwnerId(Long ownerId, boolean uninvitedOnly) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);

        List<Contact> list = fetchByCriteria(filter);

        if (!uninvitedOnly) return list;

        ArrayList<Contact> result = new ArrayList<Contact>();

        for (Contact contact : list) {
            if (contact.getInvitedDate() == null) {
                result.add(contact);
            }
        }

        return result;
    }


    @Override
    public List<Contact> saveBatch(List<Contact> list) {
        ArrayList<Contact> result = new ArrayList<Contact>();

        for (Contact contact : list) {
            contact = add(contact);
            result.add(contact);
        }

        return result;
    }


    @Override
    public Contact create(Long ownerId, String mobileNumber, String email, String firstName, String lastName) {
        Contact contact = new Contact();

        contact.setOwnerId(ownerId);
        contact.setMobileNumber(mobileNumber);
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);

        return add(contact);
    }


    @Override
    public List<Contact> fetchProximate(
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

        List<Contact> all = fetchByCriteria(filter);

        Map<Long, List<Contact>> placeMap = new HashMap<>();

        for (Contact contact : all) {
            if (contact.getPlaceId() != null) {
                List<Contact> contacts = placeMap.get(contact.getPlaceId());

                if (contacts == null) {
                    contacts = new ArrayList<>();
                    placeMap.put(contact.getPlaceId(), contacts);
                }

                contacts.add(contact);
            }
        }


        List<Place> places = placeService.fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                new ArrayList<>(placeMap.keySet())
        );

        Set<Contact> result = new HashSet<>();

        for (Place place : places) {
            List<Contact> contacts = placeMap.get(place.getId());

            if (contacts != null) {
                result.addAll(contacts);
            }

        }

        return new ArrayList<>(result);
    }
}
