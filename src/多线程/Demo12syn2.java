package 多线程;

public class Demo12syn2 {
    public static void main(String[] args) {
        final Printer2 p =  new Printer2();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    p.print1();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    p.print2();
                }
            }
        }.start();

    }
}
class Printer2{
    Demo d = new Demo();
    //非静态同步方法的锁对象是this
    //静态同步方法锁对象时字节文件.class
    public synchronized void print1(){
            System.out.print("黑");
            System.out.print("马");
            System.out.print("程");
            System.out.print("序");
            System.out.print("员");
            System.out.println();
    }
    public void print2(){
        synchronized (this){
            System.out.print("传");
            System.out.print("智");
            System.out.print("播");
            System.out.print("客");
            System.out.println();
        }
    }
}

