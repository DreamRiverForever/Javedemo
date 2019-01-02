package IO;

import java.io.*;

public class Digui3 {
    public static void main(String[] args) throws IOException{
        File src = Digui.getDir();
        File dist = Digui.getDir();
        if (src.equals(dist)){
            System.out.println("目标文件夹是源文件夹的子文件夹");
        }else {
            copy(src,dist);
        }


    }

    private static void copy(File src, File dist) throws IOException{
        File newDir = new File(dist,src.getName());//dist下创建src文件夹
        newDir.mkdir();
        File[] subs = src.listFiles();
        for (File sub: subs)
        {
            if (sub.isFile())
            {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sub));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newDir,sub.getName())));
                int b;
                while ((b=bis.read())!=-1)
                {
                    bos.write(b);
                }
                bis.close();
                bos.close();
            }else {
                copy(sub,newDir);
            }
        }
    }
}
