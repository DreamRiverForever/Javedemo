package 网络编程;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receive {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(5555);//创建SOCKET
        while (true){
            DatagramPacket packet = new DatagramPacket(new byte[1024],1024);//创建packet
            socket.receive(packet);
            byte[] arr = packet.getData();
            int len = packet.getLength();
            String ip = packet.getAddress().getHostAddress();//获取ip地址
            int  port = packet.getPort();
            System.out.println(ip+":"+port+":"+new String(arr,0,len));
        }
    }
}
