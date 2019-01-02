package Array;

import java.util.Arrays;

public class Test02 {
    public static void main(String[] args){
        int ary[]={1,2,3,4,7};
        System.out.println(Arrays.toString(ary));
        Arrays.sort(ary);
        for (int temp:ary
             ) {
            System.out.println(temp);
        }
        System.out.println(Arrays.binarySearch(ary,7));
    }
}
