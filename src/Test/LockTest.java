package Test;

public class LockTest {//可重入锁
    public void test(){
        synchronized (this){
            while (true){
                synchronized (this){
                    System.out.println("重入锁");//上面的锁对象往下传递
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        new LockTest().test();

    }
}
