package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    //当数量到达某个数值的时候，调用其他的方法(集齐七个龙珠召唤神龙)
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for(int i=0;i<7;i++){
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+temp+"只神龙");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
