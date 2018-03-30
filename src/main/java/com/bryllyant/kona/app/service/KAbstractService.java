/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.entity.KEntityObject;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KRandomUtil;
import com.bryllyant.kona.util.KResultList;
import com.bryllyant.kona.util.KSystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class KAbstractService<
		ENTITY extends KEntityObject,
        ENTITY_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<ENTITY,ENTITY_EXAMPLE>>
        implements KEntityService<ENTITY> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractService.class);




    static class Query {
        private static AtomicLong _id = new AtomicLong();

        private Long id = null;
        private AtomicInteger retryCount = new AtomicInteger();

        public Query() {
            id = _id.incrementAndGet();
        }

        public Integer incRetryCount() {
            return retryCount.incrementAndGet();
        }

        public Long getId() {
            return id;
        }
    }


    protected abstract MAPPER getMapper();



//    protected abstract ENTITY_EXAMPLE getExampleObjectInstance(
//            Integer startRow,
//            Integer resultSize,
//			String[] sortOrder,
//            Map<String, Object> filter,
//            boolean distinct
//    );



    protected ENTITY getEntityObject() {
        try {
            Class<ENTITY> clazz = KClassUtil.getGenericTypeClass(this, 0);
            return clazz.newInstance();
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

    protected ENTITY_EXAMPLE getEntityExampleObject() {
        try {
            Class<ENTITY_EXAMPLE> clazz = KClassUtil.getGenericTypeClass(this, 1);
            return clazz.newInstance();
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t);
        }
    }

	protected ENTITY_EXAMPLE getEntityExample(Integer startRow, Integer resultSize, String[] sortOrder,
													 Map<String, Object> filter, boolean distinct) {
        ENTITY_EXAMPLE example = getEntityExampleObject();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

		example.setOffset(startRow);
		example.setLimit(resultSize);
		example.setDistinct(distinct);

		//KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);

        KMyBatisUtil.buildExample(example, filter);

		return example;
	}
    

    protected Long getMaxQueryRetryWaitTime() {
        return 500L;
    }
    

    protected Integer getMaxQueryRetryCount() {
        return 3;
    }
    

    private boolean retryQuery(Query query, Throwable t) {
        return retryQuery(query, t, null);
    }
    

    @SuppressWarnings("unused")
    private boolean retryQuery(Query query, Throwable t, boolean randomWait) {
        Long randomWaitTime = null;

        if (randomWait) {
            randomWaitTime = KRandomUtil.getRandomLong(1L, getMaxQueryRetryWaitTime());
        }

        return retryQuery(query, t, randomWaitTime);
    }

    private boolean retryQuery(Query query, Throwable t, Long waitTime) {
        if (t.getMessage().contains("Deadlock found when trying to get lock")
                || t.getMessage().contains("Lock wait timeout exceeded")
                || t.getMessage().contains("Connection timed out")
                || t.getMessage().contains("Connection is not available")
                || t.getMessage().contains("Communications link failure")
                || t.getMessage().contains("Read timed out")) {

            if (waitTime != null) {
                KSystemUtil.sleep(waitTime);
            }

            Integer retryCount = query.incRetryCount();
            
            if (retryCount > getMaxQueryRetryCount()) {
                return false;
            }

            logger.info("Query [{}] [Connection/Read timeout | Deadlock/Lock wait]: Retrying query [{} ms] [{} retries] ...", 
                    query.getId(), waitTime, retryCount);

            return true;
        }

        return false;
    }


    protected boolean entityHasBlobs() {
    	return false;
    }
    

    protected String[] getDefaultSortOrder() {
    	return null;
    }
    

	@Override @Transactional
	public ENTITY add(ENTITY entity) {
		return add(new Query(), entity);
	}


    @Transactional
    private ENTITY  add(Query query, ENTITY entity) {
        validate(entity);
        
        try {
            getMapper().insert(entity);
            return entity;
        } catch (Throwable ex) {
            if (retryQuery(query, ex)) {
                return add(query, entity);
            }

            throw ex;
        }
    }
	

	@Override @Transactional
	public ENTITY update(ENTITY entity) {
	    return update(new Query(), entity);
	}


	@Transactional
	private ENTITY update(Query query, ENTITY  entity) {
	    validate(entity);

	    try {
	        if (entityHasBlobs()) {
	            getMapper().updateByPrimaryKeyWithBLOBs(entity);
	        } else {
	            getMapper().updateByPrimaryKey(entity);
	        }

	        return entity;
	    } catch (Throwable ex) {
	        if (retryQuery(query, ex)) {
	            return update(query, entity);
	        }

	        throw ex;
	    }
	}


	@Override @Transactional
	public ENTITY save(ENTITY entity) {
		if (entity.getId() == null) {
			return add(entity);
		}
		
		return update(entity);
	}
	

	@Override @Transactional
	public void remove(ENTITY entity) {
		remove(new Query(), entity);
	}


	@Transactional
	private void remove(Query query, ENTITY  t) {
	    try {
	        getMapper().deleteByPrimaryKey(t.getId());
	    } catch (Throwable ex) {
	        if (retryQuery(query, ex)) {
	            remove(query, t);
	            return;
	        }

	        throw ex;
	    }
	}

	
	@Override @Transactional
	public void removeById(Long id) {
		ENTITY entity = fetchById(id);
		remove(entity);
	}
    

	@Override
	public void validate(ENTITY entity) {
	}

    @Override @Transactional(readOnly=true)
    public KResultList<ENTITY> fetchByCriteria(Map<String, Object> filter) {
	    return fetchByCriteria(0, 9999, null, filter, false);
    }


	@Override @Transactional(readOnly=true)
	public KResultList<ENTITY> fetchByCriteria(
	        Integer startRow,
            Integer resultSize,
			String[] sortOrder,
            Map<String, Object> filter,
            boolean distinct
    ) {
	    return fetchByCriteria(new Query(), startRow, resultSize, sortOrder, filter, distinct);
	}


	@Transactional(readOnly=true)
	private KResultList<ENTITY> fetchByCriteria(
	        Query query,
            Integer startRow,
            Integer resultSize,
	        String[] sortOrder,
            Map<String, Object> filter,
            boolean distinct
    ) {
		if (sortOrder == null) {
			sortOrder = getDefaultSortOrder();
		}

		ENTITY_EXAMPLE example = getEntityExample(
		        startRow,
                resultSize,
                sortOrder,
                filter,
                distinct
        );

		return fetchByCriteria(query, example);
    }

	@Transactional(readOnly=true)
	protected KResultList<ENTITY> fetchByCriteria(ENTITY_EXAMPLE example) {
        return fetchByCriteria(new Query(), example);
    }


//    @Transactional(readOnly=true)
//    protected KResultList<ENTITY> fetchByCriteria(ENTITY_EXAMPLE example) {
//        return fetchByCriteria(new Query(), example, 0);
//    }


	@Transactional(readOnly=true)
	protected KResultList<ENTITY> fetchByCriteria(Query query, ENTITY_EXAMPLE example) {
	    logger.debug("fetchByCriteria(): called");

	    try {
	        List<ENTITY> list = null;

	        logger.debug("fetchByCriteria: entityHasBlobs: " + entityHasBlobs());

	        if (entityHasBlobs()) {
	            list = getMapper().selectByExampleWithBLOBs(example);
	        } else {
	            list = getMapper().selectByExample(example);
	        }

            Integer startIndex = ((KEntityExample)example).getOffset();
	        Integer pageSize = ((KEntityExample)example).getLimit();

            ((KEntityExample)example).setLimit(null);
            ((KEntityExample)example).setOffset(null);

            Long totalCount = getMapper().countByExample(example);

            logger.debug("fetchByCriteria:"
                    + "\ntotal count: " + totalCount
                    + "\nfetched count: " + list.size());

	        KResultList<ENTITY> resultList = new KResultList<>();

	        resultList.setStartIndex(startIndex);
	        resultList.setTotalSize(totalCount);
	        resultList.setPageSize(pageSize);
	        resultList.setEndIndex(startIndex + list.size());

	        for (ENTITY  account : list) {
	            resultList.add(account);
	        }

	        return resultList;
	    } catch (Throwable ex) {
	        if (retryQuery(query, ex)) {
	            //return fetchByCriteria(query, startRow, resultSize, sortOrder, filter, distinct);
                return fetchByCriteria(query, example);
	        }

	        throw ex;
	    }
	}


	
	@Override @Transactional(readOnly=true)
	public ENTITY fetchById(Long id) {
	    return fetchById(new Query(), id);
	}



	@Transactional(readOnly=true)
	private ENTITY fetchById(Query query, Long id) {
	    try {
	        return getMapper().selectByPrimaryKey(id);
	    } catch (Throwable ex) {
	        if (retryQuery(query, ex)) {
	            return fetchById(query, id);
	        }

	        throw ex;
	    }
	}

    @Override @Transactional(readOnly=true)
    public ENTITY fetchByUid(String uid) {
        return fetchByUid(new Query(), uid);
    }

    @Transactional(readOnly=true)
    private ENTITY fetchByUid(Query query, String uid) {
        try {
            Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
            return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
        } catch (Throwable ex) {
            if (retryQuery(query, ex)) {
                return fetchByUid(query, uid);
            }

            throw ex;
        }
    }
	
	protected String uuid() {
		return KUtil.uuid();
	}


	protected Object copyBean(Object source, Object target, boolean skipNullValues) {

	    try {
	        KClassUtil.copy(source, target, skipNullValues);

	        return target;

	    } catch (IllegalAccessException | InvocationTargetException e) {
	        logger.error(e.getMessage(), e);
	        throw new KServiceException(e);
	    }
	}

}
