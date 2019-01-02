package 多线程;

public class TestTread extends Thread{
    @Override
    public void run() {
        for (int i=0; i<20;i++)
        {
            System.out.println("tingge");
        }
    }
    public static void main(String[] args){
        TestTread test = new TestTread();
        test.start();//开始调用run方法
        for (int i=0;i<20;i++){
            System.out.println("xiedaima");
        }
    }
}
