package 多线程;

public class Demo11syn {
    //同步代码块
    public static void main(String[] args) {
        final Printer p =  new Printer();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    p.print1();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    p.print2();
                }
            }
        }.start();

    }
}
class Printer{
    Demo d = new Demo();
    public void print1(){
        synchronized (d){//同步锁传入同一个对象必须执行完括号里面的代码
            System.out.print("黑");
            System.out.print("马");
            System.out.print("程");
            System.out.print("序");
            System.out.print("员");
            System.out.println();
        }



    }
    public void print2(){
        synchronized (d){//必须同一对象
            System.out.print("传");
            System.out.print("智");
            System.out.print("播");
            System.out.print("客");
            System.out.println();
        }
    }
}
class Demo{

}
