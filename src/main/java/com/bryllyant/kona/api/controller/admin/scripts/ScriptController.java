package com.bryllyant.kona.api.controller.admin.scripts;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.ModelResultSet;
import com.bryllyant.kona.api.model.script.ScriptModel;
import com.bryllyant.kona.api.service.ScriptModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.service.ScriptService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.util.AppUtil;
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
 * Script Controller.
 */
@RestController
@RequestMapping("/api/admin/scripts")
public class ScriptController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ScriptController.class);



    @Autowired
    private ScriptService scriptService;
    
    @Autowired
    private ScriptModelService scriptModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private SystemService system;

    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ModelResultSet<ScriptModel>> search(HttpServletRequest req,
                                                              @RequestParam(value="q", required=false) String query,
                                                              @RequestParam(value="sort", required=false) String[] sortOrder,
                                                              @RequestParam(value="offset", required=false) Integer offset,
                                                              @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/scripts");

        logger.debug("ScriptController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        if (sortOrder == null) {
            sortOrder = new String[]{
                "createdDate DESC"
            };
        }

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 999;
        }

        logger.debug("ScriptController: filter: " + KJsonUtil.toJson(filter));

        KResultList result = scriptService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, scriptModelService.toModelList(result));

        return okList(resultSet);
    }





    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<ScriptModel> get(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "GET /admin/scripts/" + uid);

        Script script = scriptModelService.getEntity(uid);

        return ok(scriptModelService.toModel(script));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ScriptModel> create(HttpServletRequest req,
            @RequestBody ScriptModel model) {
        logApiRequest(req, "POST /admin/scripts");

        Script script = new Script();

        script = saveObject(req, script, model);

        return created(scriptModelService.toModel(script));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<ScriptModel> update(HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody ScriptModel model) {
        logApiRequest(req, "PUT /admin/scripts/" + uid);

        Script script = scriptModelService.getEntity(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }
        
        script = saveObject(req, script, model);

        return ok(scriptModelService.toModel(script));
    }


    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<ScriptModel> remove(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "DELETE /admin/scripts/" + uid);

        Script script = scriptModelService.getEntity(uid);

        scriptService.remove(script);

        //return ok(scriptModelService.toModel(script));
        return ok(ScriptModel.create(script.getUid()));
    }


    public Script saveObject(HttpServletRequest req, Script script, ScriptModel model) {
        logger.debug("mapToObject called for script: " + script);
        
        script = scriptModelService.mergeEntity(script, model);

        if (script.getId() == null && model.getEnabled() == null) {
            script.setEnabled(true);
        }

        return scriptService.save(script);
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
//                case "ownerUid":
//                    Long id = -1L;
//                    User owner = userModelService.getUser(util.getStringValue(value));
//                    if (owner != null) id = owner.getId();
//                    result.put(prefix + "ownerId", id);
//                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    } 

}

