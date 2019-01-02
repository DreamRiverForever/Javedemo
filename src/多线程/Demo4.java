package 多线程;

public class Demo4 {
    public static void main(String[] args) {
        new Thread(){//可以传入字符串命名
            @Override
            public void run() {
                this.setName("dengyuwen");//也可以setname方法设置名字
                System.out.println(this.getName()+"   aaa");//获取线程的name
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                this.setName("hejianan");
                System.out.println(this.getName()+"   bbb");
            }
        }.start();
    }
}
