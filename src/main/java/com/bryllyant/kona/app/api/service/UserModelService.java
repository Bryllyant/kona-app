package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ConflictException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(UserModelService.class);
    
    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private AccountModelService accountModelService;

    @Autowired
    private PositionModelService positionModelService;
    
    @Autowired
    private SystemService system;

    @Autowired
    private AppUtil util;


    public User getUser(String username) {
        User user = null;

        if (username == null) {
            throw new BadRequestException("User identifier is required.");
        }

        if (KValidator.isEmail(username)) {
            user = userService.fetchByEmail(username);
        } else if (KValidator.isE164PhoneNumber(username)) {
            user = userService.fetchByMobileNumber(username);
        } else {
            user = userService.fetchByUid(username);
        }

        if (user == null) {
            user = userService.fetchByUsername(username);
        }

        if (user == null) {
            throw new NotFoundException("Invalid uid|username|email|mobile number: " + username);
        }

        return user;
    }
    

    public User getUser(UserModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            uid = model.getUsername();
        }

        if (uid == null) {
            uid = model.getEmail();
        }

        if (uid == null) {
            uid = model.getMobileNumber();
        }

        if (uid == null) {
            throw new NotFoundException("User not found for model: " + model);
        }

        return getUser(uid);
    }
    
    
    public User getUser(Long userId) {
        User user = userService.fetchById(userId);

        if (user == null) {
            throw new NotFoundException("User not found for id: " + userId);
        }

        return user;
    }
    

    public List<UserModel> toModelList(List<User> users, String... includeKeys) {
        List<UserModel> modelList = new ArrayList<>();

        for (User user : users) {
            modelList.add(toModel(user, includeKeys));
        }

        return modelList;
    }

//    public List<MeModel> toMeModelList(List<User> users, String... includeKeys) {
//        List<MeModel> modelList = new ArrayList<>();
//
//        for (User user : users) {
//            modelList.add(toMeModel(user, includeKeys));
//        }
//
//        return modelList;
//    }

    /*
    public User mergeEntity(User user, UserModel userModel) {

        for (String key : userModel.keySet()) {
            logger.debug("toUser: setting key: " + key);

            Object value = userModel.get(key);

            switch (key) {
                case "first_name":
                    user.setFirstName(util.getStringValue(value));
                    break;

                case "last_name":
                    user.setLastName(util.getStringValue(value));
                    break;

                case "display_name":
                    user.setDisplayName(util.getStringValue(value));
                    break;

                case "username":
                    user.setUsername(util.getStringValue(value));
                    break;

                case "gender":
                    user.setGender(util.getStringValue(value));
                    break;

                case "birth_date":
                    user.setBirthDate(util.getDateValue(value));
                    break;

                case "locale":
                    user.setLocale(util.getStringValue(value));
                    break;

                case "time_zone":
                    user.setTimeZone(util.getStringValue(value));
                    break;

                case "email":
                    user.setEmail(util.getStringValue(value));
                    break;

                case "mobile_number":
                    user.setMobileNumber(util.getStringValue(value));
                    break;
            }
        }

        return user;
    }
    */
    

//    public MeModel toMeModel(User user, String... includeKeys) {
//        if (user == null) return null;
//
//        MeModel model = new MeModel();
//
//        // create base UserDeviceModel from DeviceModel
//        UserModel userModel = toModel(user);
//
//        util.copyBean(userModel, model, true);
//
//        Registration registration = registrationService.fetchByUserId(user.getId());
//
//        List<String> roles = userService.getRoles(user);
//
//        model.setEmailVerified(registration.isEmailVerified());
//        model.setMobileVerified(registration.isMobileVerified());
//        model.setRoles(new HashSet<>(roles));
//
//        if (includeKeys != null && includeKeys.length > 0) {
//            logger.debug("toMeModel: includeKeys: " + KJsonUtil.toJson(includeKeys));
//
//            model.includeKeys(includeKeys);
//        }
//
//        return model;
//    }

    public UserModel toModel(User user, String... includeKeys) {
        return toModel(user, true, true, includeKeys);
    }

    public UserModel toModel(User user, boolean includeAccount, boolean includePosition, String... includeKeys) {
        if (user == null) return null;

        UserModel model = new UserModel();
        
        model.fromBean(user);
        
        String photoUrl = util.toAbsoluteUrl(user.getPhotoUrl());
        model.setPhotoUrl(photoUrl);

        String thumbnailUrl = util.toAbsoluteUrl(user.getThumbnailUrl());
        model.setThumbnailUrl(thumbnailUrl);
        
        if (user.getParentId() != null) {
            User parent = getUser(user.getParentId()); 
            model.setParent(UserModel.create(parent.getUid()));
        }

        if (includePosition) {

            if (user.getPositionId() != null) {
                Position position = positionModelService.getPosition(user.getPositionId());
                PositionModel positionModel = PositionModel.from(position);
                model.setPosition(positionModel);
            }
        }

        if (includeAccount) {
            Account account = accountService.fetchById(user.getAccountId());
            model.setAccount(AccountModel.create(account.getUid()));

            Registration registration = registrationService.fetchByUserId(user.getId());

            List<String> roles = userService.getRoles(user);

            model.setEmailVerified(registration.isEmailVerified());
            model.setMobileVerified(registration.isMobileVerified());
            model.setRoles(new HashSet<>(roles));
        }
        
        if (includeKeys != null && includeKeys.length > 0) {
            logger.debug("toModel: includeKeys: " + KJsonUtil.toJson(includeKeys));

            model.includeKeys(includeKeys);
        }
        
        return model;
    }
    
    

    public void sendEmailVerification(Long userId) {
        authCodeService.requestAuthCode(AuthCode.Type.EMAIL_CONFIRMATION, userId, true);
    }
    

    public void sendMobileVerification(Long userId) {
        authCodeService.requestAuthCode(AuthCode.Type.MOBILE_CONFIRMATION, userId, true);
    }
    

    // NOTE: mobileNumber expected to be in E.164 format
    public boolean setMobileNumber(User user, String mobileNumber, boolean disableValidation, boolean updateUser) {
        if (mobileNumber == null) return false;
        
        // Remove whitespace from the number
        mobileNumber = mobileNumber.replaceAll("\\s+","");

        if (!KValidator.isE164PhoneNumber(mobileNumber)) {
            throw new BadRequestException("Invalid mobile number [" + mobileNumber + "]. Mobile numbers must be in E.164 format.");
        }
        
        if (!disableValidation) {
            // passed basic validation. now make sure this user isn't already in the system
            User u = userService.fetchByMobileNumber(mobileNumber);

            if (!(u == null || (user.getId() != null && u.getId().equals(user.getId())))) {
                logger.error("User already exists with mobile number:\n--- user ---\n"
                        + KClassUtil.toString(u) + "\n--- user ---\n" + KClassUtil.toString(user));

                throw new ConflictException("User already exists with mobile number: " + mobileNumber);
            }
        }
        
        if (user.getMobileNumber() == null || !user.getMobileNumber().equals(mobileNumber)) {
            user.setMobileNumber(mobileNumber);
            
            if (updateUser && user.getId() != null) {
                Registration registration = registrationService.fetchByUserId(user.getId());
                registration.setMobileVerified(false);
                registrationService.update(registration);
            }
            
            return true;
        } 
        
        return false;
    }
    

    public boolean setEmail(User user, String email, boolean disableValidation, boolean updateUser) {
        if (email == null) return false;

        if (!KValidator.isEmail(email)) {
            throw new BadRequestException("Invalid email address: " + email);
        }

        if (!disableValidation) {
            // passed basic validation. now make sure this user isn't already in the system
            User u = userService.fetchByEmail(email);

            if (!(u == null || (user.getId() != null && u.getId().equals(user.getId())))) {
                throw new ConflictException("User already exists with email: " + email);
            }
        }
        
        if (user.getEmail() == null || !user.getEmail().equals(email)) {
            user.setEmail(email);
            
            if (updateUser && user.getId() != null) {
                Registration registration = registrationService.fetchByUserId(user.getId());
                registration.setEmailVerified(false);
                registrationService.update(registration);
            }
            
            return true;
        } 
        
        return false;
    }
    

    public boolean setUsername(User user, String username, boolean disableValidation) {
        if (username == null) return false;
        
        if (!disableValidation && !userService.isUsernameAvailable(username)) {
            User u = userService.fetchByUsername(username);
            
            if (u == null) {
                throw new BadRequestException("Invalid username: " + username);
            }
            
            if (user.getId() == null || !u.getId().equals(user.getId())) {
                throw new ConflictException("User already exists with username: " + username);
            } 
        }
        
        
        if (user.getUsername() == null || !user.getUsername().equals(username)) {
            user.setUsername(username);
            return true;
        } 
        
        return false;
    }
    
   

    public User toEntity(UserModel model, boolean disableValidation, boolean updateUser) {
        User user = new User();
        return mergeEntity(user, model, disableValidation, updateUser);
    }


    public User mergeEntity(User user, UserModel model, boolean disableValidation, boolean updateUser) {
        logger.debug("toEntity called for model: " + model);
        
        boolean verifyMobile = false;
        boolean verifyEmail = false;
        
        // save originals since these to be handled differently
        String _email = user.getEmail();
        String _username = user.getUsername();;
        String _mobileNumber = user.getMobileNumber();;
        String _photoUrl = user.getPhotoUrl();;
        String _thumbnailUrl = user.getThumbnailUrl();;

        util.copyModelToObject(model, user);
        
        // Restore original values so they can be updated manually
        user.setEmail(_email);
        user.setUsername(_username);
        user.setMobileNumber(_mobileNumber);
        user.setPhotoUrl(_photoUrl);
        user.setThumbnailUrl(_thumbnailUrl);

        
        for (String key : model.initializedKeys()) {

            switch (key) {
                case "photoUrl":
                    if (user.getPhotoId() == null) {
                        user.setPhotoUrl(model.getPhotoUrl());
                    }
                    break;

                case "thumbnailUrl":
                    if (user.getPhotoId() == null) {
                        user.setThumbnailUrl(model.getThumbnailUrl());
                    }
                    break;

                case "account":
                    AccountModel accountModel = model.getAccount();
                    Account account = accountModelService.getAccount(accountModel);
                    user.setAccountId(account.getId());
                    break; 

                case "parent":
                    UserModel userModel = model.getParent();
                    User parent = getUser(userModel);
                    user.setParentId(parent.getId());
                    break;

                    /*
                case "position":
                    PositionModel positionModel = model.getPosition();
                    Position position = positionModelService.getUser(positionModel);
                    user.setPositionId(position.getId());
                    break;
                    */

//                case "presence":
//                    try {
//                        KUserPresence presence = model.getPresence();
//                        user.setPresenceId(presence.getId());
//                    } catch (Exception e) {
//                        throw new BadRequestException("presence value not valid: " + model.getPresence());
//                    }
//
//                    break;

                case "username":
                    setUsername(user, model.getUsername(), disableValidation);
                    break;

                case "email":
                    String email = model.getEmail();

                    if (updateUser && user.getId() != null) {
                        if (setEmail(user, email, disableValidation, updateUser)) {
                            verifyEmail = true;
                        } 

                    } else {
                        user.setEmail(email);
                    }

                    break;

                case "mobileNumber":
                    String mobileNumber = model.getMobileNumber();

                    if (updateUser && user.getId() != null) {
                        if (setMobileNumber(user, mobileNumber, disableValidation, updateUser)) {
                            verifyMobile = true;
                        } 

                    } else {
                        user.setMobileNumber(mobileNumber);
                    }

                    break;            
            } 
        }
        
        if (updateUser && user.getId() != null) {
            if (verifyEmail || verifyMobile) {
                user = userService.update(user);
            }
            
            if (verifyMobile) {
                sendMobileVerification(user.getId());
            }
            
            if (verifyEmail) {
                sendEmailVerification(user.getId());
            }
        } 

        return user;
    }
    
}
