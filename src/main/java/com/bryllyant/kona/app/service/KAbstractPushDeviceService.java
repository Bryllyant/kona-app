package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KPush.Platform;
import com.bryllyant.kona.app.entity.KPushDevice;
import com.bryllyant.kona.app.entity.KPushProvider;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserDevice;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class KAbstractPushDeviceService<
        PUSH_DEVICE extends KPushDevice,
        PUSH_DEVICE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PUSH_DEVICE, PUSH_DEVICE_EXAMPLE>,
        USER extends KUser,
        DEVICE extends KDevice,
        USER_DEVICE extends KUserDevice,
        PUSH_PROVIDER extends KPushProvider>
        extends KAbstractService<PUSH_DEVICE,PUSH_DEVICE_EXAMPLE,MAPPER>
        implements KPushDeviceService<PUSH_DEVICE,USER,DEVICE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPushDeviceService.class);

    protected abstract <S extends KPushProviderService<PUSH_PROVIDER>> S getPushProviderService();
    protected abstract <S extends KUserDeviceService<USER_DEVICE,USER,DEVICE>> S getUserDeviceService();

    protected abstract PUSH_DEVICE getNewObject();

    @Override @Transactional
    public PUSH_DEVICE add(PUSH_DEVICE pushDevice) {
        return this.save(pushDevice);
    }

    @Override @Transactional
    public PUSH_DEVICE update(PUSH_DEVICE pushDevice) {
        return this.save(pushDevice);
    }

    @Override @Transactional
    public void remove(PUSH_DEVICE pushDevice) {
        String endpoint = pushDevice.getEndpoint();

        super.remove(pushDevice);

        if (endpoint != null) {
            getPushProviderService().deleteDeviceEndpoint(endpoint);
        }
    }

    @Override
    public void validate(PUSH_DEVICE pushDevice) {
        if (pushDevice.getCreatedDate() == null) {
            pushDevice.setCreatedDate(new Date());
        }

        if (pushDevice.getUid() == null) {
            pushDevice.setUid(uuid());
        }

        pushDevice.setUpdatedDate(new Date());


        if (pushDevice.getEndpoint() == null
                && pushDevice.getProviderId() != null
                && pushDevice.getUserId() != null
                && pushDevice.getDeviceId() != null
                && pushDevice.getPlatform() != null
                && pushDevice.getToken() != null) {
            String endpoint = createEndpoint(pushDevice, true);
            pushDevice.setEndpoint(endpoint);
        }
    }


    protected List<PUSH_DEVICE> fetchAllByUserIds(List<Long> userIdList, boolean sandbox) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("|userId", userIdList)
                .and("sandbox", sandbox)
                .build();

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<PUSH_DEVICE> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<PUSH_DEVICE> fetchByDeviceId(Long deviceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("deviceId", deviceId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public PUSH_DEVICE fetchByUserIdAndDeviceId(Long userId, Long deviceId, boolean sandbox) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("deviceId", deviceId);
        filter.put("sandbox", sandbox);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PUSH_DEVICE fetchByToken(String token) {
        // token is a nullable column.
        // without this check, it would look for push_device where push_token is null
        if (token == null) return null;
        Map<String,Object> filter = KMyBatisUtil.createFilter("token", token);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PUSH_DEVICE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override @Transactional
    public PUSH_DEVICE save(
            Long userId,
            Long deviceId,
            String token,
            Platform platform,
            boolean sandbox
    ) {

        PUSH_PROVIDER provider = getPushProviderService().fetchByPlatformAndSandbox(platform, sandbox);

        PUSH_DEVICE pushDevice = getNewObject();
        pushDevice.setUserId(userId);
        pushDevice.setDeviceId(deviceId);
        pushDevice.setProviderId(provider.getId());
        pushDevice.setToken(token);
        pushDevice.setPlatform(platform);
        pushDevice.setSandbox(sandbox);
        pushDevice.setEnabled(true);

        return save(pushDevice);
    }


    /**
     * DEVICE object is not intended to be a saved entity but just a collection of properties.
     * osName property must be set.  All others are optional.
     */
    @Override @Transactional
    public PUSH_DEVICE save(USER user, DEVICE device, String token, boolean sandbox) {
        Long deviceId = null;

        token = token.trim();

        String osName = device.getOsName();

        osName = osName.trim().toLowerCase();

        String advertiserId = device.getAdvertiserId();

        if (advertiserId != null) {
            advertiserId = advertiserId.trim().toUpperCase();
        }

        String deviceUuid = device.getDeviceUuid();

        if (deviceUuid != null) {
            deviceUuid = deviceUuid.trim().toUpperCase();
        }

        Platform platform = getPushProviderService().getPlatform(osName, false);

        if (platform == null) {
            throw new KPushException("Invalid OS: " + osName);
        }

        device.setAdvertiserId(advertiserId);
        device.setDeviceUuid(deviceUuid);
        device.setOsName(osName);
        device.setEnabled(true);

        USER_DEVICE userDevice = null;

        PUSH_DEVICE pushDevice = null;

        List<USER_DEVICE> userDeviceList = getUserDeviceService().fetchByUserId(user.getId());

        if (userDeviceList == null || userDeviceList.size() == 0) {
            userDevice = getUserDeviceService().create(user, device, null);
        } else if (advertiserId != null) {
            for (USER_DEVICE ud : userDeviceList) {
                if (ud.getAdvertiserId() != null && ud.getAdvertiserId().equalsIgnoreCase(advertiserId)) {
                    userDevice = ud;
                    break;
                }
            }
        } else if (deviceUuid != null) {
            for (USER_DEVICE ud : userDeviceList) {
                if (ud.getDeviceUuid() != null && ud.getAdvertiserId().equalsIgnoreCase(deviceUuid)) {
                    userDevice = ud;
                    break;
                }
            }
        } else {
            // we have at least one device and no advertiserId or deviceUuid
            // check if we have a matching platform, if so just grab the first match.
            for (USER_DEVICE ud : userDeviceList) {
                if (ud.getOsName() != null && ud.getOsName().equalsIgnoreCase(osName)) {
                    userDevice = ud;

                    // check if this device is already assigned to this token
                    pushDevice = fetchByUserIdAndDeviceId(user.getId(), ud.getDeviceId(), sandbox);

                    if (pushDevice != null
                            && pushDevice.getToken() != null
                            && pushDevice.getToken().equalsIgnoreCase(token)) {
                        break;
                    }

                    pushDevice = null;
                }
            }
        }

        // if don't have a device at this point, create a new one for this user and platform
        if (userDevice == null) {

            userDevice = getUserDeviceService().create(user, device, null);
        }

        deviceId = userDevice.getDeviceId();


        if (pushDevice != null) {
            pushDevice = fetchByUserIdAndDeviceId(user.getId(), deviceId, sandbox);
        }

        // sanity check - see if this push token is tied to another
        PUSH_DEVICE otherPushDevice = fetchByToken(token);

        if (otherPushDevice != null && (pushDevice == null || !pushDevice.getId().equals(otherPushDevice.getId()))) {
            logger.info("Push token already assigned to different device:\n other device: ${otherPushDevice}\n current push device: ${pushDevice}");
            throw new KPushException("Push token already assigned to a different device");
        }

        if (pushDevice == null) {
            PUSH_PROVIDER provider =
                    getPushProviderService()
                            .fetchByPlatformAndSandbox(platform, sandbox);

            pushDevice = getNewObject();
            pushDevice.setUserId(user.getId());
            pushDevice.setDeviceId(deviceId);
            pushDevice.setProviderId(provider.getId());
        }

        pushDevice.setToken(token);
        pushDevice.setSandbox(sandbox);

        return save(pushDevice);
    }


    @Override @Transactional
    public PUSH_DEVICE save(PUSH_DEVICE pushDevice) {
        Long userId = pushDevice.getUserId();
        Long deviceId = pushDevice.getDeviceId();
        String token = pushDevice.getToken();
        boolean sandbox = pushDevice.isSandbox();

        String oldToken = null;

        PUSH_DEVICE current = fetchByUserIdAndDeviceId(userId, deviceId, sandbox);

        if (current != null) {
            if (pushDevice.getId() == null) {
                pushDevice.setId(current.getId());
            } else {
                if (!current.getId().equals(pushDevice.getId())) {
                    throw new KPushException("Existing PushDevice exists: ${pushDevice}");
                }
            }

            oldToken = current.getToken();

            if (oldToken != null && token != null && (token == null || oldToken.equals(token))) {
                pushDevice.setToken(oldToken);

                if (pushDevice.getEndpoint() == null) {
                    pushDevice.setEndpoint(current.getEndpoint());
                } else {
                    if (!pushDevice.getEndpoint().equalsIgnoreCase(current.getEndpoint())) {
                        throw new KPushException("Push endpoint mismatch:\ndevice: ${pushDevice}\ncurrent: ${current}");
                    }
                }

                if (pushDevice.getProviderId() == null) {
                    pushDevice.setProviderId(current.getProviderId());
                } else {
                    if (!pushDevice.getProviderId().equals(current.getProviderId())) {
                        throw new KPushException("Push provider mismatch:\ndevice: ${pushDevice}\ncurrent: ${current}");
                    }
                }
            }
        }

        // sanity check
        // see if we already have an endpoint for this token
        if (token != null) {
            PUSH_DEVICE device = fetchByToken(token);

            // if token exists and doesn't already belong to us then throw an exception
            if (device != null && (pushDevice.getId() == null || !pushDevice.getId().equals(device.getId()))) {
                throw new KPushException("Token already assigned to another device: " + device);
            }
        }

        if (pushDevice.getId() == null) {
            pushDevice.setEnabled(true);
            pushDevice = super.add(pushDevice);
        } else {
            pushDevice = super.update(pushDevice);
        }

        return pushDevice;
    }


    @Override @Transactional
    public String createEndpoint(PUSH_DEVICE device, boolean deleteIfExists) {
        String token = device.getToken();

        if (token == null) {
            throw new KPushException("AppId and Token must be set");
        }

        PUSH_PROVIDER provider = getPushProviderService().fetchById(device.getProviderId());

        String pushEndpoint = provider.getEndpoint();

        if (pushEndpoint == null) {
            throw new KPushException("PushProvider is null");
        }

        // if we have an existing endpoint, delete it first
        String endpoint = device.getEndpoint();

        if (endpoint != null && deleteIfExists) {
            getPushProviderService().deleteDeviceEndpoint(endpoint);
        }

        endpoint = getPushProviderService().createDeviceEndpoint(device.getPlatform(), pushEndpoint, token, null);

        if (endpoint == null) {
            throw new KPushException("Unable to create endpoint for device: " + device);
        }

        return endpoint;
    }


    @Override
    public List<PUSH_DEVICE> fetchByUserIds(List<Long> userIdList, boolean sandbox) {
        List<PUSH_DEVICE> allDevices = fetchAllByUserIds(userIdList, sandbox);

        Map<Long, PUSH_DEVICE> map = new HashMap<>();

        for (PUSH_DEVICE pushDevice : allDevices) {
            if (!pushDevice.isEnabled()) {
                continue;
            }

            if (!map.containsKey(pushDevice.getDeviceId())) {
                map.put(pushDevice.getDeviceId(), pushDevice);
            } else {
                PUSH_DEVICE nextDevice = map.get(pushDevice.getDeviceId());
                nextDevice = chooseDevice(pushDevice, nextDevice);
                map.put(pushDevice.getDeviceId(), nextDevice);
            }
        }

        return new ArrayList<PUSH_DEVICE>(map.values());
    }
//    @Override
//    public List<PUSH_DEVICE> fetchByUserIds(List<Long> userIdList, boolean sandbox) {
//        List<PUSH_DEVICE> all = fetchAllByUserIds(userIdList, sandbox);
//
//        List<PUSH_DEVICE> pushDevices = new ArrayList<>(all.size());
//        Map<Long,PUSH_DEVICE> deviceMap = new HashMap<>();
//        for (PUSH_DEVICE pushDevice : all) {
//            if (!deviceMap.containsKey(pushDevice.getDeviceId())) {
//                deviceMap.put(pushDevice.getDeviceId(), pushDevice);
//            }
//        }
//    }

    private PUSH_DEVICE chooseDevice(PUSH_DEVICE prevDevice, PUSH_DEVICE nextDevice) {
        // choose the last one created
        if (prevDevice.getCreatedDate().getTime() > nextDevice.getCreatedDate().getTime()) {
            return prevDevice;
        } else {
            return nextDevice;
        }
    }
}
