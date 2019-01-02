package IO;

import java.io.File;
import java.util.Scanner;

public class Digui {
    public static void main(String[] args) {
        File dir = getDir();
        System.out.println(getFilelength(dir));

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
    public static long getFilelength(File dir){
        long len = 0;
        File[] subfiles = dir.listFiles();
        for (File subfile:subfiles) {
            if (subfile.isFile()){
                len+=subfile.length();
            }else {
                len+=getFilelength(subfile);
            }

        }
        return len;
    }
}
