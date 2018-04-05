package com.bryllyant.kona.api.controller;

import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.service.AuthException;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.ApiLogService;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.SettingService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.FileUtil;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KModel;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.media.model.KImage;
import com.bryllyant.kona.media.util.KImageUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.ApiException;
import com.bryllyant.kona.rest.exception.AuthenticationException;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ForbiddenException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KJsonUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    @Autowired
    private KConfig config;

    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private AppCredsService appCredsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SystemService system;

    @Autowired
    private SettingService settingService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiLogService apiLogService;

    @Autowired
    private AppUtil util;

    @Autowired
    private FileUtil fileUtil;
    


    @Autowired
    private UserModelService userModelService;



    protected User getUser() {
        User user = apiAuthService.getUser();

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }
    


    protected Long getAppId(HttpServletRequest req) {
        String clientId = apiAuthService.getClientId(req);

        AppCreds creds = appCredsService.fetchByClientId(clientId);

        return creds.getAppId();
    }

    protected boolean haveRole(String role) {
        return hasRole(getUser(), role);
    }


    protected boolean hasRole(User user, String role) {
        return userService.hasRole(user, role);
    }


    protected Integer getImageMinWidth() {
        return config.getInteger("image.minWidth", 100);
    }


    protected Integer getImageMinHeight() {
        return config.getInteger("image.minHeight", 100);
    }


    protected Map<String,Object> getResultObject() {
        return getResultObject(null, null);
    }


    protected Map<String,Object> getResultObject(String key, Object value) {
        Map<String,Object> map = new HashMap<String,Object>();

        if (key != null && value != null) {
            map.put(key, value);
        }

        return map;
    }


  



    protected ResponseEntity<Map<String,Object>> created(Map<String,Object> map) {
        return created(map, true);
    }



    protected ResponseEntity<Map<String,Object>> created(Map<String,Object> map, boolean setLocation) {
        if (setLocation) {
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setLocation(
                    ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{uid}")
                    .buildAndExpand(map.get("uid")).toUri());

            return new ResponseEntity<Map<String,Object>>(map, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
        }
    }



    protected <T extends KModel> ResponseEntity<T> created(T model) {
        return created(model, null);
    }


    protected <T extends KModel> ResponseEntity<T> created(T model, String redirectLocationPath) {
        if (redirectLocationPath != null) {
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setLocation(
                    ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .replacePath("{redirectLocationPath}")
                    .buildAndExpand(redirectLocationPath).toUri());

            return new ResponseEntity<T>(model, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<T>(model, HttpStatus.CREATED);
        }    
    } 



    protected <T extends KModel & KEntityModel> ResponseEntity<T> created(T model, boolean setLocation) {
        if (setLocation && model.getUid() != null) {
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setLocation(
                    ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{uid}")
                    .buildAndExpand(model.getUid()).toUri());

            return new ResponseEntity<T>(model, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<T>(model, HttpStatus.CREATED);
        }
    }



    protected ResponseEntity<Map<String,Object>> loggedIn(Map<String,Object> map) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/me")
                .buildAndExpand().toUri());

        return new ResponseEntity<Map<String,Object>>(map, httpHeaders, HttpStatus.CREATED);
    }



    protected <T extends KModel> ResponseEntity<T> loggedIn(T model) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/me")
                .buildAndExpand().toUri());

        return new ResponseEntity<T>(model, httpHeaders, HttpStatus.CREATED);
    }
    


    protected ResponseEntity<Map<String,Object>> ok(Map<String,Object> map, HttpHeaders httpHeaders) {
        return new ResponseEntity<Map<String,Object>>(map, httpHeaders, HttpStatus.OK);
    }



    protected <T extends KModel> ResponseEntity<T> ok(T model, HttpHeaders httpHeaders) {
        return new ResponseEntity<T>(model, httpHeaders, HttpStatus.OK);
    }



    protected ResponseEntity<Map<String,Object>> ok(Map<String,Object> map) {
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }



    protected <T extends KModel> ResponseEntity<T> ok(T model) {
        return new ResponseEntity<T>(model, HttpStatus.OK);
    }
    


    protected ResponseEntity<List<Map<String,Object>>> ok(List<Map<String,Object>> list) {
        return new ResponseEntity<List<Map<String,Object>>>(list, HttpStatus.OK);
    }



    protected <T extends KModel> ResponseEntity<List<T>> okList(List<T> list) {
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

    protected <T extends KModel> ResponseEntity<ModelResultSet<T>> okList(ModelResultSet<T> result) {
        return new ResponseEntity<ModelResultSet<T>>(result, HttpStatus.OK);
    }



    protected ResponseEntity<List<Map<String,Object>>> ok(List<Map<String,Object>> list, HttpHeaders httpHeaders) {
        return new ResponseEntity<List<Map<String,Object>>>(list, httpHeaders, HttpStatus.OK);
    }



    protected <T extends KModel> ResponseEntity<List<T>> okList(List<T> list, HttpHeaders httpHeaders) {
        return new ResponseEntity<List<T>>(list, httpHeaders, HttpStatus.OK);
    }
    


    protected ApiLog logApiRequest(HttpServletRequest req, String endPoint) {
        return logApiRequest(req, endPoint, (String[]) null);
    }



    protected ApiLog logApiRequest(HttpServletRequest req, String endPoint, String... scopes) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        String methodName = null;
        String className = null;

        int maxLength = 20;

        if (stacktrace.length < maxLength) {
            maxLength = stacktrace.length;
        }


        logger.debug("logApiRequest: this.className: " + this.getClass().getName());

        for (int i=0; i<maxLength; i++) {
            StackTraceElement e = stacktrace[i]; 
            methodName = e.getMethodName();
            className = e.getClassName();

            logger.debug("logApiRequest["+i+"]: className: " + className + "   methodName: " + methodName);

            if (methodName.equals("getStackTrace") || methodName.equals("logApiRequest")) {
                continue;
            }

            if (className.equals(this.getClass().getName())) {
                break;
            }
        }

        logger.debug("logApiRequest: className: " + className + "   methodName: " + methodName);

        String clientId = apiAuthService.getClientId(req);

        if (clientId == null) {
            throw new BadRequestException("client_id not found in header or in the request parameters");
        }

        AppCreds creds = apiAuthService.fetchAppCredsByClientId(clientId);

        if (creds == null) {
            throw new AuthenticationException("Invalid clientId: " + clientId);
        }

        App app = apiAuthService.fetchAppById(creds.getAppId());

        String accessToken = apiAuthService.getAccessToken();

        if (accessToken == null) {
            try {
                accessToken = KServletUtil.getBearerToken(req);
            } catch (Exception e) { }

            if (accessToken != null) {
                throw new ValidationException("AccessToken not found in SecurityContext but specified in Authorization header");
            }
        }

        ApiLog log = new ApiLog();

        Token token = apiAuthService.fetchTokenByAccessToken(accessToken, false);

        if (token != null) {
            if (token.getAppClientId() != null && !token.getAppClientId().equals(clientId)) {
                system.alert("ClientId/Token mismatch", "\n--- clientId ---\n"
                        + clientId + "\n\n--- ntoken ---\n" + token);
                throw new ForbiddenException("Client not authorized to access resource");
            }


            // if we have a token, make sure it's authorized for this request
            if (scopes != null && token.getScope() != null && token.getScope().length() > 0) {
                List<String> scopeList = apiAuthService.toScopeList(token.getScope());
                for (String scope : scopes) {
                    if (!scopeList.contains(scope.trim().toLowerCase())) {
                        throw new ForbiddenException("Client not authorized to access resource");
                    }
                }
            }

            log.setAccessToken(accessToken);
            log.setUserId(token.getUserId());
        }

        log.setAppId(app.getId());
        log.setOwnerId(app.getUserId());
        log.setVersionId(creds.getApiVersionId());

        log.setAppClientId(clientId);
        log.setClassName(className);
        log.setMethodName(methodName);

        log.setEndPoint(endPoint);

        log.setHostname(KServletUtil.getClientHostname(req));
        log.setUserAgent(KServletUtil.getClientUserAgent(req));
        log.setLatitude(null);
        log.setLongitude(null);
        log.setCreatedDate(new Date());


        return apiLogService.add(log);
    }



    protected Map<String,Object> getSettings() {
        return settingService.getUserSettings(getUser());
    }



    // make sure current user is the same user or the parent of the userId we're checking
    protected void checkUserAuthorization(Long userId) {
        User user = userModelService.getUser(userId);
        checkUserAuthorization(user);
    }



    protected void checkUserAuthorization(User user) {
        User currentUser = getUser();

        if (!currentUser.getId().equals(user.getId()) && user.getParentId() != null && !currentUser.getId().equals(user.getParentId())) {
            throw new ForbiddenException("Access not allowed");
        }
    }



    protected KServiceClient getServiceClient(HttpServletRequest req) {
        return getServiceClient(null, null, null, req);
    }



    // sometimes the appId will be different from what is passed in to the request
    protected KServiceClient getServiceClient(Long appId, Long userId, Long deviceId, HttpServletRequest req) {
        KServiceClient client = new KServiceClient();

        if (appId == null) {
            appId = getAppId(req);
        }

        String accessToken = apiAuthService.getAccessToken(req);

        if (userId == null && accessToken != null) {
            User user = apiAuthService.fetchUserByAccessToken(accessToken);

            if (user != null) {
                userId = user.getId();
            }
        }

        if (accessToken != null) {
            Token token = tokenService.fetchByAccessToken(accessToken);

            if (token != null && !tokenService.isValid(token, false)) {
                client.setTokenId(token.getId());
                client.setAccessToken(accessToken);
            }
        }

        client.setAppId(appId);
        client.setUserId(userId);
        client.setDeviceId(deviceId);
        client.setAppClientId(apiAuthService.getClientId(req));
        client.setHostname(KServletUtil.getClientHostname(req));
        client.setUserAgent(KServletUtil.getClientUserAgent(req));
        client.setLatitude(KServletUtil.getClientLatitude(req));
        client.setLongitude(KServletUtil.getClientLongitude(req));
        client.setReferrerUrl(KServletUtil.getClientReferer(req));
        client.setRequestUrl(KServletUtil.getFullRequestURL(req));

        return client;
    }



    protected String[] getSearchSortOrder(String sort) {
        String[] sortOrder = null;
        if (sort != null) {
            sortOrder=sort.split(",");
        }
        return sortOrder;
    }



    protected File getUploadedFile(MultipartHttpServletRequest req, User user) {
        return getUploadedFile(req, user, null);
    }

    protected File getUploadedFile(MultipartHttpServletRequest req, User user, Long uploadTime) {
        try {
            File file = null;

            if (user == null) {
                user = getUser();
            } 

            String paramName = "file";

            if (req.getFiles(paramName) == null) {
                throw new BadRequestException("file parameter is empty");
            }

            List<File> fileList = fileUtil.upload(req, paramName, user, File.Access.PUBLIC);

            file = fileList.get(0);

            file.setUploadTime(uploadTime);

            if (file.getType() == File.Type.IMAGE) {
                KImage image = KImageUtil.toImage(file.getData());

                Integer width = image.getWidth();
                Integer height = image.getHeight();

                if (width < getImageMinWidth()  || height < getImageMinHeight()) {
                    throw new BadRequestException("The image is too small. The image must be at least " 
                            + getImageMinWidth() + "x" + getImageMinHeight() + "px.");
                }
            }

            return file;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(e);
        }
    }



    protected Media addMedia(MultipartHttpServletRequest req, User user, Map<String,Object> options) {
        try {

            if (options == null) {
                options = new HashMap<String,Object>();
            }

            Configuration config = new MapConfiguration(options);

            Long uploadTime = config.getLong("uploadTime", null);
            Double latitude = config.getDouble("latitude", null);
            Double longitude = config.getDouble("longitude", null);
            String description = config.getString("description", null);
            String folder = config.getString("folder", null);

            File file = getUploadedFile(req, user);

            file.setUploadTime(uploadTime);

            // NOTE: thumbnails will automatically be created if configured 
            // image.createThumbnail = true
            // image.thumbnailWidth = 400
            // image.thumbnailHeight = 400

            Media media = mediaService.add(file, folder, latitude, longitude, description);

            logger.debug("uploaded user media: " + media);

            return media;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(e);
        } 
    }   



    protected Object copyBean(Object source, Object target, boolean skipNullValues) {
        return util.copyBean(source, target, skipNullValues);
    }
    


    // https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html
    @ExceptionHandler(HttpMessageNotReadableException.class) 
    public ResponseEntity< Map<String,Object>> requestErrorHandler(Exception ex) throws Exception {
        
        String message = ex.getMessage();

        if (message.startsWith("JSON parse error: Unrecognized field")) {
            String[] parts = message.split("\\s+");

            String s = "";
            for (int i=0; i<6; i++) {
                s += parts[i] + " ";
            }

            s = s.trim();

            message = s;
        }

        String clientMessage = "Error processing request.";
        String developerMessage = clientMessage + " " + message;
        
        Map<String,Object> result = new HashMap<>();
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("code", HttpStatus.BAD_REQUEST.value());
        result.put("client_message", clientMessage);
        result.put("developer_message", developerMessage);

        return new ResponseEntity< Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity< Map<String,Object>> authErrorHandler(Exception ex) throws Exception {
        String message = ex.getMessage();

        String clientMessage = "Auth error.";
        String developerMessage = clientMessage + " " + message;

        Map<String,Object> result = new HashMap<>();
        result.put("status", HttpStatus.UNAUTHORIZED.value());
        result.put("code", HttpStatus.UNAUTHORIZED.value());
        result.put("client_message", clientMessage);
        result.put("developer_message", developerMessage);

        return new ResponseEntity< Map<String,Object>>(result, HttpStatus.UNAUTHORIZED);
    }

    // https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html
    @ExceptionHandler(Throwable.class)
    public ResponseEntity< Map<String,Object>> systemErrorHandler(Exception ex) throws Exception {
        if (ex instanceof ApiException) {
            throw ex;
        }

        String message = ex.getMessage();

        String clientMessage = "System error.";

        String developerMessage = clientMessage;

        if (message != null) {
            developerMessage += " " + message;
        }

        Map<String,Object> result = new HashMap<>();
        result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.put("client_message", clientMessage);
        result.put("developer_message", developerMessage);

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity< Map<String,Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    


    protected Map<String,Object> toFilterCriteria(String json) {
        if (json == null) {
            return null;
        }

        Map<String,Object> map = KJsonUtil.toMap(json);

        if (map == null) {
            throw new BadRequestException("Filter json must be specified");
        }

        return util.camelCaseKeys(map);
    }

}
