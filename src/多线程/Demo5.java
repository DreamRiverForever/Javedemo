package 多线程;

public class Demo5 {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(getName()+"    aaa");//获取线程名称
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"    bbb");//Thread.currentThread()获取当前执行线程，因为Runnable没有getname方法
            }
        }).start();
    }
}
