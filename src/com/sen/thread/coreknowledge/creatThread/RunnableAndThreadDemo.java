package com.sen.thread.coreknowledge.creatThread;

/**
 * @class: RunnableAndThreadDemo
 * @description: thread和runnable一起使用
 * @author: zhoushusen
 * @create: 2020-10-13 09:42
 **/
public class RunnableAndThreadDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("来自runnable执行");
            }
        })
        {
            @Override
            public void run() {
                System.out.println("来自thread执行");
            }
        }.start();
    }

}
