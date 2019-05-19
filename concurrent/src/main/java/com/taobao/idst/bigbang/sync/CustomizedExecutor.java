package com.taobao.idst.bigbang.sync;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomizedExecutor {

    private ScheduledExecutorService executorSingleScheduled;

    public static class SingleTaskExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber;
        private final String namePrefix;

        private NamedThreadFactory(String name) {
            this.threadNumber = new AtomicInteger(1);
            SecurityManager s = System.getSecurityManager();
            this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "metrics-" + name + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            t.setDaemon(true);
            if (t.getPriority() != 5) {
                t.setPriority(5);
            }

            return t;
        }
    }


    public CustomizedExecutor() {
        executorSingleScheduled =
                Executors.newSingleThreadScheduledExecutor(new CustomizedExecutor.NamedThreadFactory("test"));
        executorSingleScheduled.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {

                } catch (RuntimeException var2) {
                }
            }
        }, 1L, 1L, TimeUnit.SECONDS);
    }

    private static final Executor executor = Executors.newFixedThreadPool(10);
    private static final Executor executorCached = Executors.newCachedThreadPool();


    public static void test() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        executor.execute(r);
    }
}
