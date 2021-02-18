package com.sen.thread.coreknowledge.creatThread;

/**
 * @class: ThreadDemo
 * @description: 继承thread类创建线程
 * @author: zhoushusen
 * @create: 2020-09-30 16:30
 **/
public class ThreadDemo extends Thread {

    @Override
    public void run() {
        System.out.println("继承thread类实现线程，重写run方法");
    }

    public static void main(String[] args) {
        new ThreadDemo().start();
    }

}
