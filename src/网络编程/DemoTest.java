package 网络编程;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoTest extends Frame {
    TextField tf;
    Button send;
    Button clear;
    Button shake;
    Button log;
    TextArea viewText;
    TextArea sendText;
    DatagramSocket socket;
    BufferedWriter bw;
    public static void main(String[] args) {
        new DemoTest();

    }

    public DemoTest(){
        init();
        southPanel();
        centerPanel();
        event();
    }

    public void event(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                socket.close();
                try {
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        log.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewText.setText("");
            }
        });
        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shake();
            }
        });
        sendText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){//键盘监听
                    try {
                        send();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
    public void shake(){
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        for (int i=0;i<20;i++){
            this.setLocation(x+20,y+20);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setLocation(x+20,y-20);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setLocation(x-20,y+20);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setLocation(x-20,y-20);
        }
    }
    public void logFile() throws IOException{
        try {
            bw.flush();//刷新缓冲区
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream fis = new FileInputStream("config.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//内存中创建缓冲区
        int len;
        byte[] arr = new byte[8192];
        while ((len = fis.read(arr))!=-1){
            baos.write(arr,0,len);
        }
        String str = baos.toString();
        viewText.setText(str);
        fis.close();
    }
    public void send() throws IOException {
        String message = sendText.getText();
        String ip = tf.getText();//获取ip地址
        DatagramPacket packet = new DatagramPacket(message.getBytes(),message.getBytes().length, InetAddress.getByName(ip),9999);
        socket.send(packet);
        String time = getCurrentTime();
        String str = time+" 我对："+ip+"说\r\n"+message+"\r\n\r\n";
        viewText.append(str);
        sendText.setText("");
        bw.write(str);
        bw.flush();

    }

    private String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        return sdf.format(d);//时间格式化
    }

    public void southPanel(){
        Panel south = new Panel();
        tf = new TextField(15);
        tf.setText("127.0.0.1");
        send = new Button("send");//创建发送按钮
        log = new Button("log");
        clear = new Button("clear");
        shake = new Button("shake");
        south.add(tf);
        south.add(send);
        south.add(log);
        south.add(clear);
        south.add(shake);
        this.add(south,BorderLayout.SOUTH);

    }
    public void centerPanel(){
        Panel center = new Panel();
        viewText = new TextArea();
        sendText = new TextArea(5,1);
        center.setLayout(new BorderLayout());
        center.add(sendText,BorderLayout.SOUTH);
        center.add(viewText,BorderLayout.CENTER);
        this.add(center,BorderLayout.CENTER);
        viewText.setEditable(false);
        viewText.setBackground(Color.WHITE);
        sendText.setFont(new Font("xxx",Font.PLAIN,15));
        viewText.setFont(new Font("xxx",Font.PLAIN,15));
    }

    public void init(){
        this.setLocation(500,50);
        this.setSize(400,600);
        this.setVisible(true);
        new Receive().start();
        try {
            socket = new DatagramSocket();
            bw = new BufferedWriter(new FileWriter("config.txt",true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Receive extends Thread{
        @Override
        public void run() {
            try{
                DatagramSocket socket = new DatagramSocket(9999);
                DatagramPacket packet = new DatagramPacket(new byte[8192],8192);
                while (true){
                    socket.receive(packet);
                    byte[] arr = packet.getData();
                    int len = packet.getLength();
                    String message = new String(arr,0,len);
                    String time = getCurrentTime();
                    String ip = packet.getAddress().getHostAddress();
                    String str = time+" "+ip+"对我说：\r\n"+message+"\r\n";
                    viewText.append(str);
                    bw.write(str);
                    bw.flush();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
