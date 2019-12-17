import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Current {
    public static void main(String[] args) {
        Test t1 = new Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {

                        t1.add();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {

                        t1.add();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
       new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {

                        t1.decre();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 30; i++) {

                        t1.decre();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

//两个线程的调度， 一个线程增加1，一个线程减少1
//线程操作资源类
//拷贝小括号，写死右箭头，落地大括号
//判断 执行  通知
//用if去判断不能解决多线程共同调用需要用while去判断，因为while会从新将等待的线程拿出来从新判断，if只会将他,属于虚假唤醒
//存放到wait方法中，不会将他们拿出从新判断
//结论：当设计到多个线程之间存在联系的时候，涉及到生产者消费者的时候，需要用到while去判断，不能用if，
class Test {
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    private int number = 0;
/*
        public synchronized void add() throws InterruptedException {
            while(number!=0){
                this.wait();
            }
            System.out.println(++number);
            this.notify();
        }
        public synchronized void decre() throws InterruptedException {
            while(number==0){
                this.wait();
            }
            System.out.println(--number);
            this.notify();
        }*/
    //lock的实现,用到自己的condition
    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            System.out.println(++number);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decre() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
               condition.await();
            }
            System.out.println(--number);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           lock.unlock();
        }
    }
}