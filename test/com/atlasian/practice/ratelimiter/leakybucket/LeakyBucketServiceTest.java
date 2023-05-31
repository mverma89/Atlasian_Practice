package com.atlasian.practice.ratelimiter.leakybucket;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(JMockit.class)
public class LeakyBucketServiceTest {

    @Test
    public void testLeakyBucket() throws InterruptedException {
        LeakyBucketService leakyBucketService = new LeakyBucketService();
        List<Boolean> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=1; i<=100; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    boolean f = leakyBucketService.isAllowed("C1");
                    System.out.println(f);
                    result.add(f);
                }
            });
            Thread.sleep(10);
        }

        new Verifications(){
            {
                Assert.assertEquals(100, result.size());
                Assert.assertEquals(true, result.stream().filter(v -> v).count() > 10);
            }
        };

    }
}
