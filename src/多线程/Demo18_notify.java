package 多线程;

public class Demo18_notify {//等待唤醒机制
    public static void main(String[] args) {
       final Printer3 p= new Printer3();
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    p.print1();

                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    p.print2();

                }
            }
        }.start();



    }
}
class Printer3{
    private int flag=1;
    public void print1(){
        synchronized (this){
            if (flag!=1){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("黑");
            System.out.print("马");
            System.out.print("程");
            System.out.print("序");
            System.out.print("员");
            System.out.println();
            flag = 2;
            this.notify();
        }
    }
    public void print2(){
        synchronized (this){
            if (flag!=2){
                try {
                    this.wait();//进入等待线程队列
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("传");
            System.out.print("智");
            System.out.print("播");
            System.out.print("客");
            System.out.println();
            flag = 1;
            this.notify();//随机唤醒一个线程
        }
    }
}
