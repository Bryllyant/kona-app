/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApiVersion;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractApiVersionService<API_VERSION extends KApiVersion, API_VERSION_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<API_VERSION, API_VERSION_EXAMPLE>>
		extends KAbstractService<API_VERSION,API_VERSION_EXAMPLE,MAPPER>
		implements KApiVersionService<API_VERSION> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractApiVersionService.class);
    

    
    @Override
    public API_VERSION fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    
    @Override
    public API_VERSION fetchLatest() {
        Map<String,Object> filter = null;

        // FIXME: do not hard code table or column names
        String[] sortOrder = { "published_date" };

        List<API_VERSION> result = fetchByCriteria(0, 99999, sortOrder, filter, false);

        return result.get(result.size() - 1);
    }
    

    
	@Override
	public void validate(API_VERSION apiVersion) {
	   	 if (apiVersion.getCreatedDate() == null) {
    		 apiVersion.setCreatedDate(new Date());
    	 }
         apiVersion.setUpdatedDate(new Date());
	}
}

