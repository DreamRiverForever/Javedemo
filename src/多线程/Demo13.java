package 多线程;

public class Demo13 {
    public static void main(String[] args) {
        MyTicket mt = new MyTicket();//创建一次对象ticket不在需要共用
        new Thread(mt).start();//锁对象可以用this
        new Thread(mt).start();//多次启动一个线程是非法的
        new Thread(mt).start();
        new Thread(mt).start();
    }
}

class MyTicket implements Runnable{
    private int ticket=100;
    @Override
    public void run() {
        while (true){
            synchronized (Ticket.class){//同步代码块。参数必须是同一把锁，引用对象必须是静态的
                if (ticket==0)
                    break;
                try {
                    Thread.sleep(10);//线程1234等于1时，每次减一变成负数跳过ticket==0判断买负数票，所以必须同步ticket--。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 这是第 "+ticket--+" 号票");
            }
        }
    }
}