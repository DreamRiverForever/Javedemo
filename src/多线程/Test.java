package 多线程;

public class Test {
    public static void main(String[] args) {
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();



    }
}

class Ticket extends Thread{
    private static int ticket = 100;//静态成员共享

    @Override
    public void run() {
        while (true){
            synchronized (Ticket.class){//同步代码块。参数必须是同一把锁，引用对象必须是静态的
                if (ticket==0)
                    break;
                try {
                    Thread.sleep(10);//线程1234等于1时，每次减一变成负数跳过ticket==0判断买负数票
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+" 这是第 "+ticket--+" 号票");
            }
        }
    }
}