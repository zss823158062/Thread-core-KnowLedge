package com.sen.thread.coreknowledge.creatThread;

/**
 * @class: RunableDemo
 * @description: runable接口创建线程
 * @author: zhoushusen
 * @create: 2020-09-30 16:23
 **/
public class RunnableDemo implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new RunnableDemo());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("实现了一个runnable接口创建了一个线程");
    }
}
