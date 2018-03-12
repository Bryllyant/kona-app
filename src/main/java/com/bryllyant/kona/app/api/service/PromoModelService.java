package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.promo.PromoModel;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromoModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(PromoModelService.class);
    
    @Autowired
    private PromoService promoService;

    @Autowired
    private ApiUtil util;



    public Promo getPromo(String uid) {
        Promo promo = promoService.fetchByUid(uid);

        if (promo == null) {
            throw new NotFoundException("Promo not found for uid: " + uid);
        }

        return promo;
    }



    public Promo getPromo(Long promoId) {
        Promo promo = promoService.fetchById(promoId);

        if (promo == null) {
            throw new NotFoundException("Promo not found for id: " + promoId);
        }

        return promo;
    }
    
    


    public Promo getPromo(PromoModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Promo not found for model: " + model);
        }

        return getPromo(uid);
    }
   


    public PromoModel toModel(Promo promo, String... includeKeys) {
        if (promo == null) return null;

        PromoModel model = new PromoModel();
        
        model.fromBean(promo);
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<PromoModel> toModelList(List<Promo> promos, String... includeKeys) {
        List<PromoModel> modelList = new ArrayList<>();

        for (Promo promo : promos) {
            modelList.add(toModel(promo, includeKeys));
        }

        return modelList;
    }



    public Promo toEntity(PromoModel model) {
        Promo promo = new Promo();

        return mergeEntity(promo, model);
    }

    


    public Promo mergeEntity(Promo promo, PromoModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, promo);

        for (String key : model.initializedKeys()) {

            switch (key) {

            }

        }

        return promo;
    }
    

   
}
