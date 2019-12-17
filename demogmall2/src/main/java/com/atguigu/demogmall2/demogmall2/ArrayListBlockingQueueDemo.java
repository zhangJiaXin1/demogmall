package com.atguigu.demogmall2.demogmall2;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayListBlockingQueueDemo {
    //阻塞队列
    //add remove element  抛出异常组,只要超过线程的容量就会报错
    //offer poll peek    特殊值  只要超过线程的容量会打印false
    //put take      阻塞组  一致阻塞
    //offer(元素，时间) poll(元素，时间)
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println( arrayBlockingQueue.offer("a",3L, TimeUnit.SECONDS));
        System.out.println( arrayBlockingQueue.offer("b",3L, TimeUnit.SECONDS));
        System.out.println( arrayBlockingQueue.offer("c",3L, TimeUnit.SECONDS));
        System.out.println( arrayBlockingQueue.offer("d",3L, TimeUnit.SECONDS));
//        System.out.println( arrayBlockingQueue.add("b"));
//        System.out.println( arrayBlockingQueue.add("c"));
//        arrayBlockingQueue.add("d");
      /*  System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());*/
    }
}
