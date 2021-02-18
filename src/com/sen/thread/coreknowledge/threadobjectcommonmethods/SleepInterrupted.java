package com.sen.thread.coreknowledge.threadobjectcommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @class: SleepInterrupted
 * @description: 每隔一秒输出当前时间，被中断，观察现象
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 * @author: zhoushusen
 * @create: 2020-11-24 17:35
 **/
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6500);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(new Date());
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("线程被中断了");
            e.printStackTrace();
        }
    }
}
