/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractAccountService<ACCOUNT extends KAccount, ACCOUNT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<ACCOUNT, ACCOUNT_EXAMPLE>,USER extends KUser>
		extends KAbstractService<ACCOUNT,ACCOUNT_EXAMPLE,MAPPER>
		implements KAccountService<ACCOUNT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAccountService.class);



	protected abstract ACCOUNT getNewObject();

	protected abstract <S extends KUserService<USER>> S getUserService();
	

	
	protected String generateUid() {
		return KUtil.uuid();
	}



	@Override 
	public void validate(ACCOUNT account) {
		if (account.getCreatedDate() == null) {
			account.setCreatedDate(new Date());
		}

		if (account.getUid() == null) {
			account.setUid(generateUid());
		}

		if (account.getName() == null) {
			account.setName(account.getUid());
		}
		
		account.setUpdatedDate(new Date());

		String slug = KInflector.getInstance().slug(account.getName());
		account.setSlug(slug);
	}



	@Override @Transactional
	public ACCOUNT createAccount(String name) {

		String uid = generateUid();

		if (name == null) {
			name = uid;
		}

		// clean up displayName as much as possible before
		name = name.trim().replaceAll("\\p{Punct}", "");

		if (!isAccountNameAvailable(name)) {
			throw new IllegalArgumentException("Account name already exists: " + name);
		}

		String slug = KInflector.getInstance().slug(name);

		ACCOUNT account = getNewObject();
		account.setUid(uid);
		account.setOwnerId(null);
		account.setName(name);
		account.setSlug(slug);
		account.setEnabled(true);
		account.setVerified(false);
		account.setCreatedDate(new Date());

		/* -- STRIPE --
        String email = uid; 
        // If Stripe is enabled
        if (email != null && !account.getName().equalsIgnoreCase("guest")) {
            KCustomer customer = stripeService.addCustomer(email,
                    "Name: " + account.getDisplayName());
            String stripeUid = customer.getId();
            account.setStripeUid(stripeUid);
            account.setPaymentTypeId(PaymentType.CARD.getId());
        }
		 */

		return add(account);
	}



	@Override
	public ACCOUNT fetchBySlug(String slug) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override
	public ACCOUNT fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override
	public ACCOUNT fetchByOwnerId(Long ownerId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}




	@Override @Transactional
	public ACCOUNT retire(ACCOUNT account) {
	      // fetch fresh object
        account = fetchById(account.getId());

        // return account if already retired
        if (account == null || account.getDeletedDate() != null) {
            return account;
        }

        // first check that all users associated with this account are also retired
        List<USER> userList = getUserService().fetchByAccountId(account.getId());
        boolean haveActiveUser = false;
        for (USER user : userList) {
            if (user.getDeletedDate() != null) {
                haveActiveUser = true;
            }
        }

        if (haveActiveUser) {
            throw new IllegalStateException("Cannot retire account with active (non-retired) users: accountId: " + account.getId());
        }



        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        account.setSlug(prefix + account.getSlug());;
        account.setEnabled(false);
        account.setDeletedDate(new Date());
        account = update(account);

        return account;
	}
}
