package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    //Callable的运行要用到中间类
    public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask futureTask=new FutureTask(new Test4());
       new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
   /* FutureTask futureTask=new FutureTask(()->{
            System.out.println("nifa");
        },null );*/
//        futureTask.run();
    }

}
class Test4 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("我是Callable ");
        return 1;
    }
}