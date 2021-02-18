package com.sen.thread.coreknowledge.stopthread;

/**
 * @class: RightWayStopTreadTOP2
 * @description: 恢复中断，重新设置中断状态，以供上层判断中断状态
 * @author: zhoushusen
 * @create: 2020-10-26 17:24
 **/
public class RightWayStopTreadTop2 implements Runnable {
    @Override
    public void run() {
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("GO");
                System.out.println("保存日志");
                break;
            }
                throwInterrupt();
        }
    }

    private void throwInterrupt(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopTreadTop2());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
