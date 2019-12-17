package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderAccess2 {
    public static void main(String[] args) {
        Test3 t=new Test3();
        /*new Thread(()->{
            t.print();
        },"A").start();
        new Thread(()->{
            t.print();
        },"B").start();
        new Thread(()->{
            t.print();
        },"C").start();*/
        for(int i=0;i<3;i++){
            new Thread(()->{
                t.print();
            },String.valueOf(i)).start();
        }
    }

}
class Test3{
    Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    int flag = 1;
    public void print(){
        if(Thread.currentThread().getName().equals("1")){
            lock.lock();
            try {
                while (flag != 1) {
                    c1.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"sdada"+i);
                }
                //通知从c2
                flag=++flag;
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        if(Thread.currentThread().getName().equals("2")){
            lock.lock();
            try {
                while (flag != 2) {
                    c2.await();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"sdada"+i);
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
        if(Thread.currentThread().getName().equals("3")){
            lock.lock();
            try {
                while (flag != 3) {
                    c3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName()+"sdada"+i);
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
}
