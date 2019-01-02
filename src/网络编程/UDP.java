package 网络编程;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP {//send
    public static void main(String[] args) throws Exception{
        String str ;
        Scanner in = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();//创建socket
        while (true){
            str = in.nextLine();
            if ("quit".equals(str)){
                break;
            }
            DatagramPacket packet = new DatagramPacket(str.getBytes(),str.getBytes().length, InetAddress.getByName("127.0.0.1"),5555);//创建packet,可以使用while循环一直发
            socket.send(packet);//发送
        }

        socket.close();//关闭


    }
}
