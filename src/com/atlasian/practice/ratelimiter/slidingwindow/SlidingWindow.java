package com.atlasian.practice.ratelimiter.slidingwindow;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SlidingWindow {

    private Queue<Long> tsWindow;
    private Long windowSize;
    private Integer windowCapacity;

    public SlidingWindow(Long windowSize, Integer windowCapacity) {
        this.windowSize = windowSize;
        this.windowCapacity = windowCapacity;
        this.tsWindow = new ArrayBlockingQueue<>(windowCapacity);
    }

    public boolean isAllowed(){
        refreshWindow();
        if(tsWindow.size() < windowCapacity){
            tsWindow.offer(System.currentTimeMillis());
            System.out.println(true);
            return true;
        }
        System.out.println(false);
        return false;
    }

    private void refreshWindow(){
        if(tsWindow.size() > 0){
            Long currentTs = System.currentTimeMillis();
            Long diff = currentTs-tsWindow.peek();

            while(diff > windowSize){
                tsWindow.poll();
                diff = currentTs - tsWindow.peek();
            }
        }
    }
}
