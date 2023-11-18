package com.example.demo.service.impl;

import com.example.demo.cache.MemoryCache;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.Object;
import com.example.demo.service.IObjectService;
import com.google.gson.Gson;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ObjectServiceImpl implements IObjectService {

    private static final Logger logger = LoggerFactory.getLogger(ObjectServiceImpl.class);

    @Resource
    private MongoTemplate mongoTemplate;

    public String createObject(Object object) {
        if (object == null) {
            throw new ServiceException(Status.OBJECT_EMPTY);
        }
        if (object.getId() == null) {
            throw new ServiceException(Status.OBJECT_ID_EMPTY);
        }
        if (getObjectById(object.getId()) != null) {
            throw new ServiceException(String.format("Object with id %s exist", object.getId()));
        }
        mongoTemplate.insert(object);
        Gson gson = new Gson();
        logger.info("ObjectService : saveObject -->: object=[{}]", gson.toJson(object));
        return Status.INSERT_SUCCESSFULLY;
    }

    @Override
    public Object getObjectById(int id) {
        Object object = MemoryCache.getInstance().getObjectInCache(id);
        if (object == null) {
            logger.info("ObjectService : getObjectById : getFromDB -->: objectId=[{}]", id);
            Query query = Query.query(Criteria.where("id").is(id));
            object = mongoTemplate.findOne(query, Object.class);
            if (object != null) {
                MemoryCache.getInstance().setObjectToCache(object);
            }
        } else {
            logger.info("ObjectService : getObjectById : getFromCache -->: objectId=[{}]", id);
        }
        return object;
    }

    @Override
    public Boolean updateObject(Object object) {
        if (object == null) {
            throw new ServiceException(Status.OBJECT_EMPTY);
        }
        if (object.getId() == null) {
            throw new ServiceException(Status.OBJECT_ID_EMPTY);
        }
        Query query = new Query(Criteria.where("id").is(object.getId()));
        Update update = new Update()
                .set("name", object.getName())
                .set("objectItems",object.getObjectItems());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Object.class);
        if (result.getMatchedCount() > 0) {
            MemoryCache.getInstance().updateObjectInCache(object);
            return true;
        } else {
            return false;
        }
    }
}
