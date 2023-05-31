package com.atlasian.practice.ratelimiter.tokenbucket;

public interface RefillStrategy {

    public void setBucket(Bucket bucket);
    public void refill();
}
