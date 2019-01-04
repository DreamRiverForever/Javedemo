package Test;

public class LockTest2 {//不可重入锁
    Lock lock = new Lock();
    public void a(){
        lock.lock();
        doSomething();
        lock.unLock();
    }
    public void b(){

    }
    public void doSomething(){
        lock.lock();
        lock.unLock();
    }
    public static void main(String[] args) {
        LockTest2 lockTest2 = new LockTest2();
        lockTest2.a();
        lockTest2.doSomething();



    }
}

class Lock{
    private boolean isLock = false;
    public synchronized void lock(){
        while (isLock){//锁被获取了就等待锁释放
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
    }
    public synchronized void unLock(){
        isLock = false;//用完就释放锁对象
        notify();
    }
}