package com.sen.thread.coreknowledge.stopthread;

/**
 * @class: Cantinterrupt
 * @description: while内try/catch的问题 不会中断线程
 * @author: zhoushusen
 * @create: 2020-10-26 16:52
 **/
public class Cantinterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () ->{
            int num = 0 ;

                while (num <= 10000){
                    if (num % 100 == 0){
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    try {
                    Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }

}
