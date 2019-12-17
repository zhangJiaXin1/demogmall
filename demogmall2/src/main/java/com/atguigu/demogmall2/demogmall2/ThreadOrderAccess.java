package com.atguigu.demogmall2.demogmall2;

import jdk.nashorn.internal.ir.JumpToInlinedFinally;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderAccess {
    //设计三个线程，A线程打印5次，B线程打印10次，C线程打印15次,让线程有序进行(循环进行)
    public static void main(String[] args) {
        Test2 t = new Test2();
        /*for(int i=0;i<3;i++){
            new Thread(()->{
                t.
            },String.valueOf(i)).start();
        }*/
        for (int i = 0; i < 10; i++) {


        new Thread(()->{
            t.print5();
        },"A").start();
        new Thread(()->{
            t.print10();
        },"B").start();
        new Thread(()->{
            t.print15();
        },"C").start();
        }
    }
}

class Test2 {
    //线程操作资源类
    //判断  干活  通知
    //用while
    //修改标识符
    Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    int flag = 1;

    public void print5() {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            //通知从c2
            flag=2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void print10() {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            //通知从c2
            flag=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print15() {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            //通知从c2
            flag=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}