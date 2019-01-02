package 多线程;

public class Demo7Daemon {//守护线程
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<2;i++)
                    System.out.println(getName()+"   aaaaa");
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<12;i++)
                    System.out.println(getName()+"   bbbbb");
            }
        };
        t2.setDaemon(true);//设置守护线程，当非守护线程死亡守护线程也死亡
        t1.start();
        t2.start();
    }
}
