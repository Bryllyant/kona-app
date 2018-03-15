package com.bryllyant.kona.app.api.controller.admin;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.service.AccountModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KResultList;
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
import java.util.HashMap;
import java.util.Map;


/**
 * Account Controller.
 */
@RestController
@RequestMapping("/api/admin/accounts")
public class AccountController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);



    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AccountModelService accountModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private ApiUtil util; 



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<AccountModel>> search(HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/accounts");

        logger.debug("AccountController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        // exclude system account
        Account systemAccount = system.getSystemAccount();
        filter.put("!uid", systemAccount.getUid());

        if (sortOrder == null) {
            sortOrder = new String[]{
                "name"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("AccountController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = accountService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, accountModelService.toModelList(result));

        return okList(resultSet);
    }





    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<AccountModel> get(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "GET /admin/accounts/" + uid);

        Account account = accountModelService.getAccount(uid);

        return ok(accountModelService.toModel(account));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountModel> create(HttpServletRequest req,
            @RequestBody AccountModel model) {
        logApiRequest(req, "POST /admin/accounts");

        Account account = new Account();

        account = saveObject(req, account, model);

        return created(accountModelService.toModel(account));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<AccountModel> update(HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody AccountModel model) {
        logApiRequest(req, "PUT /admin/accounts/" + uid);

        Account account = accountModelService.getAccount(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }
        
        account = saveObject(req, account, model);

        return ok(accountModelService.toModel(account));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<AccountModel> remove(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "DELETE /admin/accounts/" + uid);

        Account account = accountModelService.getAccount(uid);

        accountService.remove(account);

        //return ok(accountModelService.toModel(account));
        return ok(AccountModel.create(account.getUid()));
    }


    public Account saveObject(HttpServletRequest req, Account account, AccountModel model) {
        logger.debug("mapToObject called for account: " + account);
        
        account = accountModelService.mergeEntity(account, model);

        if (account.getId() == null && model.getEnabled() == null) {
            account.setEnabled(true);
        }

        return accountService.save(account);
    }



    @Override
    public Map<String,Object> toFilterCriteria(String json) {
        Map<String,Object> filter = super.toFilterCriteria(json);

        if (filter == null) {
            return new HashMap<>();
        }

        Map<String,Object> result = new HashMap<>();

        for (String key : filter.keySet()) {
            Object value = filter.get(key);

            String[] parts = util.splitKey(key);
            String prefix = parts[0];
            key = parts[1];

            switch (key) {
//                case "fts":
//                    String fts = util.getStringValue(value);
//                    result.put(prefix + key, fts);
//                    break;

                case "ownerUid":
                    Long id = -1L;
                    User owner = userModelService.getUser(util.getStringValue(value));
                    if (owner != null) id = owner.getId();
                    result.put(prefix + "ownerId", id);
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    } 

}

