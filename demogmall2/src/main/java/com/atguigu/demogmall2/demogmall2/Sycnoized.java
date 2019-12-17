package com.atguigu.demogmall2.demogmall2;

import java.util.concurrent.TimeUnit;

public class Sycnoized {
    //普通方法锁的是当前的this对象,静态方法锁的是类模板Phone class
    //1.一个phone对象，普通同步方法是先发邮件还是消息   邮件,理解：属于同一个this，会出现排斥
    //2.在发邮件线程无关中添加等待三秒，先发邮件还是消息   邮件，理解： 两个线程调用同一个资源，会发生排斥，与等待
    //3.在资源类中添加一个方法，是先发邮件还是新增的方法  新增的方法 理解：
    //4.两个phone对象，通同步方法是先发邮件还是消息   信息，理解：因为两个线程的this不同，所以不会出现排斥
    //5.两个静态同步方法，一步手机，是先邮件还是短信  邮件，理解：属于同一个模板类
    //6.两个静态同步方法，两部手机，是先邮件还是短信  邮件，理解：属于一个模板，当模板被锁的时候，其他的就没办法进入
    //7.一个静态方法，一个同步方法，一部手机，  信息，理解：锁的不是同一个对象
    //8.一个静态方法，一个同步方法，两部手机  信息  理解：锁的不是同一个对象
    public static void main(String[] args) throws Exception {
        Phone phone=new Phone();
        Phone phone2=new Phone();
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   phone.sendEmail();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
       Thread.sleep(100);//此处睡眠
        new Thread(new Runnable() {
            @Override
            public void run() {
//                phone.sendSmg();
                phone2.sendSmg();
            }
        }).start();
    }

}
class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("-----sendEmail");
    }
    public   synchronized void sendSmg(){
        System.out.println("-----sendSmg");
    }
}