package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseFriendship.Status;
import java.io.Serializable;
import java.util.Date;

public class Friendship extends BaseFriendship implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.uid
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.user_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.friend_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Long friendId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.circle_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Long circleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.status
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Status status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.friendship_requested
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private boolean friendshipRequested;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.created_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.updated_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.id
     *
     * @return the value of kona__friendship.id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.id
     *
     * @param id the value for kona__friendship.id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.uid
     *
     * @return the value of kona__friendship.uid
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.uid
     *
     * @param uid the value for kona__friendship.uid
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.user_id
     *
     * @return the value of kona__friendship.user_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.user_id
     *
     * @param userId the value for kona__friendship.user_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.friend_id
     *
     * @return the value of kona__friendship.friend_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.friend_id
     *
     * @param friendId the value for kona__friendship.friend_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.circle_id
     *
     * @return the value of kona__friendship.circle_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Long getCircleId() {
        return circleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.circle_id
     *
     * @param circleId the value for kona__friendship.circle_id
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.status
     *
     * @return the value of kona__friendship.status
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.status
     *
     * @param status the value for kona__friendship.status
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.friendship_requested
     *
     * @return the value of kona__friendship.friendship_requested
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public boolean isFriendshipRequested() {
        return friendshipRequested;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.friendship_requested
     *
     * @param friendshipRequested the value for kona__friendship.friendship_requested
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setFriendshipRequested(boolean friendshipRequested) {
        this.friendshipRequested = friendshipRequested;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.created_date
     *
     * @return the value of kona__friendship.created_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.created_date
     *
     * @param createdDate the value for kona__friendship.created_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.updated_date
     *
     * @return the value of kona__friendship.updated_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.updated_date
     *
     * @param updatedDate the value for kona__friendship.updated_date
     *
     * @mbg.generated Sat May 26 14:51:47 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}