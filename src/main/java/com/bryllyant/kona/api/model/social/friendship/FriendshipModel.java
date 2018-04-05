package com.bryllyant.kona.api.model.social.friendship;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class FriendshipModel extends KJsonModel implements KEntityModel {

	private static final long serialVersionUID = 1L;
	
	private String uid;

	@RestdocsNotExpanded
	private UserModel friend;

	private Friendship.Status status;

	private Date createdDate;


    public static FriendshipModel create(String uid) {
        FriendshipModel model = new FriendshipModel();
        model.setUid(uid);
        return model;
    }


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

    public Friendship.Status getStatus() {
        return status;
    }

    public void setStatus(Friendship.Status status) {
        this.set("status", status);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
