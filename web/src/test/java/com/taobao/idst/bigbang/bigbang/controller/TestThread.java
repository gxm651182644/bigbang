package com.taobao.idst.bigbang.bigbang.controller;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestThread {

    @Test
    public void test() {
        Executor executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            executor.execute(new worker());
        }

    }

    public static class worker extends Thread {
        @Override
        public void run() {
            System.out.print(Thread.currentThread().getName() + " is running" + "\n");
        }
    }
}

