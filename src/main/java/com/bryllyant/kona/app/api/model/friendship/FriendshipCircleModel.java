package com.bryllyant.kona.app.api.model.friendship;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.rest.model.KBaseModel;

import java.util.Date;

public class FriendshipCircleModel extends KBaseModel {

	private static final long serialVersionUID = 1L;
	
    // ---------------------------------------------------------------
	
	private String uid;

	@RestdocsNotExpanded
	private UserModel user;

	private String name;
	private Boolean defaultCircle;
	private Date createdDate;

    // ----------------------------------------------------------------------
    
    public static FriendshipCircleModel create(String uid) {
        FriendshipCircleModel model = new FriendshipCircleModel();
        model.setUid(uid);
        return model;
    }

    // ----------------------------------------------------------------------

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public Boolean getDefaultCircle() {
        return defaultCircle;
    }

    public void setDefaultCircle(Boolean defaultCircle) {
        this.set("defaultCircle", defaultCircle);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }


    // ---------------------------------------------------------------

    

}
