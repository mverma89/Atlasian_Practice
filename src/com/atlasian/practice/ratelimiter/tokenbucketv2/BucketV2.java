package com.atlasian.practice.ratelimiter.tokenbucketv2;

public interface BucketV2 {

    public boolean isTokenAvailable();
    public void refillToken();
}
