package IO;

import java.io.*;
import java.util.Scanner;

public class Demo9 {
    public static void main(String[] args)throws IOException{
        File file = getfile();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));
        int b;
        while ((b=bis.read())!=-1){
            bos.write(b);
        }
        bis.close();
        bos.close();

    }
    public static File getfile(){
        Scanner in = new Scanner(System.in);
        while (true){
            String line = in.nextLine();
            File file = new File(line);
            if (!file.exists()){
                System.out.println("文件路径不存在，重新录入");
            }
            else if (file.isDirectory()){
                System.out.println("文件夹路径，重新录入");
            }
            else {
                return file;
            }
        }

    }
}
