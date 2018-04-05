package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.geo.Address;
import com.bryllyant.kona.app.model.geo.Point;
import com.bryllyant.kona.data.entity.KBaseEntity;

public abstract class BasePlace extends KBaseEntity implements Point, Address {

}