package com.sen.thread.coreknowledge.threadobjectcommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * @class: ProducerConsumerModel
 * @description: 生产者消费者模式
 * @author: zhoushusen
 * @create: 2020-11-24 10:34
 **/
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStroage stroage = new EventStroage();
        Producer producer = new Producer(stroage);
        Consumer consumer = new Consumer(stroage);
        Thread pthread = new Thread(producer, "生产者");
        Thread cthread = new Thread(consumer, "消费者");
        pthread.start();
        cthread.start();
    }
}

// 生产者
class Producer implements Runnable {
    private EventStroage stroage;

    public Producer(EventStroage stroage) {
        this.stroage = stroage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            stroage.put();
        }
    }
}

// 消费者
class Consumer implements Runnable {

    private EventStroage stroage;

    public Consumer(EventStroage stroage) {
        this.stroage = stroage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            stroage.take();
        }
    }
}


class EventStroage {
    private int maxsize;
    private LinkedList<Date> storage;

    public EventStroage() {
        maxsize = 10;
        storage = new LinkedList<>();
    }

    // 生产物品
    public synchronized void put() {
        // 如果仓库满了,等待
        while (storage.size() == maxsize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        // 通知可以消费了
        notify();
    }

    // 消费物品
    public synchronized void take() {
        // 如果仓库空了等待
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + ",现在仓库还剩下" + storage.size());
        notify();
    }
}
