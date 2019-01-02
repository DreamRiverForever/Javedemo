package IO;

import java.io.File;

public class Didui4 {
    public static void main(String[] args) {
        File dir = Digui.getDir();//获取文件夹路径
        printLev(dir,0);

    }

    private static void printLev(File dir,int lev) {
        File[] subs = dir.listFiles();
        for (File sub:subs) {
            for (int i=0;i<lev;i++){
                System.out.print("\t");
            }
            System.out.println(sub.getName());
            if (sub.isDirectory()){
                printLev(sub,lev+1);
            }
        }

    }
}
