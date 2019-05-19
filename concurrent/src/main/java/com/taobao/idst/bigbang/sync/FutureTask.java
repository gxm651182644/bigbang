package com.taobao.idst.bigbang.sync;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class FutureTask {
    @Data
    public static class TaskResult {
        private Integer no;
        private String message;
    }

    private java.util.concurrent.FutureTask futureTask = new java.util.concurrent.FutureTask(
            new Callable<TaskResult>() {
                @Override
                public TaskResult call() throws Exception {
                    return buildResult();
                }
            }
    );
    private final Thread thread = new Thread(futureTask);

    private TaskResult buildResult() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TaskResult result = new TaskResult();
        result.setMessage("hello world");
        result.setNo(1);
        return result;
    }

    public TaskResult getFutureTask() {
        try {
            return (TaskResult) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void start() {
        thread.start();
    }


    public static void main(String[] args) {
        FutureTask task = new FutureTask();
        task.start();
        TaskResult result = task.getFutureTask();
        System.out.println(JSON.toJSONString(result));
    }
}
