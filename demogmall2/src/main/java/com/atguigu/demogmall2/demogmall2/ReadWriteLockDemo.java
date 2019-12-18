package com.atguigu.demogmall2.demogmall2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //读的时候调用读锁，写的时候调用写锁,高并发10个线程
        Test6 t=new Test6();
        /*for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    t.write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    t.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }*/
        for (int i = 1; i <=10; i++) {
            final int tempI = i;
            new Thread(() -> {
                t.put(tempI+"",tempI+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            final int tempI = i;
            new Thread(() -> {
                t.get(tempI+"");
            },String.valueOf(i)).start();
        }

    }
    }

class Test6{
    ReadWriteLock rwl=new ReentrantReadWriteLock();
    private volatile Map<String,String> map = new HashMap<>();
    public void put(String key,String value)
    {
        rwl.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");
        }finally {
            rwl.writeLock().unlock();
        }
    }
    public void get(String key)
    {
        rwl.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束: "+result);
        }finally {
            rwl.readLock().unlock();
        }
    }
}