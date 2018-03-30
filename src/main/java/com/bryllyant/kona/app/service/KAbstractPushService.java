package com.bryllyant.kona.app.service;

import com.amazonaws.services.sns.model.AmazonSNSException;
import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KAppUser;
import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KPush.Platform;
import com.bryllyant.kona.app.entity.KPushDevice;
import com.bryllyant.kona.app.entity.KPushProvider;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KPositionUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class KAbstractPushService<
        PUSH extends KPush,
        PUSH_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PUSH, PUSH_EXAMPLE>,
        APP extends KApp,
        USER extends KUser,
        DEVICE extends KDevice,
        APP_USER extends KAppUser,
        PUSH_PROVIDER extends KPushProvider,
        PUSH_DEVICE extends KPushDevice>
        extends KAbstractService<PUSH,PUSH_EXAMPLE,MAPPER>
        implements KPushService<PUSH> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPushService.class);

    protected abstract PUSH getNewObject();

    protected abstract <S extends KAppService<APP>> S getAppService();

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KAppUserService<APP_USER>> S getAppUserService();

    protected abstract <S extends KPushDeviceService<PUSH_DEVICE,USER,DEVICE>> S getPushDeviceService();

    protected abstract <S extends KPushProviderService<PUSH_PROVIDER>> S getPushProviderService();


    @Override
    public void validate(PUSH push) {
        if (push.getCreatedDate() == null) {
            push.setCreatedDate(new Date());
        }

        if (push.getUid() == null) {
            push.setUid(uuid());
        }

        push.setUpdatedDate(new Date());
    }


    @Override
    public PUSH fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<PUSH> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<PUSH> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public PUSH fetchByProviderMessageId(String providerMessageId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("providerMessageId", providerMessageId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<PUSH> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public void broadcast(PUSH push, List<Long> userIds, Double latitude, Double longitude, Double radius) {

        if (radius != null) {
            radius = KPositionUtil.toMeters(radius);
        }

        Map<String,Object> filter = new HashMap<>();
        filter.put("userIds", userIds);
        filter.put("latitude", latitude);
        filter.put("longitude", longitude);
        filter.put("radius", radius);

        broadcast(push, filter);
    }


    @Override
    // filter currently only supports: latitude, longitude, radius, userIds
    public void broadcast(
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            Map<String, Object> filter,
            boolean sandbox
    ) {

        PUSH push = getNewObject();

        push.setSandbox(sandbox);
        push.setTitle(title);
        push.setMessage(message);
        push.setImageUrl(imageUrl);
        push.setActionUrl(actionUrl);

        broadcast(push, filter);
    }


    @Override
    public void broadcast(final PUSH push, final Map<String, Object> filter) {

        push.setId(null); // make sure we're creating a new object

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    logger.debug("blast thread running ...");
                    blast(push, filter);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        };

        t.start();
    }


    protected Map<String, Object> createPayLoadData(PUSH push) {
        APP app = getAppService().getSystemApp();

        Map<String, Object> data = new HashMap<>();

        data.put("source", app.getName());

        if (push.getTitle() != null) {
            data.put("title", push.getTitle());
        }

        if (push.getActionUrl() != null) {
            data.put("action_url", push.getActionUrl());
        }

        if (push.getImageUrl() != null) {
            data.put("image_url", push.getImageUrl());
        }

        data.put("push_uid", push.getUid());

        return data;
    }

    @Override
    public PUSH send (
            Long userId,
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            boolean sandbox
    ) {
        PUSH push = getNewObject();

        push.setUserId(userId);
        push.setSandbox(sandbox);
        push.setTitle(title);
        push.setMessage(message);
        push.setImageUrl(imageUrl);
        push.setActionUrl(actionUrl);

        return send(push);
    }

    // TODO: make sure push object contains required fields
    private void validateMessage(PUSH push) {

    }

    @Override
    public PUSH send(PUSH push) {

        validateMessage(push);

        List<Long> userIds = new ArrayList<>();

        userIds.add(push.getUserId());

        Map<String, Object> filter = new HashMap<>();

        filter.put("userIds", userIds);

        List<PUSH> result = blast(push, filter);

        if (result.size() != 1) {
            throw new KPushException("Error sending push notification");
        }

        return result.get(0);
    }

    public PUSH send(PUSH_DEVICE device, PUSH push) {
        logger.debug("send: push device: " + device);
        logger.debug("send: push object: " + push);

        Platform platform = device.getPlatform();

        String endpoint = device.getEndpoint();

        if (platform == null || endpoint == null) {
            return null;
        }

        if (push.getUserId() == null) {
            push.setUserId(device.getUserId());
        }

        // sanity check
        if (!push.getUserId().equals(device.getUserId())) {
            throw new KPushException("Error: Push user does not match device owner.\npush: ${push}\ndevice: ${device}");
        }


        push.setId(null); // ensure new object
        push.setPlatform(device.getPlatform());
        push.setSandbox(device.isSandbox()); // force sandbox value to device's state
        push.setDeviceId(device.getId());
        push.setSentDate(new Date());

        validateMessage(push);

        push = add(push);

        // Make sure to add the object before creating the payload since createPayLoadData will use push.getUid()
        Map<String, Object> data = createPayLoadData(push);

        try {
            String providerMessageId = getPushProviderService().publish(platform, endpoint, push.getMessage(), data);

            push.setProviderMessageId(providerMessageId);

            logger.debug("\n--------------------------\n"
                    + "\npush message:"
                    + "\nplatform: " + platform
                    + "\nendpoint: " + endpoint
                    + "\nproviderMessageId: " + providerMessageId
                    + "\npush: " + push
                    + "\ndata: " + data
                    + "\n--------------------------\n"
            );
        } catch (Throwable t) {
            if (t instanceof AmazonSNSException) {
                AmazonSNSException ex = (AmazonSNSException) t;
                push.setErrorCode(ex.getErrorCode());
                push.setErrorMessage(ex.getErrorMessage());
            } else {
                push.setErrorMessage(t.getMessage());
            }
        }

        return update(push);
    }


    protected List<PUSH> blast(PUSH push, Map<String, Object> filter) {
        logger.debug("blast called for push: " + push);

        if (filter == null) {
            throw new KPushException("Error blast filter not defined.");
        }

        List<PUSH_DEVICE> deviceList = getDeviceList(filter, push.isSandbox());

        List<PUSH> deliveries = new ArrayList<>();

        for (PUSH_DEVICE device : deviceList) {
            logger.debug("blast push device: " + device);

            PUSH delivery = send(device, push);
            deliveries.add(delivery);
        }

        return deliveries;
    }

    private List<PUSH_DEVICE> getDeviceList(Map<String,Object> filter, boolean sandbox) {
        logger.debug("getDeviceList called for filter: " + KJsonUtil.toJson(filter));

        List<Long> userIdList = getUserIdList(filter);

        if (userIdList == null) {
            logger.debug("getDeviceList: getUserIdList returned null");
            return null;
        }

        List<PUSH_DEVICE> result = getPushDeviceService().fetchByUserIds(userIdList, sandbox);

        logger.debug("getDeviceList: fetchByUserIds: result size: " + result.size());

        return result;
    }


    @SuppressWarnings("unchecked")
    private List<Long> getUserIdList(Map<String,Object> filter) {
        logger.debug("getUserIdList called for filter: " + KJsonUtil.toJson(filter));

        List<Long> userIdList = null;
        Double latitude = null;
        Double longitude = null;
        Double radius = null; // in meters

        // NOTE: filter is serialized as Map<String,String> so explicitly convert types
        if (filter != null) {
            Object value = filter.get("latitude");
            if (value != null) {
                latitude = Double.valueOf(value.toString());
            }

            value = filter.get("longitude");
            if (value != null) {
                longitude = Double.valueOf(value.toString());
            }

            value = filter.get("radius");
            if (value != null) {
                radius = Double.valueOf(value.toString());
            }

            value = filter.get("userIds");
            if (value != null) {
                userIdList = new ArrayList<Long>();

                List<Object> list = (List<Object>) value;

                for (Object o : list) {
                    userIdList.add(Long.valueOf(o.toString()));
                }
            }
        }

        if (userIdList == null) {
            userIdList = new ArrayList<Long>();
        }

        List<USER> userList = null;


        // first see if we have a geofence
        if (latitude != null && longitude != null && radius != null) {
            // see if we have users who were within the geofence within the last hour
            Date startDate = KDateUtil.addHours(new Date(), 1);

            userList = getUserService().fetchProximate(latitude, longitude, radius, startDate, null);

            logger.debug("getUserIdList: haveGeoFence: matched userList count: " + userList.size());
        } else {
            userList = getUserService().fetchAllRegistered(null);
        }

        if (userList != null) {
            for (USER user : userList) {
                userIdList.add(user.getId());
            }
        }

        return userIdList;
    }
}
