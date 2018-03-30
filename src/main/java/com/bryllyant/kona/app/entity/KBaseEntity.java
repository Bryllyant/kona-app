package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KJsonUtil;

public class KBaseEntity implements KEntityObject {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toJSON() {
        return KJsonUtil.toJson(this);
    }

    public String toString() {
        return KJsonUtil.toJson(this, 255, 5000);
    }

}
