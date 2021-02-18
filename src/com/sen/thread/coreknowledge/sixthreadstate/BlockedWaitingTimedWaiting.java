package com.sen.thread.coreknowledge.sixthreadstate;

/**
 * @class: BlockedWaitingTimedWaiting
 * @description: synchronized锁、等待、时间等待
 * @author: zhoushusen
 * @create: 2020-11-17 15:33
 **/
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        Thread.sleep(1000);
        // 打印出Timed_waiting状态因为正在执行Thread.sleep
        System.out.println(thread1.getState());
        // 打印blocked状态，因为thread2想拿到sync()锁缺拿不到sync的锁
        System.out.println(thread2.getState());
        Thread.sleep(1000);
        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
