package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.social.invitation.InvitationModel;
import com.bryllyant.kona.api.model.social.invitation.InvitationModel;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(InvitationModelService.class);



    @Autowired
    private InvitationService invitationService;
    
    @Autowired
    private AppUtil util;
    


    public Invitation getInvitation(String invitationCode) {
        Invitation invitation = invitationService.fetchByInvitationCode(invitationCode);

        if (invitation == null) {
            throw new NotFoundException("Invitation not found for code: " + invitationCode);
        }

        return invitation;
    }



    public Invitation getInvitation(Long invitationId) {
        Invitation invitation = invitationService.fetchById(invitationId);

        if (invitation == null) {
            throw new NotFoundException("Invitation not found for id: " + invitationId);
        }

        return invitation;
    }

    


    public Invitation getInvitation(InvitationModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Invitation not found for model: " + model);
        }

        return getInvitation(uid);
    }


    
    public final InvitationModel toModel(Invitation invitation, String... includeKeys) {
        if (invitation == null) return null;

        InvitationModel model = new InvitationModel();
        
        model.fromBean(invitation);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }
    


    public final List<InvitationModel> toModelList(List<Invitation> invitations, String... includeKeys) {
        List<InvitationModel> modelList = new ArrayList<>();

        for (Invitation invitation : invitations) {
            modelList.add(toModel(invitation, includeKeys));
        }

        return modelList;
    }



    public Invitation toEntity(InvitationModel model, boolean disableValidation) {
        Invitation invitation = new Invitation();

        return mergeEntity(invitation, model, disableValidation);
    }




    public Invitation mergeEntity(Invitation invitation, InvitationModel model, boolean disableValidation) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, invitation);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                case "type":
                    break;
                    
                case "channel":
                    break;

                case "status":
                    break;
            }

        }

        return invitation;
    }
    

    
}
