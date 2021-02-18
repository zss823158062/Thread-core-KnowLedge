package com.sen.thread.coreknowledge.startthread;

/**
 * @class: StartAndRunMethod
 * @description: run方法和start方法启动线程
 * @author: zhoushusen
 * @create: 2020-10-19 15:34
 **/
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();
        new Thread(runnable).start();
    }

}
