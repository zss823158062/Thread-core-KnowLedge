package com.sen.thread.uncaughtexception;

/**
 * @class: UseOwnUncaughtExceptionHandler
 * @description: 使用自定义UncaughtExceptionHandler
 * @author: zhoushusen
 * @create: 2020-11-26 11:11
 **/
public class UseOwnUncaughtExceptionHandler {

    public static void main(String[] args) throws InterruptedException {

        Thread.setDefaultUncaughtExceptionHandler(MyUncaughtExceptionHanlder::uncaugthException);

        new Thread(CantCatchDirectly::run, "thread-1").start();
        Thread.sleep(300);
        new Thread(CantCatchDirectly::run, "thread-2").start();
        Thread.sleep(300);
        new Thread(CantCatchDirectly::run, "thread-3").start();
        Thread.sleep(300);
        new Thread(CantCatchDirectly::run, "thread-4").start();
        Thread.sleep(300);
    }

    public static void run() {
        throw new RuntimeException();
    }
}
