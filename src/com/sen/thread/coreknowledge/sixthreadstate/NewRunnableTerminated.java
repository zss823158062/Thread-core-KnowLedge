package com.sen.thread.coreknowledge.sixthreadstate;

/**
 * @class: NewRunnableTerminated
 * @description: 新建运行停止状态，即使是正在运行， 也是runnbale状态
 * @author: zhoushusen
 * @create: 2020-11-17 15:02
 **/
public class NewRunnableTerminated implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try{
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(thread.getState());
        try{
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0;i<1000;i++){
            System.out.println(i);
        }
    }
}
