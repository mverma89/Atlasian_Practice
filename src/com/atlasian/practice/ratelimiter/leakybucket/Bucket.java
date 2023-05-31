package com.atlasian.practice.ratelimiter.leakybucket;

public interface Bucket {

    public boolean isAllowed(String clientId);

}
