package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: TwoThreadAlternatePrint
 * @description: 双线程交替打印1到100的奇偶数 synchronized关键字实现
 * @author: zhoushusen
 * @create: 2020-11-24 11:26
 **/
public class TwoThreadAlternatePrint {
    private static final Object lock = new Object();
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // 打印偶数
        Thread thread1 = new Thread(TwoThreadAlternatePrint::go, "线程A");
        // 打印奇数
        Thread thread2 = new Thread(TwoThreadAlternatePrint::go, "线程B");
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }
    private static void go() {
        while (count <= 100) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "打印了" + count++);
                // 通知其他线程争抢锁
                lock.notify();
                try {
                    // 释放锁
                    if (count<=100)
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


