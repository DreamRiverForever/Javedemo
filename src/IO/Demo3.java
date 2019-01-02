package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("yyy.txt");
        FileOutputStream fos = new FileOutputStream("copy.txt");
        int lenght=fis.available();
        byte[] bytes = new byte[lenght];//在读文件过程中不能写文件，也有可能导致大文件需要的数组太大，虚拟机不能分配大数组内存
        fis.read(bytes);//文件上字节读入字节数组
        fos.write(bytes);//字节数组中字节读入copy
        fis.close();
        fos.close();
    }
}
