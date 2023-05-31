package com.atlasian.practice.ratelimiter.tokenbucket;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketService {

    private Map<String, Bucket> clientBuckets;

    public TokenBucketService() {
        clientBuckets = new HashMap<>();
    }

    public boolean isAllowed(String clientId){
        initClientBucket(clientId);
        return clientBuckets.get(clientId).isTokenAvailable();
    }

    private void initClientBucket(String clientId){
        if(!clientBuckets.containsKey(clientId)){
            RefillStrategy refillStrategy = new IntervalRefillStrategy(2, 500l);
            Bucket bucket = new TokenBucket(5, refillStrategy);
            clientBuckets.putIfAbsent(clientId, bucket);
        }
    }
}
