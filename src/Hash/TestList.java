package Hash;

import java.util.ArrayList;
import java.util.Collection;

public class TestList {
    public static void main(String[] args){
        Collection<String> c = new ArrayList<String>();
        System.out.println(c.size());
        System.out.println(c.isEmpty());
        c.add("dengyuwen");
        c.add("deng");

        System.out.println(c.toArray());
        System.out.println(c);//调用toString方法,  contains
        System.out.println(c.size());
        c.remove("deng");
        System.out.println(c);


    }

}
