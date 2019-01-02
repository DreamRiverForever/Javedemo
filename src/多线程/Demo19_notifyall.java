package 多线程;

public class Demo19_notifyall {
    public static void main(String[] args) {
        Printer4 p = new Printer4();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    p.print1();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    p.print2();
                }
            }
        }.start();new Thread(){
            @Override
            public void run() {
                while (true){
                    p.print3();
                }
            }
        }.start();


    }
}
/*
 *用什么对象锁，就要用那个对象调用wait方法
 * wait、notify方法必须在object中，object是所有类的基类，锁对象可以使任意的。
 *
 */
class Printer4{
    private int flag=1;
    public void print1(){
        synchronized (this){
            while (flag!=1){
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
            this.notifyAll();
        }
    }
    public void print2(){
        synchronized (this){
            while (flag!=2){
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
            flag = 3;
            this.notifyAll();//随机唤醒所有线程
        }
    }
    public void print3(){
        synchronized (this){
            while (flag!=3){
                try {
                    this.wait();//进入等待线程队列
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("i");
            System.out.print("t");
            System.out.print("h");
            System.out.print("m");
            System.out.println();
            flag = 1;
            this.notifyAll();//随机唤醒所有线程
        }
    }
}
