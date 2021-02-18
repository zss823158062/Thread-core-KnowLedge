package com.sen.thread.coreknowledge.stopthread.volatiledemo;

/**
 * @class: WrongwayVolatile
 * @description: 演示用volatile的局限
 * @author: zhoushusen
 * @create: 2020-10-29 11:19
 **/
public class WrongwayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + " 是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongwayVolatile wrongwayVolatile = new WrongwayVolatile();
        Thread thread = new Thread(wrongwayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongwayVolatile.canceled = true;
    }
}
