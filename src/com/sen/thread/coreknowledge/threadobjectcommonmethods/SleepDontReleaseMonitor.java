package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: SleepDontReleaseMonitor
 * @description: sleep的时候不会释放synchronized的monitor，等sleep时间到了之后才会正常的释放锁
 * @author: zhoushusen
 * @create: 2020-11-24 17:02
 **/
public class SleepDontReleaseMonitor implements Runnable{


    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("线程"+ Thread.currentThread().getName() + "获取到了monitor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+ Thread.currentThread().getName() + "退出了同步代码块");
    }
}
