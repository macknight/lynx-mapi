package com.lynx.geo.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-8-13 上午9:33
 */
public class HttpService extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final AtomicLong taskNum = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public HttpService(int corePoolSize, int maxPoolSize, long keepAliveTime,
                       TimeUnit timeUnit, BlockingQueue<Runnable> workQueue,
                       RejectedExecutionHandler handler) {
        super(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            taskNum.incrementAndGet();
            totalTime.addAndGet(taskTime);
            System.out.println(String.format("Thread %s: end %s, time=%s", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            System.out.println(String.format("Terminated: avg time=%s", totalTime.get() / taskNum.get()));
        } finally {
            super.terminated();
        }
    }

    private static class Task implements Runnable {
        private String id;

        public Task(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println(id);
                Thread.sleep(1000);
                if (id.equals("51")) {
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        HttpService httpService = new HttpService(3, 4, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; ++i) {
            try {
                String id = "task-" + i;
                System.out.println("add " + id);
                httpService.execute(new Task(id));

                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(5000);
        httpService.shutdown();
        System.out.println(httpService.getTaskCount() + " c:" + httpService.getCompletedTaskCount());
    }
}
