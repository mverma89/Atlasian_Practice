package com.atlasian.practice.ratelimiter.leakybucket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LeakyBucket implements Bucket{

    private BlockingQueue<String> leakyBucket;
    private Integer maxCapacity;

    public LeakyBucket(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.leakyBucket = new ArrayBlockingQueue<>(maxCapacity);
        Executors.newSingleThreadExecutor().submit(new LeakyBucketConsumer(leakyBucket));
    }

    @Override
    public boolean isAllowed(String clientId) {
        return leakyBucket.offer(clientId);
    }
}
