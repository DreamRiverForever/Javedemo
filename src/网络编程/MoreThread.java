package 网络编程;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MoreThread {//多线程实现发送和
    public static void main(String[] args) {
        new MyReceive().start();
        new Send().start();

    }
}

class MyReceive extends Thread{
    @Override
    public void run() {
        try{
            DatagramSocket socket = new DatagramSocket(5555);//创建SOCKET
            DatagramPacket packet = new DatagramPacket(new byte[1024],1024);//创建packet
            while (true){
                socket.receive(packet);
                byte[] arr = packet.getData();
                int len = packet.getLength();
                String ip = packet.getAddress().getHostAddress();//获取ip地址
                int  port = packet.getPort();
                System.out.println(ip+":"+port+":"+new String(arr,0,len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
class Send extends Thread{
    @Override
    public void run() {
        try {
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
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}