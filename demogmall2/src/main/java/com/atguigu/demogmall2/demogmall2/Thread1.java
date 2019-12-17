package com.atguigu.demogmall2.demogmall2;

public class Thread1 {
    //线程1是多个线程共用同一个资源
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{for (int i = 0; i < 20; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sale();},"B").start();
    }
}
class Ticket{
   private int number=30;
   public synchronized void sale(){
       if(number>0){
           System.out.println(Thread.currentThread().getName()+"票的数量"+--number);
       }

   }


}