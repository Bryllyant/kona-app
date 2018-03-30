package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KProduct;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KProductService<PRODUCT extends KProduct>
        extends KService, KEntityService<PRODUCT> {

    PRODUCT fetchByUid(String uid);

    PRODUCT fetchBySlug(String slug);

    List<PRODUCT> fetchAll(Boolean enabled);
}
