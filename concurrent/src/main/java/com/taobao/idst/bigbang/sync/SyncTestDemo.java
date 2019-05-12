package com.taobao.idst.bigbang.sync;

public class SyncTestDemo {
    Object lock1 = new Object();

    private void method1() {
        synchronized (lock1) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": is running");
        }

    }

    private synchronized void method2() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": is running");
        }
    }


    public static void main(String[] args) {
        SyncTestDemo syncTestDemo = new SyncTestDemo();

        Thread t = new Thread("t1") {
            @Override
            public void run() {
                syncTestDemo.method1();
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                syncTestDemo.method2();
            }
        };


        t.start();
        t2.start();
    }
}
