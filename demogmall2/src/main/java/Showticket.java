public class Showticket  {


    public static void main(String[] args) {


//        SlowTicket t1=new SlowTicket("窗口一");
//        t1.start();
//        SlowTicket t2=new SlowTicket("窗口二");
//        t2.start();
//        SlowTicket t3=new SlowTicket("窗口三");
//        t3.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SlowTicket().sale();
            }
        },"").start();
    }
}
    class SlowTicket{
        private static int ticket = 30;
        public void sale() {
            while (ticket > 0) {
                synchronized ("this") {
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName()+"票的数量为" + ticket--);
                    } else {
                        System.out.println("票的数量不足");
                    }
                }
            }
        }
    }
