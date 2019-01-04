package Test;

public class LockTest3 {//可重入锁,可以延续使用，里面有计数器
    ReLock lock = new ReLock();
    public void a(){
        lock.lock();
        System.out.println(lock.getCount());
        doSomething();
        lock.unLock();
        System.out.println(lock.getCount());
    }
    public void b(){

    }
    public void doSomething(){
        lock.lock();
        System.out.println(lock.getCount());
        System.out.println("可重入锁");
        lock.unLock();
        System.out.println(lock.getCount());
    }
    public static void main(String[] args) {
        LockTest3 lockTest3 = new LockTest3();
        lockTest3.a();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lockTest3.lock.getCount());
    }
}

class ReLock{
    private boolean isLock = false;
    private Thread lockBy = null;//存储线程
    private int count = 0;

    public int getCount() {
        return count;
    }

    public synchronized void lock(){
        Thread t = Thread.currentThread();
        while (isLock && lockBy!=t){//锁被获取了就等待锁释放，如果是当前线程等于存储线程就不等待了
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
        lockBy = t;
        count++;
    }
    public synchronized void unLock(){
        if (Thread.currentThread() == lockBy){
            count--;
            if (count == 0){
                isLock = false;//用完就释放锁对象
                notify();
                lockBy = null;
            }
        }
    }
}
