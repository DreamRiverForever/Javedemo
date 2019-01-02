package IO;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Demo21 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("zw.txt");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] arr = new byte[5];//读字符流容易乱码
        int len;
        while ((len=fis.read(arr))!=-1){
            bos.write(arr,0,len);//先读入内存
        }
        System.out.println(bos);//通过转成字符串

    }
}
