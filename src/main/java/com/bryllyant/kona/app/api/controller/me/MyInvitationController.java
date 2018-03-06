package com.bryllyant.kona.app.api.controller.me;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.social.invitation.InvitationModel;
import com.bryllyant.kona.app.api.service.InvitationModelService;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.KInvitationStatus;
import com.bryllyant.kona.app.entity.KInvitationType;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.NotFoundException;
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
import java.util.List;
import java.util.Map;

/**
 * Invitation Controller.
 */
@RestController
@RequestMapping("/api/me/invitations")
public class MyInvitationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MyInvitationController.class);


	
    @Autowired
    private InvitationService invitationService;

    @Autowired
    private SystemService system;

    @Autowired
    private InvitationModelService invitationModelService;

	


	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<InvitationModel>> search(HttpServletRequest req,
			@RequestParam(value="pending", required=false) Boolean pending) {
		logApiRequest(req, "GET /me/invitations");
		
		KInvitationStatus status = null;
		if (pending != null && pending == true) {
			status = KInvitationStatus.PENDING;
		}
		
		List<Invitation> invitations = 
					invitationService
					.fetchByOwnerId(getUser().getId(), status, null, null);
		
		return okList(invitationModelService.toModelList(invitations));
	}
	


	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public ResponseEntity<InvitationModel> getByCode(HttpServletRequest req,
			@PathVariable String invitationCode) {
		logApiRequest(req, "GET /me/invitations/" + invitationCode);

		return ok(invitationModelService.toModel(getInvitation(invitationCode)));
	}
	


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<InvitationModel> create(HttpServletRequest req,
			@RequestBody Map<String,Object> map) {
		logApiRequest(req, "POST /me/invitations");

		Invitation invitation = new Invitation();

		invitation = saveObject(req, invitation, map);

		return created(invitationModelService.toModel(invitation));
	}

	

	
    private Invitation saveObject(HttpServletRequest req, Invitation invitation, Map<String,Object> map) {
    	logger.debug("mapToObject called for invitation: " + invitation);
        
    	Boolean resend = false;
    	KInvitationType type = null;
    	String email = null;
    	String mobileNumber = null;
    	String firstName = null;
    	
        if (map != null) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value == null) {
                    logger.warn("Null value for key: " + key);
                    continue;
                }

                switch (key) {
                case "type":
                	try {
                		type = KInvitationType.getInstance(value.toString());
            		} catch (Exception e) {
            			throw new BadRequestException("Invalid type: " + type);
            		}
            		break;
            		

                case "first_name":
                	firstName = value.toString();
                    break;
                    
                case "email":
                	email = value.toString();
                	if (!KValidator.isEmail(email)) {
                		throw new BadRequestException("Invalid email address: " + email);
                	}
                    break;
                    
                case "mobile_number":
                	mobileNumber = value.toString();
                	if (!system.isTestPhoneNumber(mobileNumber) && !KValidator.isE164PhoneNumber(mobileNumber)) {
                        throw new BadRequestException("Invalid mobile number [" + mobileNumber + "]. Mobile numbers must be in E.164 format.");
                    }
                    break;
                    
                default:
                    throw new BadRequestException("Invalid property: " + key);
                }
            }
        }
        
        if (email == null && mobileNumber == null) {
        	throw new BadRequestException("Email or mobileNumber must be defined");
        }
        
        if (email != null && mobileNumber != null) {
        	throw new BadRequestException("Only email or mobileNumber must be defined, not both");
        }
      
        if (email != null) {
            invitation = invitationService
            		.inviteByEmail(getUser().getId(), type, email, firstName, resend);
        } else {
            invitation = invitationService
            		.inviteByMobileNumber(getUser().getId(), type, mobileNumber, firstName, resend);
        }
        
        return invitation;
    }
    


    protected Invitation getInvitation(String invitationCode) {
        Invitation invitation = invitationService.fetchByInvitationCode(invitationCode);
        
        if (invitation == null) {
            throw new NotFoundException("Invitation not found for code: " + invitationCode);
        }
        
        return invitation;

    }
    


 

}
