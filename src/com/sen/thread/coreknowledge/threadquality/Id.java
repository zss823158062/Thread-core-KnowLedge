package com.sen.thread.coreknowledge.threadquality;

/**
 * @class: Id
 * @description: id从1开始,
 * @author: zhoushusen
 * @create: 2020-11-20 16:07
 **/
public class Id {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的id:" + Thread.currentThread().getId());
        System.out.println("子线程的id =="+ thread.getId());
    }
}
