package WebServer2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *使用Serversocket启动tcp链接
 */
public class Server01 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server01 server01 = new Server01();
        server01.start();

    }
    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(8888);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //接受连接
    public void receive() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("一个客户端建立了连接");
        InputStream is= socket.getInputStream();
        byte[] bytes = new byte[1024*1024];
        int len = is.read(bytes);
        String requestInfo = new String(bytes,0,len);
        System.out.println(requestInfo);

    }
    //停止服务
    public void stop(){

    }
}
