package com.bryllyant.kona.api.controller.system;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.SupportMessageService;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Support Controller.
 */
@RestController
@RequestMapping("/api/system/support")
public class SupportController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SupportController.class);
    


    @Autowired
    private SupportMessageService supportMessageService;

    @Autowired
    private UserModelService userModelService;



    @RequestMapping(value = "/messages", method=RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<Map<String,Object>>  message(HttpServletRequest req,
            @RequestBody Map<String,Object> map) {
        logApiRequest(req, "POST /system/support/messages");

        String message = (String) map.get("message");
        String email = (String) map.get("email");
        String mobileNumber = (String) map.get("mobile_number");
        String name = (String) map.get("name");
        String userUid = (String) map.get("user");
        Long userId = null;

        if (userUid != null) {
            User user = userModelService.getUser(userUid);
            userId = user.getId();
        }

        if (email == null && mobileNumber == null) {
            throw new BadRequestException("email or mobile_number is required");
        }

        if (message == null) {
            throw new BadRequestException("message is required");
        }


        KServiceClient client = getServiceClient(req);
        // explicitly set userId to the value found here
        client.setUserId(userId);

        SupportMessage supportMessage = supportMessageService.send(client, name, email, mobileNumber, message);

        return created(toMap(supportMessage));
    }


    protected Map<String,Object> toMap(SupportMessage supportMessage) {
        if (supportMessage == null) return null;


        Map<String,Object> result = new HashMap<String,Object>();
        result.put("uid", supportMessage.getUid());
        result.put("created_date", supportMessage.getCreatedDate());
        return result;
    }


    /*
	private void message(HttpServletRequest req, String subject, String body) {

		String from = config.getString("system.mail.from");
		String to = config.getString("system.mail.alertTo");
		String replyTo = null;
		String cc = null;
		String bcc = null;
		boolean html = false;

		logger.debug("Support Email:\nfrom: " + from + "\nsubject: " + subject + "\nbody:\n" + body);

		system.sendEmail(body, subject, from, replyTo, to, cc, bcc, html, null);
	}
     */
}
