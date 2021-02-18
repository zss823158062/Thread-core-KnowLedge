package com.sen.thread.coreknowledge.stopthread;

/**
 * @class: RightWayStopThreadwithSleepEveryloop
 * @description: 循环中阻塞停止线程
 * @author: zhoushusen
 * @create: 2020-10-21 10:58
 **/
public class RightWayStopThreadwithSleepEveryloop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () ->{
            int num = 0 ;
            try {
                while (num <= 10000){
                    if (num % 100 == 0){
                        System.out.println(num + "是100的倍数");
                    }
                    Thread.sleep(10);
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
