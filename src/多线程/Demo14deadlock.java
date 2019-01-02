package 多线程;

public class Demo14deadlock {
    private static String s1="筷子左";
    private static String s2="筷子右";

    public static void main(String[] args) {//死锁避免同步代码块嵌套
        new Thread(){
            @Override
            public void run() {

                    while (true){
                        synchronized (s1){
                        System.out.println(getName()+" 获取 "+s1+" 等待 "+s2);//获取不到s2
                        synchronized (s2){
                            System.out.println(getName()+" 拿到 "+s2+"开吃");
                        }

                        }
                    }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {

                while (true){
                    synchronized (s2){
                        System.out.println(getName()+" 获取 "+s2+" 等待 "+s1);
                        synchronized (s1){//获取不到s1
                            System.out.println(getName()+" 拿到 "+s1+"开吃");
                        }

                    }
                }
            }
        }.start();

    }
}

