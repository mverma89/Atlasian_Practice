package com.atlasian.practice.ratelimiter.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SlidingWindowService {

    private Map<String, SlidingWindow> slidingWindows;

    public SlidingWindowService() {
        this.slidingWindows = new HashMap<>();
    }

    public boolean canAccess(String clientId){
        initWindow(clientId);
        return slidingWindows.get(clientId).isAllowed();
    }

    private void initWindow(String clientId){
        if(!slidingWindows.containsKey(clientId)){
            slidingWindows.putIfAbsent(clientId, new SlidingWindow(TimeUnit.SECONDS.toMillis(1l), 10));
        }
    }
}
