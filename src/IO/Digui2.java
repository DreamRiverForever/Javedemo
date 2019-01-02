package IO;

import jdk.jfr.FlightRecorder;

import java.io.File;

public class Digui2 {
    /*
     *键盘接受一个文件夹，并且删除
     */
    public static void main(String[] args) {
        File dir = Digui.getDir();
        deleteFile(dir);

    }
    public static void deleteFile(File dir){
        File[] subs = dir.listFiles();
        for (File sub:subs) {
            if (sub.isFile()){
                sub.delete();
            }
            else {
                deleteFile(sub);
            }

        }
        dir.delete();
    }
}
