package com.atlasian.practice.ratelimiter.slidingwindow;

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
import java.util.stream.Collectors;

@RunWith(JMockit.class)
public class SlidingWindowServiceTest {

    @Test
    public void testSlidingWindow() throws InterruptedException {
        SlidingWindowService slidingWindowService = new SlidingWindowService();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Boolean> result = new ArrayList<>();
        //slidingWindowService.canAccess("C1");
        for(int i=1; i<=100; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    result.add(slidingWindowService.canAccess("C1"));
                }
            });
        }
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        new Verifications(){
            {
                Assert.assertEquals(100, result.size());
                Assert.assertEquals(true, result.stream().filter(v -> v).collect(Collectors.toList()).size() > 10);
            }
        };
    }
}
