package Test;

public class ThreadLocalTest {
    /*
     *ThreadLocal是每个线程存储本地局部的区域,里面的线程互不影响
     * get/set/initialValue
     */
    //private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();//初始化值为null
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>(){//更改初始化的值
        @Override
        protected Integer initialValue() {
            return 200;
        }
    };
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
        threadLocal.set(33);
        System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
    }
}
