package com.taobao.idst.bigbang.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceDemo {

    private ExecutorService executor;

    public CompletionServiceDemo(ExecutorService executor) {
        this.executor = executor;
    }

    void demoTest() {
        List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
        }};
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);

        for (String s : list) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + ": is running,out string is " + s);
                    return s + " is outing";
                }
            });
        }

        for (int i = 0; i < list.size(); i++) {
            try {
                Future<String> future = completionService.take();
                System.out.println("get future result:" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionServiceDemo demo = new CompletionServiceDemo(executor);
        demo.demoTest();
    }
}
