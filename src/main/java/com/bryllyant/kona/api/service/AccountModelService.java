package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.account.AccountModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(AccountModelService.class);
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserModelService userModelService;
    
    @Autowired
    private AppUtil util;



    public Account getAccount(String uid) {
        Account account = accountService.fetchByUid(uid);

        if (account == null) {
            throw new NotFoundException("Account not found for uid: " + uid);
        }

        return account;
    }



    public Account getAccount(Long accountId) {
        Account account = accountService.fetchById(accountId);

        if (account == null) {
            throw new NotFoundException("Account not found for id: " + accountId);
        }

        return account;
    }
    
    


    public Account getAccount(AccountModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Account not found for model: " + model);
        }

        return getAccount(uid);
    }
   


    public AccountModel toModel(Account account, String... includeKeys) {
        if (account == null) return null;

        AccountModel model = new AccountModel();
        
        model.fromBean(account);
        
        if (account.getOwnerId() != null) {
            User owner = userModelService.getUser(account.getOwnerId());
            model.setOwner(UserModel.create(owner.getUid()));
        }


        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<AccountModel> toModelList(List<Account> accounts, String... includeKeys) {
        List<AccountModel> modelList = new ArrayList<>();

        for (Account account : accounts) {
            modelList.add(toModel(account, includeKeys));
        }

        return modelList;
    }



    public Account toEntity(AccountModel model) {
        Account account = new Account();

        return mergeEntity(account, model);
    }

    


    public Account mergeEntity(Account account, AccountModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, account);

        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "owner":
                    User owner = userModelService.getUser(model.getOwner());
                    account.setOwnerId(owner == null ? null : owner.getId());
                    break;
            }
        }

        return account;
    }
    

   
}
