package com.atlasian.practice.ratelimiter.tokenbucket;

public class IntervalRefillStrategy implements RefillStrategy{

    private Bucket bucket;
    private Integer tokenFillRate;
    private Long tokenFillInterval;

    public IntervalRefillStrategy(Integer tokenFillRate, Long tokenFillInterval) {
        this.tokenFillRate = tokenFillRate;
        this.tokenFillInterval = tokenFillInterval;
    }

    @Override
    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void refill(){
        while (true){
            try {
                int refillTokens = Math.min(bucket.getAvailableTokens()+tokenFillRate, bucket.getMaxBucketSize())-bucket.getAvailableTokens();
                //System.out.println("refill "+refillTokens);
                bucket.refillToken(refillTokens);
                Thread.sleep(tokenFillInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
