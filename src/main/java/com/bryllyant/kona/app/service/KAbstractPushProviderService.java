package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KPushProvider;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class KAbstractPushProviderService<
            PUSH_PROVIDER extends KPushProvider,
            PUSH_PROVIDER_EXAMPLE extends KEntityExample,
            MAPPER extends KMyBatisMapper<PUSH_PROVIDER, PUSH_PROVIDER_EXAMPLE>,
            APP extends KApp>
        extends KAbstractAWSPushProviderService<PUSH_PROVIDER,PUSH_PROVIDER_EXAMPLE,MAPPER,APP>
        implements KPushProviderService<PUSH_PROVIDER> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPushProviderService.class);

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }
}
