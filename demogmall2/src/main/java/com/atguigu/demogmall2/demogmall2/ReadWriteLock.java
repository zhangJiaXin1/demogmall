package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    //高并发支持下的读
    public static void main(String[] args) {
        Test t1=new Test();
        for(int i=0;i<10;i++){
            new Thread(()->{
                t1.write();
            },String.valueOf(i)).start();
        }
        for(int i=0;i<10;i++){
            new Thread(()->{
                t1.read();
            },String.valueOf(i)).start();
        }
    }
}
class Test{
    private ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    //读锁不加锁
    public void write(){
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"写操作开始");
        System.out.println(Thread.currentThread().getName()+"写操作结束");
        rwl.writeLock().unlock();
    }
    public void read(){
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"读操作开始");

        System.out.println(Thread.currentThread().getName()+"读操作结束");
        rwl.readLock().unlock();
    }

}
