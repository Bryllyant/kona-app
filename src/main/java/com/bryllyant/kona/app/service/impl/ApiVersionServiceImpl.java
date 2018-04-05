/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ApiVersionMapper;
import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.app.entity.ApiVersionExample;
import com.bryllyant.kona.app.service.ApiVersionService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(ApiVersionService.SERVICE_PATH)
public class ApiVersionServiceImpl 
		extends KAbstractService<ApiVersion, ApiVersionExample, ApiVersionMapper>
		implements ApiVersionService {
	
	private static Logger logger = LoggerFactory.getLogger(ApiVersionServiceImpl.class);

	@Autowired
	private ApiVersionMapper apiVersionMapper;
	

	@Override @SuppressWarnings("unchecked")
	protected ApiVersionMapper getMapper() {
		return apiVersionMapper;
	}

    @Override
    public void validate(ApiVersion apiVersion) {
        if (apiVersion.getCreatedDate() == null) {
            apiVersion.setCreatedDate(new Date());
        }

        apiVersion.setUpdatedDate(new Date());

//        if (apiVersion.getUid() == null) {
//            apiVersion.setUid(uuid());
//        }
    }

    @Override
    public ApiVersion fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public ApiVersion fetchLatest() {
        Map<String,Object> filter = null;

        // FIXME: do not hard code table or column names
        String[] sortOrder = { "published_date" };

        List<ApiVersion> result = fetchByCriteria(0, 99999, sortOrder, filter, false);

        return result.get(result.size() - 1);
    }
}
