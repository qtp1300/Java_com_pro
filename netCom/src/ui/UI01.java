/*下一版改进计划接收发送区合并，最下面加一个状态栏*/
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.sound.sampled.Port;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import rxtx001.SerialPortUtilityNew;

public class UI01 {
	static boolean tempflag = false;
	static int high = 500;
	static int weight = 600;
	static SerialPortUtilityNew porr = new SerialPortUtilityNew();
	public static void main(String[] args) {
		UI01();
		
		while(true)
		{
			System.out.println("ccc");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			porr.readComm();
			}
		
	}
	
	public static void UI01() {
		// TODO Auto-generated method stub
		/*初始化控件*/
		JFrame window = new JFrame("串口助手");				//窗口
		/*接收区*/
		JPanel reci = new JPanel();					//接收显示部分	
		JPanel recil = new JPanel();
		JTextArea textreci = new JTextArea();		//上方文本接收区域
		JButton cleanreci = new JButton("清除接收区");
		/*发送区*/
		JPanel sen = new JPanel();					//发送显示部分
		JPanel senl = new JPanel();
		JTextArea textsend = new JTextArea();		//上方文本显示区域
		JButton cleansend = new JButton("清除发送区");
		JButton send = new JButton("发送");
		/*控制区*/			
		JPanel control = new JPanel();				//控制部分	
		JPanel set = new JPanel(new FlowLayout());
		JPanel kg = new JPanel();
		JToggleButton onoff_SP = new JToggleButton("打开串口");
		JLabel com_text = new JLabel("端口");
		JComboBox<String> com_num = new JComboBox<String>();
		JLabel speed_text = new JLabel("速率");
		JComboBox<String> speed = new JComboBox<String>();
		JLabel crc_text = new JLabel("校验位");
		JComboBox<String> crc = new JComboBox<String>();
		JLabel stop_text = new JLabel("停止位");
		JComboBox<String> stop = new JComboBox<String>();		
		
		/*接收区*/
		reci.setPreferredSize(new Dimension(weight,high/3));		//设定接收区大小
		recil.add(cleanreci,BorderLayout.WEST);										//将 发送左 部分添加进发送区容器
		cleanreci.setPreferredSize(new Dimension(150,high/4));
		textreci.setPreferredSize(new Dimension(weight-200,high/4));
		reci.add(recil,BorderLayout.WEST);
		reci.add(textreci,BorderLayout.EAST);
		
		
		
		/*发送区*/
		senl.setPreferredSize(new Dimension(150,high/4));		//设定发送区大小
		senl.setLayout(new FlowLayout());
		senl.add(cleansend);
		senl.add(send);	
		cleansend.setPreferredSize(new Dimension(150,high/9));
		send.setPreferredSize(new Dimension(150,high/9));	
		textsend.setPreferredSize(new Dimension(weight-200,high/4));
		sen.add(senl,BorderLayout.WEST);
		sen.add(textsend,BorderLayout.EAST);
		
		
		
		/*控制区*/
		control.add(set,BorderLayout.NORTH);
		control.add(kg,BorderLayout.SOUTH);
		kg.add(onoff_SP);
		com_num.setPreferredSize(new Dimension(100,30));
		for(int i= 0;i< 256;i++)
		{
			com_num.addItem("COM"+i);
		}
		speed.addItem("2400");
		speed.addItem("3600");
		speed.addItem("9600");
		crc.addItem("无校验");
		crc.addItem("奇校验");
		crc.addItem("偶校验");
		crc.addItem("0校验");
		crc.addItem("1校验");
		stop.addItem("1位");
		stop.addItem("1.5位");
		stop.addItem("2位");
		set.add(com_text);
		set.add(com_num);
		set.add(speed_text);
		set.add(speed);
		set.add(crc_text);
		set.add(crc);
		set.add(stop_text);
		set.add(stop);
		control.setPreferredSize(new Dimension(weight,high/4));
		

		window.setBounds(0, 0, weight, high);		//总窗口大小		
		window.setLocation(100, 100);				//窗口出现位置	
		window.add(reci,BorderLayout.NORTH);		//接收区
		window.add(sen,BorderLayout.CENTER);		//发送区
		window.add(control,BorderLayout.SOUTH);		//控制区
		
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		
		/*
		 * 监听*/
		class Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abs = (AbstractButton)e.getSource();
				if(abs.isSelected())
				{
					System.out.println("尝试打开串口"+"通过"+com_num.getSelectedItem()+"端口，以"+speed.getSelectedItem()+"的速率"+crc.getSelectedItem()+"位"+stop.getSelectedItem()+"停止位");
					
					try {		
						
						porr.setcom(com_num.getSelectedItem().toString());
						porr.setspd(Integer.parseInt(speed.getSelectedItem().toString()));												
						porr.init();
						abs.setText("关闭串口");
						tempflag = true;
						porr.readComm();
//						System.out.println(porr.get_str());
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("试图设置串口参数出错，错误原因"+e2);
						abs.setText("打开串口");
						abs.setSelected(false);
						tempflag = false;
					} 				
				}
				else {
					System.out.println("关闭串口");
					abs.setText("打开串口");
				}
			}			
		}
		onoff_SP.addActionListener(new Listener());
		
	}
}

