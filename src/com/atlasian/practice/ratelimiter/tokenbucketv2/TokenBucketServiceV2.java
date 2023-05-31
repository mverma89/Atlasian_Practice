package com.atlasian.practice.ratelimiter.tokenbucketv2;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketServiceV2 {

    private Map<String, TokenBucketV2> tokenBuckets;

    public TokenBucketServiceV2() {
        this.tokenBuckets = new HashMap<>();
    }

    public boolean isAllow(String clientId){
        if(!tokenBuckets.containsKey(clientId))
            initTokenBucket(clientId);

        return tokenBuckets.get(clientId).isTokenAvailable();
    }

    private void initTokenBucket(String clientId){
        tokenBuckets.putIfAbsent(clientId, new TokenBucketV2(10, 10));
    }
}
