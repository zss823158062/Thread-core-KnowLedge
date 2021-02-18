package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: Join
 * @description: 演示join的普通用法；注意语句的顺序。
 * @author: zhoushusen
 * @create: 2020-11-25 09:29
 **/
public class Join {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                int a = 1 + 1;
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("子线程停止运行");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        thread.start();
        System.out.println("开始等待子线程运行完毕");
        // 等待2秒,如果子线程两秒内没运行完成,则先继续下面的,且不会抛异常
        try {
            System.out.println("等待2s的子线程超时");
            thread.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 利用同步代码块和wait方法代替join
//        synchronized (thread){
//            // 子线程等待
//            thread.wait();
//        }
        System.out.println("所有子线程运行完毕");
        // 主线程都运行完成后,中断子线程
        thread.interrupt();
    }
}
