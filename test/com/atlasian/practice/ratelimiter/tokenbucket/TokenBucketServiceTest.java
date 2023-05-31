package com.atlasian.practice.ratelimiter.tokenbucket;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(JMockit.class)
public class TokenBucketServiceTest {

    @Test
    public void testTokenBucket() throws InterruptedException {

        TokenBucketService tokenBucketService = new TokenBucketService();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Boolean> result = new ArrayList<>();
        for(int c=1; c<=100; c++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    result.add(tokenBucketService.isAllowed("CL1"));
                }
            });
            Thread.sleep(100);
        }

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        new Verifications(){
            {
                Assert.assertEquals(100, result.size());
                Assert.assertEquals(true, result.stream().filter(p -> p).count() > 10);
            }
        };
    }
}
