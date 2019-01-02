package 多线程;

public class Demo6Sleep {
    public static void main(String[] args) {
        for (int i=20;i>=0;i--){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("倒计时第"+i+"秒");
        }
    }
}
