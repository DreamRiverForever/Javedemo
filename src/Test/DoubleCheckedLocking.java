package Test;

import javax.xml.crypto.Data;

/*
 *单例设计模式dcl
 */
public class DoubleCheckedLocking {
    private DoubleCheckedLocking(){//私有构造器

    }
    //没有volatile其他线程可能访问到没有初始化的null对象
    private volatile static DoubleCheckedLocking instance;//创建空对象
    public static DoubleCheckedLocking getInstance(){
        //再次检测
        if (instance!=null){
            return instance;//避免不必要的同步
        }
        //如果对象不为空，避免不必要的同步而导致线程排队进入同步块，浪费时间。所以设置上面的再次检测
        synchronized (DoubleCheckedLocking.class){
            if (instance==null){
                instance = new DoubleCheckedLocking();
            }
            return instance;
        }

    }

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println(DoubleCheckedLocking.getInstance());
        });
        t.start();
        System.out.println(DoubleCheckedLocking.getInstance());

    }
}
