package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    //两个线程之间的联系
    public static void main(String[] args) {
        Test5 t=new Test5();
        /*for(int i=0;i<10;i++){
            new Thread(()->{
                t
            },String.valueOf(i)).start();
        }*/
        new Thread(()-> { for (int i = 0; i < 10; i++) t.add();},"A").start();
        new Thread(()-> { for (int i = 0; i < 10; i++) t.add();},"B").start();
    }
}
class  Test5{
    private int number=0;
    private Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    public void add(){
        lock.lock();
        try {
            while(number==1){
                condition.await();
            }
            System.out.println(++number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void reduce(){
        lock.lock();
        try {
            while(number!=1){
                condition.await();
            }
            System.out.println(--number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}