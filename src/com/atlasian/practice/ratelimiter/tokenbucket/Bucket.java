package com.atlasian.practice.ratelimiter.tokenbucket;

public interface Bucket {

    public boolean isTokenAvailable();
    public void refillToken(Integer token);
    public Integer getAvailableTokens();
    public Integer getMaxBucketSize();
}
