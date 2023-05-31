package com.atlasian.practice.ratelimiter.tokenbucketv2;

import com.atlasian.practice.ratelimiter.tokenbucketv2.TokenBucketServiceV2;
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
public class TokenBucketServiceV2Test {

    @Test
    public void testTokenBucket() throws InterruptedException {
        TokenBucketServiceV2 tokenBucketServiceV2 = new TokenBucketServiceV2();
        List<Boolean> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=1; i<=100; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    result.add(tokenBucketServiceV2.isAllow("C1"));
                }
            });
            Thread.sleep(10);
        }

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        new Verifications(){
            {
                Assert.assertEquals(100, result.size());
                Assert.assertEquals(true, result.stream().filter(v -> v).count() > 10);
            }
        };
    }
}
