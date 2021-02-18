package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: WaitNotifyReleaseOwnMonitor
 * @description: 证明wait只释放当前的那把锁
 * @author: zhoushusen
 * @create: 2020-11-23 17:42
 **/
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock");
                    resourceB.notifyAll();
                    /*
                     * B通知其他线程争抢B锁,但是不会立马释放B锁,需要等同步块代码执行完
                     * A锁在B同步块释放了,导致A线程处于等待状态,B锁就释放不了了,而A锁则可以释放,则B线程可以获取到A锁,获取不到B锁
                     *
                     **/
//                    try {
//                        System.out.println("Th readA got releases resourceA lock");
//                        resourceA.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                try {
                    System.out.println("ThreadA got releases resourceA lock");
                    resourceA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("ThreadB got resourceA lock");
                System.out.println("ThreadB tried to resourceB lock");
                synchronized (resourceB) {
                    System.out.println("ThreadB got resourceB lock");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
