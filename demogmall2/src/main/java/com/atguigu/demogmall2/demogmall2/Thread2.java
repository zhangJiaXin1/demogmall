package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread2 {
    //线程二是两个线程之间有联系,生产者和消费者的问题
    public static void main(String[] args) {
        Assicoted a = new Assicoted();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    a.reduce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    a.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }

}

class Assicoted {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition conditon = lock.newCondition();

    /* public synchronized void add(){
         //用的是synchronized
         if(number!=0){
             try {
                 this.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         ++number;
         System.out.println(Thread.currentThread().getName()+""+number);
         this.notify();
     }
     public synchronized void reduce(){
         if(number==0){
             try {
                 this.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         --number;
         System.out.println(Thread.currentThread().getName()+""+number);
         this.notify();
     }*/
    //用的是lock锁
    public void add() throws InterruptedException {
        lock.lock();
        if (number == 1) {
            conditon.await();
        }
        System.out.println(++number);
        conditon.signal();
        lock.unlock();
    }
    public void reduce() throws InterruptedException {
        lock.lock();
        if (number != 1) {
            conditon.await();
        }
        System.out.println(--number);
        conditon.signal();
        lock.unlock();
    }
}
