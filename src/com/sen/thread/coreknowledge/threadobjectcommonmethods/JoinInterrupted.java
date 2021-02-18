package com.sen.thread.coreknowledge.threadobjectcommonmethods;

import sun.awt.ScrollPaneWheelScroller;

import java.time.temporal.Temporal;

/**
 * @class: JoinInterrupted
 * @description: join遇到中断情况
 * @author: zhoushusen
 * @create: 2020-11-25 10:11
 **/
public class JoinInterrupted {

    public static void main(String[] args) {
        Thread thread1 = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                thread1.interrupt();
                Thread.sleep(5000);
                System.out.println("子线程运行完毕");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        try {
            // 捕获到的是主线程的中断
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"主线程中断了");
//            e.printStackTrace();
            // 主线程的中断要处理子线程停止
            thread.interrupt();
        }
        System.out.println("子线程已运行完毕");
    }
}
