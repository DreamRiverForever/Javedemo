package 多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo20_ReentrantLock {
    public static void main(String[] args) {
        final Printer5 p = new Printer5();
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
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    p.print3();
                }
            }
        }.start();

    }
}


class Printer5{
    private ReentrantLock r = new ReentrantLock();
    private Condition c  =  r.newCondition();
    private Condition c1 =  r.newCondition();
    private Condition c2 =  r.newCondition();
    private int flag=1;
    public void print1(){
        r.lock();
            if (flag!=1){
                try {
                    c.await();
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
            c1.signal();
        r.unlock();
    }
    public void print2(){
        r.lock();
            if (flag!=2){
                try {
                    c1.await();
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
            c2.signal();
        r.unlock();
    }
    public void print3(){
        r.lock();
            if (flag!=3){
                try {
                    c2.await();
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
            c.signal();
        r.unlock();
    }
}