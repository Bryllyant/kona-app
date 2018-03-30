package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.stripe.entity.KCard;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.stripe.entity.KCustomer;
import com.bryllyant.kona.stripe.entity.KStripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public abstract class KAbstractStripeService<
        USER extends KUser,
		PAYMENT_ACCOUNT extends KPaymentAccount>
        extends com.bryllyant.kona.stripe.service.KAbstractStripeService
        implements KStripeService {

	private static Logger logger = LoggerFactory.getLogger(KAbstractStripeService.class);

	protected abstract <S extends KUserService<USER>> S getUserService();

	protected abstract <S extends KPaymentAccountService<PAYMENT_ACCOUNT, USER>> S getPaymentAccountService();

	protected abstract void sendPrimaryCardUpdateEmail(USER user, KCard card);


    private String getStripeUid(USER user) {
        String stripeUid = null;

        PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(user.getAccountId());

        if (paymentAccount != null) {
            stripeUid = paymentAccount.getProviderCustomerId();
        }

        return stripeUid;
    }

	private String getUserDescription(USER user) {
		String s = "Username: " + user.getUsername();
		/*
        s += "\nFirst Name: " + user.getFirstName();
        s += "\nLast Name: " + user.getLastName();
        s += "\nCompany: " + user.getCompany();
        s += "\nEmail: " + user.getEmail();
		 */
		return s;
	}


    @Override
    public String addCustomer(Long userId) {
        USER user = getUserService().fetchById(userId);

        KCustomer customer = addCustomer(user.getEmail(), getUserDescription(user));

        return customer.getId();
    }
    
    @Override
    public void deleteCustomer(Long userId) {
        USER user = getUserService().fetchById(userId);
        deleteCustomer(getStripeUid(user));
    }
    


    @Override 
    public void updateCustomer(Long userId) {
        USER user = getUserService().fetchById(userId);
        
        String stripeUid = getStripeUid(user);
        
        if (stripeUid != null) {
            KCustomer customer = fetchCustomerById(stripeUid);
        
            customer.setEmail(user.getEmail());
        
            customer.setDescription(getUserDescription(user));
        
            updateCustomer(customer);
        }
    }
    

	
    @Override
	public String updateStripeUid(Long userId, String cardToken)  {
        USER user = getUserService().fetchById(userId);
        
        String email = user.getEmail();

        String description = user.getUsername();

        String stripeUid = getStripeUid(user);
        
        if (stripeUid == null) {
        	KCustomer customer = addCustomer(email, description, cardToken);

        	stripeUid = customer.getId();

        	KCard card = customer.getDefaultCard();

        	getPaymentAccountService().addStripeAccount(user.getAccountId(), stripeUid, card.getLast4(), true);

        } else {
        	addPrimaryCard(userId, cardToken);
        }
        
    	return stripeUid;
	}
    


    @Override 
    public KCharge chargeCustomer(Long userId, BigDecimal amount,
            String description, String receiptEmail, Map<String,Object> metadata,
            Map<String,Object> shipping) throws KStripeException {
        logger.debug("calling chargeCustomer");
        USER user = getUserService().fetchById(userId);
        
        String stripeUid = getStripeUid(user);
        
        if (stripeUid == null) {
            String s = "Stripe UID is null for user: " + user;
            throw new KStripeException(s);
        }

        return chargeCustomer(stripeUid, amount, description, receiptEmail, metadata, shipping);
    }
    
    

    
    @Override
    public KCard addPrimaryCard(Long userId, KCard card) {
        USER user = getUserService().fetchById(userId);

        String stripeUid = getStripeUid(user);

        return addCustomerCard(stripeUid, card);
    }
    


    /*
     * Passing source will create a new source object, make it the new customer default source, and delete 
     * the old customer default if one exists. If you want to add additional sources instead of replacing 
     * the existing default, use the card creation API. Whenever you attach a card to a customer, Stripe 
     * will automatically validate the card.
     * 
     * https://stripe.com/docs/api/java#update_customer
     */
    @Override
    public KCard addPrimaryCard(Long userId, String cardToken) {
        USER user = getUserService().fetchById(userId);

        String stripeUid = getStripeUid(user);

        return addCustomerCard(stripeUid, cardToken);
    }
    


    @Override 
    public KCard updatePrimaryCard(Long userId, KCard card) {
    	card = addPrimaryCard(userId, card);
        USER user = getUserService().fetchById(userId);
    	sendPrimaryCardUpdateEmail(user, card);
    	return card;
    }
    


    @Override
    public KCard updatePrimaryCard(Long userId, String cardToken) {
    	KCard card = addPrimaryCard(userId, cardToken);
        USER user = getUserService().fetchById(userId);
    	sendPrimaryCardUpdateEmail(user, card);
    	return card;
    }
    

    
    @Override
    public KCard getPrimaryCard(Long userId) {
        USER user = getUserService().fetchById(userId);

        String stripeUid = getStripeUid(user);

        if (stripeUid == null) {
        	logger.warn("getPrimaryCard: stripeUid is null");
        	return null;
        }
        
    	return fetchCustomerActiveCard(stripeUid);
    }
    

    
    @Override 
    public String getPrimaryCardLast4(Long userId) {
        
        if (userId == null) {
            logger.warn("getPrimaryCardLast4ByUserId: userId is null");
        	return null;
        }
        
    	KCard card = getPrimaryCard(userId);
        
        if (card == null) {
            logger.debug("getPrimaryCardLast4ByUserId: card is null");
        	return null;
        }
        
        return card.getLast4();
        
    }
    



}
