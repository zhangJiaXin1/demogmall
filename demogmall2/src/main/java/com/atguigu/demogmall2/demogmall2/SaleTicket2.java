package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket2 {
    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newSingleThreadExecutor();
        Thread3 thread3=new Thread3();
        try {
            for (int i = 0; i <30 ; i++) {

                threadPool.execute(()->thread3.sale());
            }
//            threadPool.execute(()->thread3.sale());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
class Thread3{
     private int number=30;
     private Lock lock=new ReentrantLock();
     public  void sale(){
         lock.lock();
         try {
             if(number>0){
                 System.out.println(Thread.currentThread().getName()+"还剩"+--number+"张票");
             }else {
                 System.out.println("票的数量不足");
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }

     }
}