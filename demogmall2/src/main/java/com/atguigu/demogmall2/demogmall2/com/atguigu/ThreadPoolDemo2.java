package com.atguigu.demogmall2.demogmall2.com.atguigu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
    //自定义线程池,当队列中的容量不满的时候不会启用非核心的线程
    //理解：访问线程，当并发的请求大于核心的线程数的时候，会先让请求在队列中等待，当队列满的时候，会启用
    //非核心线程，如果线程的请求过快，队列还是满的状态，就会启用线程的拒绝策略
    //如果请求减少了，线程会根据空闲时间自动停掉非核心线程，回到初始状态
    public static void main(String[] args) {
        Thread7 t=new Thread7();
        ThreadPoolExecutor threadPool=new ThreadPoolExecutor(2,
                5,
                2l, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(14),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
                );
        try {
            for (int i = 0; i <34 ; i++) {

                threadPool.execute(()->t.saleTicket());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
class Thread7{
    private int number=30;
    //用表锁
    public synchronized void saleTicket(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"票的数量"+--number);
        }else {
            System.out.println("票的数量不足");
        }
    }
}