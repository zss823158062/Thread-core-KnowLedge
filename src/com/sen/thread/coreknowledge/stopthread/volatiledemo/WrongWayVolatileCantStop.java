package com.sen.thread.coreknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @class: WrongWayVolatileCantStop
 * @description: 使用生产者消费者模式演示volatile关键字无效
 * @author: zhoushusen
 * @create: 2020-10-30 09:50
 **/
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Object> storage = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据");
        producer.canceled = true;
    }
}
class Producer implements Runnable{
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + " 是100的倍数,被放入到仓库中");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer{

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        double random = Math.random();
        return !(random > 0.95);
    }
}
