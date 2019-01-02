package IO;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Demo14 {
    public static void main(String[] args) throws IOException{
        LineNumberReader lnr = new LineNumberReader(new FileReader("zzz.txt"));
        String line;
        //lnr.setLineNumber(100);行号初始值得设定
        while ((line=lnr.readLine())!=null){
            System.out.println(lnr.getLineNumber()+" "+line);//加行号
        }

    }
}
