package IO;

import java.io.File;
import java.util.Scanner;

public class Demo17{
    public static void main(String[] args) {
        File dir= getDir();
        print(dir);



    }
    public static File getDir(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入文件夹路径");
        while (true){
            String line = in.nextLine();
            File file = new File(line);
            if (!file.exists()){
                System.out.println("文件路径不存在，重新录入");
            }
            else if (file.isFile()){
                System.out.println("文件路径，重新录入文件夹路径");
            }
            else {
                return file;
            }
        }

    }
    public static void print(File file){
        File[] arr = file.listFiles();//file中文件夹全部放入arr
        for (File subfile:arr){
            if (subfile.isFile()&&subfile.getName().endsWith(".java")){
                System.out.println(subfile);
            }else if (subfile.isDirectory()){
                print(subfile);
            }
        }

    }
}
