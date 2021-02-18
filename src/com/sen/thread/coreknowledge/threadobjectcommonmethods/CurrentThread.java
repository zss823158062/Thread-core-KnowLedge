package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: CurrentThread
 * @description: 演示main，thread-0，thread-1
 * @author: zhoushusen
 * @create: 2020-11-25 16:07
 **/
public class CurrentThread{


    public static void main(String[] args) {
        run();
        new Thread(CurrentThread::run).start();
        new Thread(CurrentThread::run).start();
    }

    public static void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
