package com.example.demo.cache;

import com.example.demo.model.entity.Object;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryCache {

    private static final Logger logger = LoggerFactory.getLogger(MemoryCache.class);

    private static ConcurrentLinkedHashMap<Integer, Object> cache = null;

    private MemoryCache() {
        // Create a cache with LRU strategy.
        cache = new ConcurrentLinkedHashMap.Builder<Integer, Object>()
                .maximumWeightedCapacity(20).weigher(Weighers.singleton()).build();
    }

    private ConcurrentLinkedHashMap<Integer, Object> getCache() {
        return cache;
    }

    private static class Singleton {
        private static final MemoryCache INSTANCE = new MemoryCache();
    }

    public static MemoryCache getInstance() {
        return Singleton.INSTANCE;
    }

    public void setObjectToCache(Object object) {
        getCache().put(object.getId(), object);
        logger.info("MemoryCache : setObjectToCache objectId=[{}]", object.getId());
    }

    public Object getObjectInCache(Integer id) {
        logger.info("MemoryCache : getObjectInCache objectId=[{}]", id);
        return getCache().get(id);
    }

    public void updateObjectInCache(Object object) {
        getCache().put(object.getId(), object);
        logger.info("MemoryCache : updateObjectInCache objectId=[{}]", object.getId());
    }

}
