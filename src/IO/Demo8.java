package IO;

import java.io.*;

public class Demo8 {
    public static void main(String[] args)throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("xxxx.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("xxxxx.txt"));
        int b;
        while ((b=bis.read())!=-1){
            bos.write(b ^ 123);//异或一个数，这个数就是秘钥，解密再次异或就行
        }
        bis.close();
        bos.close();
    }
}
