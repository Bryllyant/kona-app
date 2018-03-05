package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.api.model.sales.promo.PromoModel;
import com.bryllyant.kona.app.api.model.user.RegistrationModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationModelService.class);
    
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AccountModelService accountModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppModelService appModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private PartnerModelService partnerModelService;

    @Autowired
    private PromoModelService promoModelService;



    @Autowired
    private ApiUtil util;


    public Registration getRegistration(String uid) {
        Registration registration = registrationService.fetchByUid(uid);

        if (registration == null) {
            throw new NotFoundException("Registration not found for uid: " + uid);
        }

        return registration;
    }


    public Registration getRegistration(Long registrationId) {
        Registration registration = registrationService.fetchById(registrationId);

        if (registration == null) {
            throw new NotFoundException("Registration not found for id: " + registrationId);
        }

        return registration;
    }


    public Registration getRegistration(User user) {
        Registration registration = registrationService.fetchByUserId(user.getId());

        if (registration == null) {
            throw new NotFoundException("Registration not found for user: " + user);
        }

        return registration;
    }
    


    public Registration getRegistration(RegistrationModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Registration not found for model: " + model);
        }

        return getRegistration(uid);
    }
   


    public RegistrationModel toModel(Registration registration, String... includeKeys) {
        if (registration == null) return null;


        RegistrationModel model = new RegistrationModel();
        
        model.fromBean(registration);
        
        if (registration.getAppId() != null) {
            App app = appModelService.getApp(registration.getAppId());
            model.setApp(AppModel.create(app.getUid()));
        }

        if (registration.getAccountId() != null) {
            Account account = accountModelService.getAccount(registration.getAccountId());
            model.setAccount(AccountModel.create(account.getUid()));
        }

        if (registration.getUserId() != null) {
            User owner = userModelService.getUser(registration.getUserId());
            model.setUser(UserModel.create(owner.getUid()));
        }

        if (registration.getDeviceId() != null) {
            Device device = deviceModelService.getDevice(registration.getDeviceId());
            model.setDevice(DeviceModel.create(device.getUid()));
        }

        if (registration.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(registration.getCampaignId());
            model.setCampaign(CampaignModel.create(campaign.getUid()));
        }

        if (registration.getPartnerId() != null) {
            Partner partner = partnerModelService.getPartner(registration.getPartnerId());
            model.setPartner(PartnerModel.create(partner.getUid()));
        }

        if (registration.getPromoId() != null) {
            Promo promo = promoModelService.getPromo(registration.getPromoId());
            model.setPromo(PromoModel.create(promo.getUid()));
        }

        if (registration.getReferredById() != null) {
            User referredBy = userModelService.getUser(registration.getReferredById());
            model.setReferredBy(UserModel.create(referredBy.getUid()));
        }


        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<RegistrationModel> toRegistrationModelList(List<Registration> registrations, String... includeKeys) {
        List<RegistrationModel> modelList = new ArrayList<>();

        for (Registration registration : registrations) {
            modelList.add(toModel(registration, includeKeys));
        }

        return modelList;
    }



    public Registration toEntity(RegistrationModel model) {
        Registration registration = new Registration();

        return mergeEntity(registration, model);
    }

    


    public Registration mergeEntity(Registration registration, RegistrationModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, registration);

        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "user":
                    User user = userModelService.getUser(model.getUser());
                    registration.setUserId(user.getId());
                    break;

                case "referredBy":
                    User referredBy = userModelService.getUser(model.getReferredBy());
                    registration.setReferredById(referredBy.getId());
                    break;

                case "app":
                    App app = appModelService.getApp(model.getApp());
                    registration.setAppId(app.getId());
                    break;

                case "account":
                    Account account = accountModelService.getAccount(model.getAccount());
                    registration.setAccountId(account.getId());
                    break;

                case "device":
                    Device device = deviceModelService.getOrCreateDevice(model.getDevice());
                    registration.setDeviceId(device.getId());
                    break;

                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    registration.setCampaignId(campaign.getId());
                    break;

                case "partner":
                    Partner partner = partnerModelService.getPartner(model.getPartner());
                    registration.setPartnerId(partner.getId());
                    break;

                case "promo":
                    Promo promo = promoModelService.getPromo(model.getPromo());
                    registration.setPromoId(promo.getId());
                    break;
            }

        }

        return registration;
    }
    

   
}
