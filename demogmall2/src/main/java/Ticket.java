import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
    //企业级的线程的使用，运用到java8的新特性
    private int number = 30;
    private Lock lock=new ReentrantLock();

    public  void sale() {
        //买票功能,加入lock锁,要在lock上面加入锁
        lock.lock();
        try{
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "还剩下" + number--);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }
}
class Ceshi{
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
//        new Thread(new Runnable() {
//            @Override

//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    ticket.sale();
//                }
//            }
//        },"窗口一").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    ticket.sale();
//                }
//            }
//        },"窗口二").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 35; i++) {
//                    ticket.sale();
//                }
//            }
//        },"窗口三").start();abcdefghijklmnopqrstuvwxyz
        new Thread(()->{for (int i = 0; i < 35; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 35; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 35; i++) ticket.sale();},"C").start();
    }
}
class Te{
    private int name;
}
