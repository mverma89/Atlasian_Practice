package com.atlasian.practice.ratelimiter.leakybucket;

import java.util.HashMap;
import java.util.Map;

public class LeakyBucketService {

    private Map<String, Bucket> leakyBuckets;

    public LeakyBucketService() {
        this.leakyBuckets = new HashMap<>();
    }

    public boolean isAllowed(String clientId){
        if(!leakyBuckets.containsKey(clientId)){
            init(clientId);
        }
        return leakyBuckets.get(clientId).isAllowed(clientId);
    }

    private void init(String clientId){
        leakyBuckets.putIfAbsent(clientId, new LeakyBucket(10));
    }
}
