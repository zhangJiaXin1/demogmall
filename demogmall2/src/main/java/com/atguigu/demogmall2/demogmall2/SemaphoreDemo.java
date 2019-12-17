package com.atguigu.demogmall2.demogmall2;

import org.elasticsearch.action.support.ThreadedActionListener;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //抢车位，有三个车位，当有车位离开的时候，其他的车位接到通知去停车
        Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<6;i++){
            new Thread(()->{
              boolean flag=false;
                try {
                    semaphore.acquire();
                    //抢到车位后，将状态转成true
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    //睡眠三秒后离开车位
                    TimeUnit.SECONDS.sleep(3);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }

    }
}
