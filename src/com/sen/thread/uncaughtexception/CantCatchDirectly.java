package com.sen.thread.uncaughtexception;

/**
 * @class: CantCatchDirectly
 * @description: 不加 try catch，抛出4个异常，都带线程名字
 * 加了try catch 期望捕获到第一个线程的异常,234不应该运行
 * @author: zhoushusen
 * @create: 2020-11-25 17:36
 **/
public class CantCatchDirectly {

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(CantCatchDirectly::run,"thread-1").start();
            Thread.sleep(300);
            new Thread(CantCatchDirectly::run,"thread-2").start();
            Thread.sleep(300);
            new Thread(CantCatchDirectly::run,"thread-3").start();
            Thread.sleep(300);
            new Thread(CantCatchDirectly::run,"thread-4").start();
            Thread.sleep(300);
        }catch (Exception e){
            System.out.println("抛出了异常");
        }
    }

    public static void run(){
        throw new RuntimeException();
    }
}
