package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.media.FileModel;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.model.message.EmailTemplateModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailContentModelService extends BaseEntityModelService<EmailContentModel,EmailContent> {
    private static final Logger logger = LoggerFactory.getLogger(EmailContentModelService.class);

    @Autowired
    private EmailContentService entityService;

    @Autowired
    private EmailAttachmentService emailAttachmentService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private EmailTemplateModelService emailTemplateModelService;

    @Autowired
    private FileModelService fileModelService;

    protected EmailContentService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailContentModel model, EmailContent entity) {
        if (entity.getOwnerId() != null) {
            User owner = userModelService.getUser(entity.getOwnerId());
            model.setOwner(UserModel.from(owner));
        }

        if (entity.getTemplateId() != null) {
            EmailTemplate template = emailTemplateModelService.getEntity(entity.getTemplateId());
            model.setTemplate(EmailTemplateModel.from(template));
        }

        List<EmailAttachment> attachmentList = emailAttachmentService.fetchByContentId(entity.getId());

        List<FileModel> files = new ArrayList<>();

        for (EmailAttachment attachment : attachmentList) {
            File file = fileService.fetchById(attachment.getFileId());

            if (file != null) {
                files.add(fileModelService.toModel(file));
            }
        }

        model.setAttachments(files);
    }

    protected void setEntityProperty(String key, EmailContentModel model, EmailContent entity) {
        switch (key) {

            case "owner":
                User owner = userModelService.getUser(model.getOwner());
                entity.setOwnerId(owner == null ? null : owner.getId());
                break;

            case "template":
                EmailTemplate template = emailTemplateModelService.getEntity(model.getTemplate());
                entity.setTemplateId(template == null ? null : template.getId());
                break;
        }

    }
}
