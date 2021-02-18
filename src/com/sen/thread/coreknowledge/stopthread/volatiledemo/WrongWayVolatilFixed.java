package com.sen.thread.coreknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @class: WrongWayVolatilFixed
 * @description: volatile生产者修复
 * @author: zhoushusen
 * @create: 2020-10-30 16:12
 **/
public class WrongWayVolatilFixed {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Object> storage = new ArrayBlockingQueue<>(10);
        WrongWayVolatilFixed body = new WrongWayVolatilFixed();
        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据");
        producerThread.interrupt();
    }
    class Producer implements Runnable{
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
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
            if (random > 0.95){
                return false;
            }
            return true;
        }
    }
}
