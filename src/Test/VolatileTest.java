package Test;

public class VolatileTest {//用于保证数据同步，但不是原子性
    private volatile static int num = 0;
    public static void main(String[] args) {
        new Thread(()->{
            while (num==0){

            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;

    }
}
