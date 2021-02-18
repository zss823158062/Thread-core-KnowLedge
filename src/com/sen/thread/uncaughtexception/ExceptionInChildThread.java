package com.sen.thread.uncaughtexception;

/**
 * @class: ExceptionInChildThread
 * @description: 子线程发生异常,单线程抛出、处理、有异常堆栈
 *               多线程，子线程发生异常
 * @author: zhoushusen
 * @create: 2020-11-20 17:59
 **/
public class ExceptionInChildThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 100;i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
