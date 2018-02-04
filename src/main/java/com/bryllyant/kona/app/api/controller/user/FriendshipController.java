package com.bryllyant.kona.app.api.controller.user;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.social.friendship.FriendshipCircleModel;
import com.bryllyant.kona.app.api.model.social.friendship.FriendshipModel;
import com.bryllyant.kona.app.api.model.social.friendship.FriendshipRequest;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.service.FriendshipModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.KFriendshipStatus;
import com.bryllyant.kona.app.entity.KInvitationType;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ForbiddenException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Friendship Controller.
 */
@RestController
@RequestMapping("/api/friendships")
public class FriendshipController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FriendshipController.class);

    // ----------------------------------------------------------------------

    @Autowired
    private FriendshipService friendshipService;



    @Autowired
    private InvitationService invitationService;

    @Autowired
    private FriendshipModelService friendshipModelService;

    @Autowired
    private UserModelService userModelService;

    // ----------------------------------------------------------------------

    @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<FriendshipModel>> search(
			HttpServletRequest req,
            //@RequestParam(value="friend", required=false) String friendUid,
            @RequestParam(value="followers", required=false) Boolean includeFollowers,
            @RequestParam(value="followings", required=false) Boolean includeFollowings,
            @RequestParam(value="friends", required=false) Boolean includeFriends) {

		logApiRequest(req, "GET /friendships");
		
		if (includeFollowers == null) {
			includeFollowers = true;
		}
		
		if (includeFollowings == null) {
			includeFollowings = true;
		}
		
		if (includeFriends == null) {
			includeFriends = true;
		}
        
		List<Friendship> all = new ArrayList<Friendship>();
        
		/*
		if (friendUid != null) {
			User friend = getUser(friendUid);
			Friendship friendship = friendshipService().fetchByUserIdAndFriendId(getUser().getId(), friend.getId());
            all.add(friendship);
            return ok(toFriendshipMapList(all));
		}
		*/

		if (includeFriends) {
			List<Friendship> friends = 
					friendshipService
					.fetchByUserIdAndStatusId(getUser().getId(), KFriendshipStatus.FRIENDS.getId());
			
			all.addAll(friends);
		}
		
		if (includeFollowers) {
			List<Friendship> followers = 
					friendshipService
					.fetchByUserIdAndStatusId(getUser().getId(), KFriendshipStatus.FOLLOWED.getId());
			
			all.addAll(followers);
		}
		
		if (includeFollowings) {
			List<Friendship> followings = 
					friendshipService
					.fetchByUserIdAndStatusId(getUser().getId(), KFriendshipStatus.FOLLOWING.getId());
			
			all.addAll(followings);
		}
		
		return okList(friendshipModelService.toFriendshipModelList(all));
	}
	
	// ----------------------------------------------------------------------

	@RequestMapping(value="/friends/{friendUid}", method=RequestMethod.GET)
	public ResponseEntity<FriendshipModel> getFriendship(HttpServletRequest req,
			@PathVariable String friendUid) {
		logApiRequest(req, "GET /friendships/friends/" + friendUid);
		
		User friend = userModelService.getUser(friendUid);

		Friendship friendship = 
				friendshipService
				.fetchByUserIdAndFriendId(getUser().getId(), friend.getId());
		
		if (friendship == null) {
			throw new NotFoundException("Friendship not found for friend uid: " + friendUid);
		}

		return ok(friendshipModelService.toModel(friendship));
	}

	// ----------------------------------------------------------------------
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public ResponseEntity<FriendshipModel> get(HttpServletRequest req,
			@PathVariable String uid) {
		logApiRequest(req, "GET /friendships/" + uid);

		return ok(friendshipModelService.toModel(getFriendship(uid)));
	}
	
	// ----------------------------------------------------------------------

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FriendshipModel> follow(HttpServletRequest req,
			@RequestBody FriendshipRequest requestModel) {
		logApiRequest(req, "POST /friendships");

		Friendship friendship = new Friendship();

		friendship = saveObject(friendship, requestModel);

		if (friendship == null) {
		    FriendshipModel model = new FriendshipModel();
		    model.setStatus(KFriendshipStatus.PENDING);
		    return ok(model);
		}

		return created(friendshipModelService.toModel(friendship));
	}

	// ----------------------------------------------------------------------

	@RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
	public ResponseEntity<FriendshipModel> block(HttpServletRequest req,
			@PathVariable String uid,
			@RequestBody Map<String,Object> map) {
		logApiRequest(req, "PUT /friendships/" + uid);

		Friendship friendship = getFriendship(uid);
		
		if (map.get("uid") != null && !map.get("uid").toString().equals(uid)) {
			throw new ValidationException("Object UID does not match requested UID");
		}
        
		if (!friendship.getUserId().equals(getUser().getId())) {
            throw new ForbiddenException("Cannot access friendship uid: " + uid);
		}
        
		Object value = map.get("block");
		
		if (value == null) {
			throw new BadRequestException("block parameter is required");
		}
        
        Boolean block = Boolean.valueOf(value.toString());
        
        if (block) {
            friendship = friendshipService.block(friendship.getUserId(), friendship.getFriendId());
        } else {
        	friendship = friendshipService.unblock(friendship.getUserId(), friendship.getFriendId());
        }
		
		return ok(friendshipModelService.toModel(friendship));
	}

	// ----------------------------------------------------------------------
	
	@RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
	public ResponseEntity<FriendshipModel> unfollow(HttpServletRequest req,
			@PathVariable String uid) {
		logApiRequest(req, "DELETE /friendships/" + uid);

		Friendship friendship = getFriendship(uid);
		
        KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
        
        if (status == KFriendshipStatus.FRIENDS) {
        	friendship = friendshipService.revokeFriendship(friendship.getUserId(), friendship.getFriendId());
        } else {
        	friendship = friendshipService.unfollow(friendship.getUserId(), friendship.getFriendId());
        }
        
		return ok(friendshipModelService.toModel(friendship));
	}

	// ----------------------------------------------------------------------
	
    private Friendship saveObject(Friendship friendship, FriendshipRequest model) {
    	logger.debug("mapToObject called for friendship: " + friendship);
        
    	Boolean requestFriendship = null;
        User friend = null;
        String mobileNumber = null;
        FriendshipCircle circle  = null;

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "type":
                    switch (model.getType()) {
                        case FOLLOW:
                            requestFriendship = false;
                            break;
                        case FRIEND:
                            requestFriendship = true;
                            break;
                        default:
                            throw new BadRequestException("Invalid friendship type: " + model.getType());
                    };
                    break;

                case "friend":
                    UserModel userModel = model.getFriend();
                    friend = userModelService.getUser(userModel);
                    break;

                case "mobileNumber":
                    mobileNumber = model.getMobileNumber();
                    break;

                case "circle":
                    FriendshipCircleModel circleModel = model.getCircle();
                    circle = friendshipModelService.getFriendshipCircle(circleModel);
                    break;

            }
        }


        if (friendship.getId() == null && requestFriendship == null) {
            throw new BadRequestException("type parameter is required");
        }
        
        if (friendship.getId() == null && friend == null && mobileNumber == null) {
            throw new BadRequestException("friend_uid or mobile_number parameter is required");
        }

        if (friend == null) {
            if (mobileNumber != null) {
                friend = userModelService.getUser(mobileNumber);
            }

            if (friend == null) {
                String firstName = null;

                boolean resend = false;

                Invitation invitation = invitationService.inviteByMobileNumber(
                        getUser().getId(), 
                        KInvitationType.FRIEND, 
                        mobileNumber, 
                        firstName, 
                        resend);

                logger.debug("FriendshipController: saveObject: invitation: " + invitation);

                return null;
            }
        }

        if (friendship.getId() == null) {
            Date now = new Date();
            friendship.setUserId(getUser().getId());
            friendship.setCreatedDate(now);
            
            Long circleId = null;

            if (circle != null) {
                circleId = circle.getId();
            }

            if (requestFriendship) {
            	friendship = friendshipService.requestFriendship(
            	        getUser().getId(), 
            	        friend.getId(), 
            	        circleId);
            } else {
            	friendship = friendshipService.follow(
            	        getUser().getId(), 
            	        friend.getId(), 
            	        circleId);
            }
        } 
        
        return friendship;
    }
    

    
    // ----------------------------------------------------------------------

    public Friendship getFriendship(String uid) {
        Friendship friendship = friendshipService.fetchByUid(uid);
        if (friendship == null) {
            throw new NotFoundException("Friendship not found for uid: " + uid);
        }
        return friendship;

    }
    
 

}
