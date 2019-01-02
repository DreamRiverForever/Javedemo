package 多线程;

public class Demo2 {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread t =new Thread(mr);//传入Runnable子类对象
        t.start();//开启线程
        for (int i=0;i<100;i++)
            System.out.println("bb");
    }
}

class MyRunnable implements Runnable{//实现Runnable接口
    @Override
    public void run() {//重写run方法
        for (int i=0;i<100;i++)
            System.out.println(Thread.currentThread().getName()+i);
    }
}