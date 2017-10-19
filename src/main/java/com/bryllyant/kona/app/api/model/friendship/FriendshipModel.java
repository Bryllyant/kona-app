package com.bryllyant.kona.app.api.model.friendship;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.KFriendshipStatus;
import com.bryllyant.kona.rest.model.KBaseModel;
import com.bryllyant.kona.rest.model.KEntityModel;

import java.util.Date;

public class FriendshipModel extends KBaseModel implements KEntityModel {

	private static final long serialVersionUID = 1L;
	
    // ---------------------------------------------------------------
	
	private String uid;

	@RestdocsNotExpanded
	private UserModel friend;

	private KFriendshipStatus status;

	private Date createdDate;

    // ----------------------------------------------------------------------
    
    public static FriendshipModel create(String uid) {
        FriendshipModel model = new FriendshipModel();
        model.setUid(uid);
        return model;
    }

    // ----------------------------------------------------------------------

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getFriend() {
        return friend;
    }

    public void setFriend(UserModel friend) {
        this.set("friend", friend);
    }

    public KFriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(KFriendshipStatus status) {
        this.set("status", status);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }


    // ---------------------------------------------------------------

    

}
