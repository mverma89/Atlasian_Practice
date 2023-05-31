package com.atlasian.practice.ratelimiter.leakybucket;

import java.util.concurrent.BlockingQueue;

public class LeakyBucketConsumer implements Runnable{

    private BlockingQueue<String> leakyBucket;

    public LeakyBucketConsumer(BlockingQueue<String> leakyBucket) {
        this.leakyBucket = leakyBucket;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
                leakyBucket.poll();
                System.out.println("polled");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
