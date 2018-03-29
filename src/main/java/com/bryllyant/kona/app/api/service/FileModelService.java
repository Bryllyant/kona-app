package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.auth.TokenModel;
import com.bryllyant.kona.app.api.model.media.FileModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(FileModelService.class);
    
    @Autowired
    private FileService fileService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AuthModelService authModelService;

    @Autowired
    private AccountModelService accountModelService;

    @Autowired
    private AppUtil util;

   


    public File getFile(String uid) {
        File file = null;

        try {
            file = fileService.fetchByUid(uid, false);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        if (file == null) {
            throw new NotFoundException("User file not found for uid: " + uid);
        }

        return file;
    }


    public File getFile(Long fileId) {
        File file = fileService.fetchById(fileId);

        if (file == null) {
            throw new NotFoundException("User file not found for id: " + fileId);
        }

        return file;
    }
    

    public File getFile(FileModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("File not found for model: " + model);
        }

        return getFile(uid);
    }



    public final FileModel toModel(File file, String... includeKeys) {
        
        FileModel model = new FileModel();

        model.fromBean(file);

        String url = util.toAbsoluteUrl(file.getUrlPath());

        model.setUrl(url);

        if (file.getUserId() != null) {
            User user = userModelService.getUser(file.getUserId());
            model.setUser(UserModel.from(user));
        }

        if (file.getAccountId() != null) {
            Account account = accountModelService.getAccount(file.getAccountId());
            model.setAccount(AccountModel.from(account));
        }

        if (file.getParentId() != null) {
            File parent = getFile(file.getParentId());
            String parentUrl = util.toAbsoluteUrl(parent.getUrlPath());
            model.setParent(FileModel.from(parent, parentUrl));
        }

        if (file.getTokenId() != null) {
            Token token = authModelService.getToken(file.getTokenId());
            model.setToken(TokenModel.from(token));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public final List<FileModel> toModelList(List<File> fileList, String... includeKeys) {
        List<FileModel> modelList = new ArrayList<FileModel>();

        for (File item : fileList) {
            modelList.add(toModel(item, includeKeys));
        }

        return modelList;
    }
    
    


    public File toEntity(FileModel model) {
        File file = new File();

        return mergeEntity(file, model);
    }



    public File mergeEntity(File file, FileModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, file);
        
        for (String key : model.initializedKeys()) {

            switch (key) {

                case "user":
                    User user = userModelService.getUser(model.getUser());
                    file.setUserId(user == null ? null : user.getId());
                    file.setAccountId(user == null ? null : user.getAccountId());
                    break;

                case "account":
                    Account account = accountModelService.getAccount(model.getAccount());
                    file.setAccountId(account == null ? null : account.getId());
                    break;

                case "parent":
                    File parent = getFile(model.getParent());
                    file.setParentId(parent == null ? null : parent.getId());
                    break;

                case "token":
                    Token token = authModelService.getToken(model.getToken());
                    file.setTokenId(token == null ? null : token.getId());
                    break;
            }

        }

        return file;
    }
    

   
    
}
