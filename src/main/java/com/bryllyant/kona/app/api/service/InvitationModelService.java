package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.social.invitation.InvitationModel;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.KInvitationChannel;
import com.bryllyant.kona.app.entity.KInvitationStatus;
import com.bryllyant.kona.app.entity.KInvitationType;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.rest.exception.BadRequestException;
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
    private ApiUtil util;
    


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

        KInvitationType type = KInvitationType.getInstance(invitation.getTypeId());
        KInvitationChannel channel = KInvitationChannel.getInstance(invitation.getChannelId());
        KInvitationStatus status = KInvitationStatus.getInstance(invitation.getStatusId());

        InvitationModel model = new InvitationModel();
        
        model.fromBean(invitation);

        model.setType(type);
        model.setChannel(channel);
        model.setStatus(status);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }
    


    public final List<InvitationModel> toInvitationModelList(List<Invitation> invitations, String... includeKeys) {
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
                    KInvitationType type = model.getType();

                    if (type == null) {
                        throw new BadRequestException("Invalid type: " + type);
                    }

                    invitation.setTypeId(type.getId());
                    break;
                    
                case "channel":
                    KInvitationChannel channel = model.getChannel();

                    if (channel == null) {
                        throw new BadRequestException("Invalid channel: " + channel);
                    }

                    invitation.setChannelId(channel.getId());
                    break;

                case "status":
                    KInvitationStatus status = model.getStatus();

                    if (status == null) {
                        throw new BadRequestException("Invalid status: " + status);
                    }

                    invitation.setStatusId(status.getId());
                    break;
            }

        }

        return invitation;
    }
    

    
}
