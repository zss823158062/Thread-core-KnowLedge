package com.sen.thread.coreknowledge.threadobjectcommonmethods;


/**
 * @class: WaitNotifyAll
 * @description: 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们
 * 1、start先执行不代表线程先启动
 * @author: zhoushusen
 * @create: 2020-11-23 15:56
 **/
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resourceA lock");
            try {
                System.out.println(Thread.currentThread().getName() + "wait to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "is waiting end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
//                    resourceA.notify();
                System.out.println("ThreadC notifyed");
            }
        });
        threadA.setName("线程1");
        threadB.setName("线程2");
        threadC.setName("线程3");
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
    }
}
