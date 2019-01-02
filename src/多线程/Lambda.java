package 多线程;

public class Lambda {
    public static void main(String[] args) {
         Thread t = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" lambda");
        });
         t.start();
         Thread.State state = t.getState();
        System.out.println(state);

    }
}
