package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KUserRole extends KEntityObject {

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getUserId();
    void setUserId(Long userId);

    Long getRoleId();
    void setRoleId(Long roleId);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
