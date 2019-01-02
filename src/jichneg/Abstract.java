package jichneg;

public abstract class Abstract {

    abstract public void shou();//必须在子类中实现该方法。给子类提供规范
    public void run(){
        System.out.println("run forever");
    }
}

class obj extends Abstract{
    public void shou(){//实现父类中的抽象方法

    }

}
