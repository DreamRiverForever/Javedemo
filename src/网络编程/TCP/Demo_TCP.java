package 网络编程.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Demo_TCP {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",12345);
        InputStream is = socket.getInputStream();//获取客户端输入流
        OutputStream os = socket.getOutputStream();//获取客户端输出流
        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0,len));
        os.write("你好".getBytes());
        socket.close();
    }
}
