package 多线程;

public class Demo8Join {//加入线程
    public static void main(String[] args) {
        final Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<12;i++)
                    System.out.println(getName()+"   aaaaa");
            }
        };
        final Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i=0;i<12;i++){
                    if (i==2) {
                        try {
                            t1.join();//插队，t2执行两次，t1加入执行到结束t2才能执行。在join中可以传入插队时间
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName()+"   bbbbb");

                }

            }
        };
        t1.start();
        t2.start();
    }
}
