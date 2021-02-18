package com.sen.thread.deadlock;

/**
 * @class: MustDeadLock
 * @description: 必定发生死锁的情况
 * @author: zhoushusen
 * @create: 2021-01-15 09:53
 **/
public class MustDeadLock implements Runnable {
    int flag = 1;

    static final Object o1 = new Object();
    static final Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    /* 线程1启动，获取o1锁并持有;等待500ms，要获取o2锁;然而
    *  在等待的过程中线程2启动，获取o2锁并持有;等待500ms后要获取o1锁,此时o1锁是在线程1持有，而
    * 线程1要释放o1锁需要获得o2锁，但o2锁在线程2持有，线程2释放o2锁要获得o1锁。从而造成了死锁 */
    @Override
    public void run() {
        System.out.println(flag + "  ==============");
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}
