package NodeList;

import java.util.LinkedList;

public class SxtLinkList {
    private Node first;
    private Node last;
    private int size;
    public void add(Object obj){
        Node node = new Node(obj);
        if (first==null)
        {
            node.next=null;
            node.previous=null;
            first=node;
            last=node;
        }else {
            node.previous=last;
            node.next=null;

            last.next=node;
            last=node;
        }
        size++;
    }
    public Object get(int index){
        if (index<0||index>=size){
            throw new RuntimeException("索引数字不合法");
        }
        Node temp=first;
        for (int i=0;i<index;i++)
        {
            temp=temp.next;
        }
        return temp.element;


    }
    public void remove(int index){
        Node temp=first;
        for (int i=0;i<index;i++){
            temp=temp.next;
        }
        Node up=temp.previous;
        Node down=temp.next;
        if (up!=null){
            up.next=down;
        }
        if (down!=null){
            down.previous=up;
        }
        if (index==0){
            first=down;
        }
        if (index==size-1){
            last=up;
        }
        size--;

    }
    public void insert(int index,Object obj){
        Node temp=first;
        for (int i=0;i<index-1;i++){
            temp=temp.next;
        }
        Node  newnode = new Node(obj);
        newnode.next=temp.next;
        temp.next.previous=newnode;
        temp.next=newnode;
        newnode.previous=temp;
        size++;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node temp=first;
        while(temp!=null){
            sb.append(temp.element+",");
            temp=temp.next;
        }
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }

    public static void main(String[] args){
        SxtLinkList list = new SxtLinkList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.remove(4);
        System.out.println(list);
        list.insert(2,"z");
        System.out.println(list);
        list.insert(3,"x");
        System.out.println(list);
    }

}
