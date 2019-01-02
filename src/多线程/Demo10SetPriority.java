package 多线程;

public class Demo10SetPriority {//设置进程优先级

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    System.out.println(getName()+"   aaa");
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    System.out.println(getName()+"   bbb");
                }
            }
        };
        t1.setPriority(10);//优先级10最大，1最小
        t2.setPriority(1);
        t1.start();
        t2.start();

    }
}
