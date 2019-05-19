package com.taobao.idst.bigbang.sync;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private ThreadGroup threadGroup;
    private String threadPoolName;
    private static AtomicInteger threadNumber;


    public MyThreadFactory(String threadPoolName) {
        this.threadGroup = Thread.currentThread().getThreadGroup();
        this.threadPoolName = threadPoolName;
        this.threadNumber = new AtomicInteger(1);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, threadPoolName + "_" + threadNumber.addAndGet(1), 0);
        return t;
    }


}
