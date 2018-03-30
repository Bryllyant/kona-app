package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.message.PushDeviceModel;
import com.bryllyant.kona.api.model.message.PushProviderModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.message.PushDeviceModel;
import com.bryllyant.kona.api.model.message.PushProviderModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.PushDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushDeviceModelService extends BaseEntityModelService<PushDeviceModel,PushDevice> {
    private static final Logger logger = LoggerFactory.getLogger(PushDeviceModelService.class);

    @Autowired
    private PushDeviceService entityService;

    @Autowired
    private PushProviderModelService pushProviderModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private UserModelService userModelService;


    protected PushDeviceService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(PushDeviceModel model, PushDevice entity) {
        if (entity.getProviderId() != null) {
            PushProvider provider = pushProviderModelService.getEntity(entity.getProviderId());
            model.setProvider(PushProviderModel.from(provider));
        }

        if (entity.getDeviceId() != null) {
            Device device = deviceModelService.getDevice(entity.getDeviceId());
            model.setDevice(DeviceModel.from(device));
        }

        if (entity.getUserId() != null) {
            User user = userModelService.getUser(entity.getUserId());
            model.setUser(UserModel.from(user));
        }
    }

    protected void setEntityProperty(String key, PushDeviceModel model, PushDevice entity) {
        switch (key) {
            case "provider":
                PushProvider provider = pushProviderModelService.getEntity(model.getProvider());
                entity.setProviderId(provider == null ? null : provider.getId());
                break;

            case "device":
                Device device = deviceModelService.getDevice(model.getDevice());
                entity.setDeviceId(device == null ? null : device.getId());
                break;

            case "toUser":
                User user = userModelService.getUser(model.getUser());
                entity.setUserId(user == null ? null : user.getId());
                break;
        }

    }
}
