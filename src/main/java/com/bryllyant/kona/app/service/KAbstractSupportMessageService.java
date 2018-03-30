package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KSupportMessage;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KNameParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractSupportMessageService<
SUPPORT_MESSAGE extends KSupportMessage,
SUPPORT_MESSAGE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<SUPPORT_MESSAGE, SUPPORT_MESSAGE_EXAMPLE>,
USER extends KUser> 
extends KAbstractService<SUPPORT_MESSAGE,SUPPORT_MESSAGE_EXAMPLE,MAPPER>
implements KSupportMessageService<SUPPORT_MESSAGE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSupportMessageService.class);

    protected abstract SUPPORT_MESSAGE getNewObject();

    protected abstract <S extends KUserService<USER>> S getUserService();
    
    protected abstract void sendNotification(SUPPORT_MESSAGE message);


    @Override
    public void validate(SUPPORT_MESSAGE supportMessage) {
        if (supportMessage.getCreatedDate() == null) {
            supportMessage.setCreatedDate(new Date());
        }

        if (supportMessage.getUid() == null) {
            supportMessage.setUid(uuid());
        }

        supportMessage.setUpdatedDate(new Date());

        if (supportMessage.getUserId() == null && supportMessage.getEmail() != null) {
            USER user = getUserService().fetchByEmail(supportMessage.getEmail());

            if (user != null) {
                supportMessage.setUserId(user.getId());
            }
        }

        if (supportMessage.getUserId() == null && supportMessage.getMobileNumber() != null) {
            USER user = getUserService().fetchByMobileNumber(supportMessage.getMobileNumber());

            if (user != null) {
                supportMessage.setUserId(user.getId());
            }
        }
        
        if (supportMessage.getUserId() != null 
                && (supportMessage.getFirstName() == null || supportMessage.getLastName() == null)) {

            USER user = getUserService().fetchById(supportMessage.getUserId());

            if (supportMessage.getFirstName() == null) {
                supportMessage.setFirstName(user.getFirstName());
            }

            if (supportMessage.getLastName() == null) {
                supportMessage.setLastName(user.getLastName());
            }
        }
    }



    @Override
    public SUPPORT_MESSAGE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<SUPPORT_MESSAGE> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<SUPPORT_MESSAGE> fetchByEmail(String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<SUPPORT_MESSAGE> fetchByMobileNumber(String mobileNumber) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    @Override
    public SUPPORT_MESSAGE send(SUPPORT_MESSAGE message) {
        message = save(message);
        sendNotification(message);
        return message;
    }



    @Override
    public SUPPORT_MESSAGE send(KServiceClient client, String name, String email, String mobileNumber, String message) {
        SUPPORT_MESSAGE supportMessage = getNewObject();
        
        String firstName = null;
        String lastName = null;
        
        if (name != null) {
            KNameParser parser = KNameParser.parse(name, true);
            firstName = parser.getFirstName();
            lastName = parser.getLastName();
        }

        supportMessage.setUserId(client.getUserId());
        supportMessage.setFirstName(firstName);
        supportMessage.setLastName(lastName);
        supportMessage.setEmail(email);
        supportMessage.setMobileNumber(mobileNumber);
        supportMessage.setMessage(message);
        supportMessage.setHostname(client.getHostname());
        supportMessage.setUserAgent(client.getUserAgent());
        
        return send(supportMessage);
    }

}
