package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.CountDownLatch;

public class CountDownLacth {

    //CountDownLacth是设计线程的运行完做的操作，要有先后顺序
    public static void main(String[] args) throws InterruptedException {
        //lambda中的变量要用final修饰，多线程有执行的顺序，未执行完就关闭教室，
        //控制前面的线程执行完，在执行下面的
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=0;i<6;i++){
            final int temp=i;
            new Thread(()->{
                System.out.println(temp+"离开教室"+countDownLatch.getCount());
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("关闭教室");
    }
}

