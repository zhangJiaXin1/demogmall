package com.atguigu.demogmall2.demogmall2.com.atguigu.blockingQueue;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //阻塞队列有初始的容量
        BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
        //抛出异常   超过容量Queue full   移除多的java.util.NoSuchElementException
        //特殊值  false，null
        //阻塞
        //超时
      /*  System.out.println(blockingQueue.add("w"));
        System.out.println(blockingQueue.add("s"));
        System.out.println(blockingQueue.add("v"));
//        System.out.println(blockingQueue.add("e"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

      //阻塞
        blockingQueue.put("D");
        blockingQueue.put("D");
        blockingQueue.put("D");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        blockingQueue.put("D");
        /*System.out.println(blockingQueue.offer("e",3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3l, TimeUnit.SECONDS));*/
    }
}
