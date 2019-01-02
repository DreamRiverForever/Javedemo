package 网络编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("服务器启动绑定12345端口号");
        while (true){
            final Socket socket = serverSocket.accept();
            new Thread(){
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        String line = br.readLine();
                        File dir = new File("update");
                        dir.mkdir();
                        File file = new File(dir,line);//父级目录dir
                        if (file.exists()){
                            ps.println("文件存在，不用上传");
                            socket.close();
                            return;
                        }else {
                            ps.println("不存在，可以上传");
                        }
                        FileOutputStream fos = new FileOutputStream(file);//file文件创建好了
                        byte[] arr = new byte[8192];
                        int len;
                        while ((len=is.read(arr))!=-1)
                        {
                            fos.write(arr,0,len);//不可以用println会把字节转化为字符
                        }
                        fos.close();
                        socket.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }

    }
}
