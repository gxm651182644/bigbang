package com.taobao.idst.bigbang.sync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * demo of overwrite ThreadPollExecutor
 */
public class MyThreadExecutorPool extends ThreadPoolExecutor {

    public MyThreadExecutorPool() {
        super(1, 1, 1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        super.terminated();
    }
}

