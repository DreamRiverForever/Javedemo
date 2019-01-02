package jisuanqi;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class jisuanqi extends JFrame implements ActionListener, KeyListener,
        MouseListener {
    final int WIDTH = 440, HEIGHT = 350;
    GridLayout gridLayout = new GridLayout(5, 4, 5, 5); // 初始化 
    BorderLayout borderLayout = new BorderLayout();
    FlowLayout flowLayout = new FlowLayout();
    Container container;
    
    JPanel panel, buttonPanel;
    JButton[] buttons;
    String[] name;
    JTextField textField;                 //定义文本组件
    boolean cleared = true;

    public jisuanqi() {
        super("jisuanqi");
        container = getContentPane();
       
         buttonPanel = new JPanel();
        panel = new JPanel();
       
        buttonPanel.setLayout(gridLayout);
        textField = new JTextField(15);
        textField.setFont(textField.getFont().deriveFont(Font.BOLD,(float) 32.0));
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
        panel.add(textField);
      
        container.setLayout(borderLayout);
      
        container.add(panel, borderLayout.NORTH);
       container.add(buttonPanel, borderLayout.CENTER);
       
       buttons = new JButton[18];
       name = new String[] { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2",
                "3", "-", "0", ".", "=", "+" ,"q","t"};
        for (int index = 0; index <= 17; index++) {
            buttons[index] = new JButton(name[index]);
            buttons[index].addActionListener(this);
            buttons[index].setBackground(Color.GRAY);
            buttons[index].setForeground(Color.WHITE);
            buttons[index].addKeyListener(this);
            buttons[index].addMouseListener(this);
            buttons[index].setFont(buttons[index].getFont().deriveFont(
                    Font.BOLD, (float) 32.0));               //字体
            
            buttonPanel.add(buttons[index]);
        }
        this.addKeyListener(this);
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
       Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        setLocation(screenWidth / 2 - WIDTH / 2, screenHeight / 2 - HEIGHT / 2);// 设置窗口居中显示
        
        setVisible(true);
        setFocusable(true);
    }

    String calculate(String str) {                      //计算函数
        String result = "Wrong Expression";
        String temp = "";
        if (str.charAt(0) != '-'                    //检验操作数是否合法
               && !(str.charAt(0) <= '9' && str.charAt(0) >= '0')) {
           return result;
        }
        LinkedList<Double> list = new LinkedList<Double>();             //存储数字的栈
        LinkedList<Character> optList = new LinkedList<Character>();    //存储符号的栈
        Double doubleTemp;
        boolean isFormerOpt = true;
        for (int index = 0; index <= str.length() - 1; index++) {
            if (index == 0) {
                isFormerOpt = true;
            } else {
                if (str.charAt(index - 1) > '9' || str.charAt(index - 1) < '0') {
                    isFormerOpt = true;
                } else {
                    isFormerOpt = false;
                }
            }
            if (str.charAt(index) != '+' && str.charAt(index) != '*'
                    && str.charAt(index) != '/'
                    && (!(str.charAt(index) == '-' && isFormerOpt == false))) {
                temp += str.charAt(index);
            } else {
                doubleTemp = new Double(temp);
                list.add(doubleTemp);                        //将数字存入栈
                temp = "";
                optList.add(str.charAt(index));              //将符号存入栈
            }
        }
        doubleTemp = new Double(temp);
        list.add(doubleTemp);
        temp = "";
        boolean isThereHigherOpt = true;
        while (isThereHigherOpt == true) {                      //乘除优先
               isThereHigherOpt = false;
            for (int index = 0; index <= optList.size() - 1; index++) {
               if (optList.get(index) == '*') {
                   Double t = list.get(index) * list.get(index + 1);
                    list.remove(index + 1);
                    list.set(index, t);
                    optList.remove(index);
                    isThereHigherOpt = true;
                    break;
                }
                if (optList.get(index) == '/') {
                    Double t = list.get(index) / list.get(index + 1);
                    list.remove(index + 1);
                    list.set(index, t);
                    optList.remove(index);
                    isThereHigherOpt = true;
                    break;
               }
        }
            }
       while (optList.isEmpty() == false) {                               //计算加减
            for (int index = 0; index <= optList.size() - 1; index++) {
                if (optList.get(index) == '+') {
                    Double t = list.get(index) + list.get(index + 1);
                    list.remove(index + 1);
                    list.set(index, t);
                    optList.remove(index);
                    break;
                }
                if (optList.get(index) == '-') {
                    Double t = list.get(index) + 0.0 - list.get(index + 1);
                    list.remove(index + 1);
                    list.set(index, t);
                    optList.remove(index);
                    break;
                }
                }
       }
  
        if (list.size() == 1) {
            result = list.get(0).toString();
        }
       return result;
    }

    void addText(char ch) {                               //输入操作符
        if (cleared == true && ((ch <= '9' && ch >= '0'))) {
            textField.setText("");
            cleared = false;
        }
        String str = textField.getText();
        
        if(ch=='q') {                                //清除
       	 //System.out.println("1");
       	 textField.setText("");}
        if(ch=='t')                                 //退格
        {   str=str.substring(0,str.length()-1);
       	 textField.setText(str);}
        if(ch!='q'&&ch!='t') {
        if (ch != '=') {                             //不是等号，继续输入，并检验是否合法
            if (str.length() > 0) {
                if (str.charAt(str.length() - 1) <= '9'
                        && str.charAt(str.length() - 1) >= '0') {
                    if (ch != '.') {
                   	 
                        textField.setText(str + ch);
                    } else {
                        boolean isTherePoint = false;
                        int i = str.length() - 1;
                        while (i >= 0) {
                            if (str.charAt(i) == '*' || str.charAt(i) == '/'
                                    || str.charAt(i) == '+'
                                    || str.charAt(i) == '-') {
                                break;
                            }
                            if (str.charAt(i) == '.') {
                                isTherePoint = true;
                                break;
                            }
                            i--;
                        }
                        if (isTherePoint == false) {
                       	 
                            textField.setText(str + ch);
                        }
                    }
                } else {
                    if ((ch <= '9' && ch >= '0') || ch == '-') {
                   	
                        textField.setText(str + ch);
                    }
                }
            } else {
                if (ch == '-' || (ch <= '9' && ch >= '0'))
               	 
                    textField.setText(str + ch);
            }
            cleared = false;
        } else {                                            //输入等号计算结果
            if (cleared == true) {
                textField.setText("");
            } else {
                str = textField.getText();
              
                textField.setText("");
                if (str.length() > 0) {
                    if (str.charAt(str.length() - 1) <= '9'
                            && str.charAt(str.length() - 1) >= '0') {
                        textField.setText(calculate(str));
                    } else {
                        textField.setText("Wrong Expression");
                    }
                }
            }
            cleared = true;
        }}
    }

    public void actionPerformed(ActionEvent event) {             //按钮事件侦听
        Object source = event.getSource();
        if (source.getClass() == JButton.class) {
            JButton button = (JButton) source;
            char ch = button.getText().charAt(0);
            addText(ch);
        }
    }

    public void keyPressed(KeyEvent e) {            //按下
           
    
    }

    public void keyReleased(KeyEvent e) {            //弹起
    }

    public void keyTyped(KeyEvent e) {              //按键敲击，输入操作符，颜色变化
        char ch = e.getKeyChar();
        if (ch == ' ') {
            System.exit(EXIT_ON_CLOSE);
        }
        if (ch == KeyEvent.VK_ENTER) {
            buttons[14].setBackground(Color.LIGHT_GRAY);
            for (int i = 0; i <= name.length - 1; i++) {
                if (i != 14) {
                    buttons[i].setBackground(Color.GRAY);
                }
            }
            addText('=');
            return;
        }
        for (int index = 0; index <= name.length - 1; index++) {
            if (ch == name[index].charAt(0)) {
                
                buttons[index].setBackground(Color.LIGHT_GRAY);
                for (int i = 0; i <= name.length - 1; i++) {
                    if (i != index) {
                        buttons[i].setBackground(Color.GRAY);
                    }
                }
                addText(ch);
                break;
            }
        }
    }

    public void mouseClicked(MouseEvent event) {                //鼠标点击
    }

    public void mouseEntered(MouseEvent event) {               //鼠标进入组件，颜色变化
        Object source = event.getSource();
        if (source.getClass() == JButton.class) {
            JButton button = (JButton) source;
         
            button.setBackground(Color.red);
        }
    }

    public void mousePressed(MouseEvent event) {
   	
        
    }

    public void mouseReleased(MouseEvent event) {                     //鼠标按下释放颜色变化
   	 Object source = event.getSource();
        if (source.getClass() == JButton.class) {
            JButton button = (JButton) source;
             
            button.setBackground(Color.yellow);
        }
    }

    public void mouseExited(MouseEvent event) {               //离开组件，颜色变化
        Object source = event.getSource();
        if (source.getClass() == JButton.class) {
            JButton button = (JButton) source;
          
            button.setBackground(Color.gray);
        }
    }
    
    
    

    public static void main(String[] args) {
        jisuanqi c = new jisuanqi();
        
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}