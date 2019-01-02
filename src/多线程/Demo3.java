package 多线程;

public class Demo3 {
    public static void main(String[] args) {//匿名内部类
        new Thread(){//继承Threadlei
            @Override
            public void run() {//重写run方法
                for (int i=0;i<100;i++)
                    System.out.println("aaaaa");
            }
        }.start();
        new Thread(new Runnable() {//将runnable子类对象放入Thread构造函数
            @Override
            public void run() {
                for (int i=0;i<100;i++)
                    System.out.println("bbb");
            }
        }).start();
    }
}
