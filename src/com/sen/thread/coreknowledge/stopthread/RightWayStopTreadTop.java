package com.sen.thread.coreknowledge.stopthread;


/**
 * @class: RightWayStopTreadTop
 * @description: 传递中断
 * @author: zhoushusen
 * @create: 2020-10-26 17:24
 **/
public class RightWayStopTreadTop implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("GO");
            try {
                throwInterrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("保存日志");
                break;
            }
        }
    }

    private void throwInterrupt() throws InterruptedException {
            Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopTreadTop());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
