package com.sen.thread.coreknowledge.stopthread;

/**
 * @class: RightWayStopThreadWithoutSleep
 * @description: run方法内没有sleep或者wait停止线程
 * @author: zhoushusen
 * @create: 2020-10-20 11:27
 **/
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2){
            if (num % 10000 == 0){
                System.out.println(num + "是10000的整数");
            }
            num++;
        }
        System.out.println("任务运行结束！！！");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
