package com.bryllyant.kona.app.api.model.friendship;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.rest.model.KBaseModel;

import java.util.Date;

public class FriendshipRequest extends KBaseModel {

	private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------

    public enum FriendshipType {
        FOLLOW,
        FRIEND
    }

    // ---------------------------------------------------------------
	
	private FriendshipType type;

	@RestdocsNotExpanded
	private UserModel friend;

	@RestdocsNotExpanded
	private FriendshipCircleModel circle;

	private String mobileNumber;

	private Date createdDate;

    // ---------------------------------------------------------------

    public FriendshipType getType() {
        return type;
    }

    public void setType(FriendshipType type) {
        this.set("type", type);
    }

    public UserModel getFriend() {
        return friend;
    }

    public void setFriend(UserModel friend) {
        this.set("friend", friend);
    }

    public FriendshipCircleModel getCircle() {
        return circle;
    }

    public void setCircle(FriendshipCircleModel circle) {
        this.set("circle", circle);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }


    // ----------------------------------------------------------------------
    
  

    

}
