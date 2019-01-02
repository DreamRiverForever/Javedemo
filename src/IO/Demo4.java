package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("xxx.txt");
        FileOutputStream fos = new FileOutputStream("zzz.txt");
        byte[] arr = new  byte[3];//建议1024*n，就是1024的整数倍
        int len;
        while ((len=fis.read(arr))!=-1){//如果不写arr返回的是AS2码值，len长度变了，write写入len个空字符。
            fos.write(arr,0,len);//取消len后面的字符，防止重复
        }
        fis.close();
        fos.close();



    }
}
