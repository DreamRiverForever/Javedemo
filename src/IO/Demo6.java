package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo6 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("zw.txt");
        FileOutputStream fos = new FileOutputStream("zw2.txt");
            byte[] arr = new byte[6];
            int len;
            while ((len = fis.read(arr)) != -1) {
                System.out.println(new String(arr,0,len));
            }
            fis.close();
            fos.write("我六级挂了".getBytes());//转化为字节数组
            fos.close();
    }
}
