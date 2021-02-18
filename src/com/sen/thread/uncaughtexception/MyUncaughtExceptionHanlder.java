package com.sen.thread.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class: MyUncaughtExceptionHanlder
 * @description: 自定义UncaughtExceptionHanlder
 * @author: zhoushusen
 * @create: 2020-11-26 10:59
 **/
public class MyUncaughtExceptionHanlder {

    public static void uncaugthException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，中止了" + t.getName(), e);
        System.out.println("捕获了异常" + t.getName() + "异常：" + e);
    }
}
