package 网络编程.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_Server {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(12345);
        while (true){
            Socket socket = serverSocket.accept();//接受客户端的请求
            new Thread(){
                @Override
                public void run() {
                    try{
                        InputStream is = socket.getInputStream();//获取服务器输入流
                        OutputStream os = socket.getOutputStream();//获取服务器输出流
                        os.write("百度一下".getBytes());
                        byte[] arr = new byte[1024];
                        int len = is.read(arr);
                        System.out.println(new String(arr,0,len));
                        socket.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }.start();

        }

    }
}
