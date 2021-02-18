package com.sen.thread.coreknowledge.stopthread;

/**
 * @class: RightWayStopThreadwithsleep
 * @description: run方法内有sleep或者wait停止线程
 * @author: zhoushusen
 * @create: 2020-10-21 10:37
 **/
public class RightWayStopThreadwithsleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () ->{
            int num = 0 ;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0){
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
