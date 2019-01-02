package Hash;

import javafx.beans.binding.ObjectBinding;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args){
        Mycollection<String> mc = new Mycollection<String>();
        mc.set("gaoqi",0);
        System.out.println(mc.get(0));
        List list = new ArrayList();
    }



}

class Mycollection<E>{
    Object[] objs=new Object[5];

   public void set(E e,int index){
       objs[index]=e;
   }
   public Object get(int index){
       return objs[index];
   }
}