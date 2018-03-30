package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KSalesLeadEvent extends KEntityObject {
    enum Type {
        VIEW,
        NOTE,
        REPLY
    }

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getUserId();

    void setUserId(Long userId);

    Long getSalesLeadId();

    void setSalesLeadId(Long salesLeadId);

    Type getType();

    void setType(Type type);

    String getTarget();

    void setTarget(String target);

    String getMessage();

    void setMessage(String message);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
