package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.geo.place.PlaceModel;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.api.model.social.SocialHandleModel;
import com.bryllyant.kona.app.api.model.user.PersonModel;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.service.PartnerService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerModelService.class);
    
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private PlaceModelService placeModelService;

    @Autowired
    private ApiUtil util;


    public Partner getPartner(String uid) {
        Partner partner = partnerService.fetchByUid(uid);

        if (partner == null) {
            throw new NotFoundException("Partner not found for uid: " + uid);
        }

        return partner;
    }


    public Partner getPartner(Long partnerId) {
        Partner partner = partnerService.fetchById(partnerId);

        if (partner == null) {
            throw new NotFoundException("Partner not found for id: " + partnerId);
        }

        return partner;
    }
    
    
    public Partner getPartner(PartnerModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Partner not found for model: " + model);
        }

        return getPartner(uid);
    }
   
    public PartnerModel toModel(Partner partner, String... includeKeys) {
        if (partner == null) return null;

        PartnerModel model = new PartnerModel();
        
        model.fromBean(partner);
        
        // set
        if (partner.getParentId() != null) {
            Partner parent = getPartner(partner.getParentId());
            model.setParent(PartnerModel.create(parent.getUid()));
        }

        if (partner.getPlaceId() != null) {
            Place place = placeModelService.getPlace(partner.getPlaceId());
            model.setPlace(PlaceModel.create(place.getUid()));
        }

        if (partner.getSocialHandles() != null) {
            List<SocialHandleModel> socialHandles = KJsonUtil.fromJson(partner.getSocialHandles(), List.class);
            model.setSocialHandles(socialHandles);
        }

        PersonModel contact = new PersonModel();

        contact.setFirstName(partner.getContactFirstName());
        contact.setLastName(partner.getContactLastName());
        contact.setEmail(partner.getContactEmail());
        contact.setPhoneNumber(partner.getPhoneNumber());
        contact.setMobileNumber(partner.getContactMobileNumber());

        model.setContact(contact);
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    public List<PartnerModel> toModelList(List<Partner> partners, String... includeKeys) {
        List<PartnerModel> modelList = new ArrayList<>();

        for (Partner partner : partners) {
            modelList.add(toModel(partner, includeKeys));
        }

        return modelList;
    }


    public Partner toEntity(PartnerModel model) {
        Partner partner = new Partner();

        return mergeEntity(partner, model);
    }

    

    public Partner mergeEntity(Partner partner, PartnerModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, partner);

        for (String key : model.initializedKeys()) {

            switch (key) {

                case "parent":
                    Partner parent = getPartner(model.getParent());
                    partner.setParentId(parent.getId());
                    break;

                case "place":
                    Place place = placeModelService.getPlace(model.getPlace());
                    partner.setPlaceId(place.getId());
                    break;

                case "socialHandles":
                    String json = KJsonUtil.toJson(model.getSocialHandles());
                    partner.setSocialHandles(json);
                    break;

                case "contact":
                    PersonModel person = model.getContact();
                    partner.setContactFirstName(person.getFirstName());
                    partner.setContactLastName(person.getLastName());
                    partner.setContactEmail(person.getEmail());
                    partner.setContactPhoneNumber(person.getPhoneNumber());
                    partner.setContactMobileNumber(person.getMobileNumber());
                    break;
            }
        }

        return partner;
    }
    

}
