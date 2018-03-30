package com.bryllyant.kona.api.model.user;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.geo.position.PositionModel;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.geo.position.PositionModel;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PositionRequest extends KJsonModel {

	private static final long serialVersionUID = 1L;

	@RestdocsNotExpanded
	private DeviceModel device;
	
	@NotNull
	@RestdocsNotExpanded
	private List<PositionModel> positions;


    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.set("device", device);
    }

    public List<PositionModel> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionModel> positions) {
        this.set("positions", positions);
    }

}
