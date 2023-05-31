package com.atlasian.practice.ratelimiter.tokenbucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket implements Bucket{

    private AtomicInteger tokenBucket;
    private Integer maxBucketSize;
    private RefillStrategy refillStrategy;

    public TokenBucket(Integer maxBucketSize, RefillStrategy refillStrategy) {
        this.tokenBucket = new AtomicInteger(maxBucketSize);
        this.maxBucketSize = maxBucketSize;
        this.refillStrategy = refillStrategy;
        initRefillStrategy();
    }

    public void initRefillStrategy(){
        this.refillStrategy.setBucket(this);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                refillStrategy.refill();
            }
        });
        executorService.submit(thread);
    }

    @Override
    public Integer getMaxBucketSize() {
        return maxBucketSize;
    }

    @Override
    public Integer getAvailableTokens(){
        return tokenBucket.get();
    }

    @Override
    public synchronized boolean isTokenAvailable() {

        if(tokenBucket.get() > 0){
            tokenBucket.decrementAndGet();
            System.out.println("true");
            return true;
        }
        System.out.println("false");
        return false;
    }

    @Override
    public void refillToken(Integer token) {
        tokenBucket.addAndGet(token);
    }
}
