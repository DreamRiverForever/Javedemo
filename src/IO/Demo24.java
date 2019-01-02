package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Demo24 {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();//用于读取配置文件
        prop.load(new FileInputStream("com.properties"));
        prop.setProperty("tel","18952122212");
        prop.store(new FileOutputStream("com.properties"),"jkv");//第二个参数用来描述文件列表的
        System.out.println(prop);
    }

}
