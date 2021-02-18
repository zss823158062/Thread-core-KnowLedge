package com.sen.thread.deadlock;

import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;

import java.util.TooManyListenersException;

/**
 * @class: TransferMoney
 * @description: 转账时遇到死锁，一旦打开注释，便会发生死锁
 * @author: zhoushusen
 * @create: 2021-01-15 13:53
 **/
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额"+ a.balance);
        System.out.println("b的余额"+ b.balance);
    }

    @Override
    public void run() {
        if (flag == 1){
            // a转b 200
            transferMoney(a,b,200);
        }
        if (flag == 0){
            // b转a 200
            transferMoney(b,a,200);
        }
    }
    static class Account{
        public Account(int balance) {
            this.balance = balance;
        }
        int balance;
    }

    public static void transferMoney(Account from, Account to,int amount){
        synchronized (from){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to){
                if (from.balance - amount < 0){
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账"+amount+"元");
            }
        }
    }
}
