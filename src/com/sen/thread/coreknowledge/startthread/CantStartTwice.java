package com.sen.thread.coreknowledge.startthread;

/**
 * @class: CantStartTwice
 * @description: 不能重复执行start方法，否则会抛出异常
 * @author: zhoushusen
 * @create: 2020-10-19 15:58
 **/
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
