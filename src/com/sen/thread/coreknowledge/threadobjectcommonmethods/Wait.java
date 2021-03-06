package com.sen.thread.coreknowledge.threadobjectcommonmethods;

/**
 * @class: Wait
 * @description: 展示wait和notify的基本用法
 * 1、研究代码的执行顺序
 * 2、证明wait释放锁
 * @author: zhoushusen
 * @create: 2020-11-23 12:03
 **/
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        public void run(){
            synchronized (object){
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify");
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread1 t1 = new Thread1();
            t1.setName("线程1");
            Thread2 t2 = new Thread2();
            t2.setName("线程2");
            t1.start();
            Thread.sleep(1000);
            t2.start();
        }
    }
}
