package com.bryllyant.kona.api.service;

import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.data.entity.KEntityObject;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseEntityModelService<MODEL extends KJsonModel&KEntityModel, ENTITY extends KEntityObject> {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityModelService.class);
    
    @Autowired
    private AppUtil util;

    protected abstract <T extends KEntityService<ENTITY>> T getEntityService();

    protected Map<String,Object> toMap(String key, Object value) {
        Map<String,Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public ENTITY getEntity(String uid) {
        ENTITY entity = getEntityService().fetchByUid(uid);

        if (entity == null) {
            throw new NotFoundException("Object not found for uid: " + uid);
        }

        return entity;
    }

    public ENTITY getEntity(Long id) {
        ENTITY entity = getEntityService().fetchById(id);

        if (entity == null) {
            throw new NotFoundException("Object not found for id: " + id);
        }

        return entity;
    }

    public ENTITY getEntity(MODEL model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("CampaignReply not found for model: " + model);
        }

        return getEntity(uid);
    }

    protected abstract void setForeignModelProperties(MODEL model, ENTITY entity);

    public MODEL toModel(ENTITY entity, String... includeKeys) {
        if (entity == null) return null;

        MODEL model = getModelObject();

        model.fromBean(entity);

        setForeignModelProperties(model, entity);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    public List<MODEL> toModelList(List<ENTITY> entityList, String... includeKeys) {
        List<MODEL> modelList = new ArrayList<>();

        for (ENTITY entity : entityList) {
            modelList.add(toModel(entity, includeKeys));
        }

        return modelList;
    }

    public ENTITY toEntity(MODEL model) {
        ENTITY entity = getEntityObject();

        return mergeEntity(entity, model);
    }


    protected abstract void setEntityProperty(String modelKey, MODEL model, ENTITY entity);

    public ENTITY mergeEntity(ENTITY entity, MODEL model) {
        logger.debug("toEntity called for model: " + model);

        util.copyModelToObject(model, entity);

        for (String key : model.initializedKeys()) {
            setEntityProperty(key, model, entity);
        }

        return entity;
    }


    protected MODEL getModelObject() {
        try {
            Class<MODEL> clazz = KClassUtil.getGenericTypeClass(this, 0);
            return clazz.newInstance();
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new SystemException(t);
        }
    }

    protected ENTITY getEntityObject() {
        try {
            Class<ENTITY> clazz = KClassUtil.getGenericTypeClass(this, 1);
            return clazz.newInstance();
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new SystemException(t);
        }
    }
}
