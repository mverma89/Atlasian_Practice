package com.atlasian.practice.ratelimiter.tokenbucketv2;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketV2 implements BucketV2{

    private AtomicInteger tokenBucket;
    private Integer maxToken;
    private Integer refillRatePerSecond;
    private Long lastRefillTs;

    public TokenBucketV2(Integer maxToken, Integer refillRatePerSecond) {
        this.maxToken = maxToken;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokenBucket = new AtomicInteger(maxToken);
        this.lastRefillTs = System.currentTimeMillis();
    }

    @Override
    public boolean isTokenAvailable() {

        refillToken();

        if(tokenBucket.get() > 0){
            synchronized (this){
                if(tokenBucket.get() > 0){
                    tokenBucket.decrementAndGet();
                    System.out.println(true);
                    return true;
                }
            }
        }
        System.out.println(false);
        return false;
    }

    @Override
    public void refillToken() {

        synchronized (this){
            long currentTs = System.currentTimeMillis();
            int tokenToBeRefill = (int)(currentTs - lastRefillTs)*refillRatePerSecond/1000;
            if(tokenToBeRefill > 0){
                if(tokenBucket.get()+tokenToBeRefill > maxToken){
                    tokenToBeRefill = maxToken - tokenBucket.get();
                }
                System.out.println("Refill "+tokenToBeRefill);
                lastRefillTs = currentTs;
                tokenBucket.addAndGet(tokenToBeRefill);
            }
        }
    }
}
