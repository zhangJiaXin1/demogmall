package com.atguigu.demogmall2.demogmall2.com.atguigu.blockingQueue;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        Ticket3 t=new Ticket3();
        //自定义线程池，因为java自带的线程池的阻塞队列的数值太大，每次浪费资源，只要阻塞队列的值不超过
        //最大的Integer.MAX就不会报错
//        ExecutorService threadPool= Executors.newFixedThreadPool(3);
        /*ExecutorService threadPool= Executors.newFixedThreadPool(3);*/
         ThreadPoolExecutor threadPool=new ThreadPoolExecutor(2,
                3,
                3l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()抛出异常策略
//                 new ThreadPoolExecutor.CallerRunsPolicy() 退到调用者让调用者调用，此处是main
//                 new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃运行时间最久的线程
                 new ThreadPoolExecutor.DiscardPolicy()//不处理
                 );

        //工具包自带的线程池
        try {
            for (int i = 0; i < 10; i++) {
                final int temp=i;
                threadPool.execute(()->t.saleTicket());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
            //一定要释放线程让其回归到线程池，否则会产生一直运行，用到线程池的原因节约资源不会被
            //垃圾回收处理，统一资源，提高管理
        }
        //自定义线程池,7个参数
    }
}
class Ticket3{
    private int number=30;
    private Lock lock=new ReentrantLock();
    public void saleTicket(){
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"还剩"+--number+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}
