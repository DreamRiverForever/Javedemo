package IO;

import java.io.*;

public class Demo5 {
    public static void main(String[] args)throws IOException {
        FileInputStream fis = new FileInputStream("zzz.txt");
        FileOutputStream fos = new FileOutputStream("zz.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);//带缓冲区的输入流
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int b;
        while ((b=bis.read())!=-1){
            bos.write(b);
        }
        bis.close();//flush也具备及时刷新功能，flush刷完还可以写，close关闭流。
        bos.close();//具备刷新功能，关闭流之前刷新一次缓冲区，将缓冲区中的文件刷新到文件中。有可能缓冲区没有装满，没能传到文件中close可以强制把缓冲区中字节输出

    }
}
