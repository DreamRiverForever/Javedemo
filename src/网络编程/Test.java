package 网络编程;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = getFile();
        Socket socket = new Socket("127.0.0.1",12345);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(file.getName());
        String flag = br.readLine();
        if ("文件存在，不用上传".equals(flag)){
            System.out.println(flag);
            socket.close();
            return;
        }else {
            System.out.println(flag);
        }
        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[8192];
        int len;
        while ((len=fis.read(arr))!=-1){
            ps.write(arr,0,len);//不可以用println会把字节转化为字符
        }
        fis.close();
        socket.close();
    }

    private static File getFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入文件路径");
        while (true){
            File file = new File(in.nextLine());
            if (!file.exists()){
                System.out.println("输入的文件路径不存在，重新录入");
            }else if (file.isDirectory()){
                System.out.println("文件夹路径，请输入文件路径");
            }else{
                return file;
            }
        }
    }
}
