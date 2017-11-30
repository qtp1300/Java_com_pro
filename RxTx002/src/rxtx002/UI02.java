/*下一版改进计划接收发送区合并，最下面加一个状态栏*/
package rxtx002;

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


import rxtx002.ComUtility;;


public class UI02 {
	static boolean openflag = false;
	static int high = 500;
	static int weight = 600;
//	SerialPortUtilityNew porr = new SerialPortUtilityNew();
//	Com comm = new Com();
	
	/*初始化控件*/
	//
	static JFrame window = new JFrame("串口助手");				//窗口
	/*接收区*/
	static JPanel reci = new JPanel();					//接收显示部分	
	static JPanel recil = new JPanel();
	static JTextArea textreci = new JTextArea();		//上方文本接收区域
	static JButton cleanreci = new JButton("清除接收区");
	/*发送区*/
	static JPanel sen = new JPanel();					//发送显示部分
	static JPanel senl = new JPanel();
	static JTextArea textsend = new JTextArea();		//上方文本显示区域
	static JButton cleansend = new JButton("清除发送区");
	static JButton send = new JButton("发送");
	/*控制区*/			
	static JPanel control = new JPanel();				//控制部分	
	static JPanel set = new JPanel(new FlowLayout());
	static JPanel kg = new JPanel();
	static JToggleButton onoff_SP = new JToggleButton("打开串口");
	static JLabel com_text = new JLabel("端口");
	static JComboBox<String> com_num = new JComboBox<String>();
	static JLabel speed_text = new JLabel("速率");
	static JComboBox<String> speed = new JComboBox<String>();
	static JLabel crc_text = new JLabel("校验位");
	static JComboBox<String> crc = new JComboBox<String>();
	static JLabel stop_text = new JLabel("停止位");
	static JComboBox<String> stop = new JComboBox<String>();
	static AbstractButton abs;				//开关键
	
	void Sign_up() {
		class clean implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "清除接收区":
					textreci.setText("");					
					break;
				case "清除发送区":
					textsend.setText("");
				default:
					break;
				}
				}
			
		}
		class KGListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(Thread.getAllStackTraces());
//				System.out.println(Com.activeCount());
//				System.out.println(Com.getAllStackTraces());
				if (Com.activeCount() == 2) {
					Com comm = new Com();
	//				System.out.println(Thread.getAllStackTraces());
					abs = (AbstractButton)e.getSource();
					if(abs.isSelected())
					{					
						System.out.println("尝试打开串口"+"通过"+com_num.getSelectedItem()+"端口，以"+speed.getSelectedItem()+"的速率"+crc.getSelectedItem()+"位"+stop.getSelectedItem()+"停止位");
						comm.set_comnum(com_num.getSelectedItem().toString());
						comm.set_comspd(Integer.parseInt(speed.getSelectedItem().toString()));
						openflag = true;
						comm.start();						
	//					System.out.println(Com.activeCount());
					}
					else {			
	//					com_close();
						openflag = false;
						comm.com_off();
						if(comm.isAlive())
						{
							comm.stop();	
						}
						
						System.out.println("关闭串口");
						abs.setText("打开串口");
					}
				}
				else {
					openflag = false;
					abs.setText("打开串口");
//					System.out.println("还是线程啊");
				}
			}			
		}
		// TODO Auto-generated method stub	
		/*接收区*/
		reci.setPreferredSize(new Dimension(weight,high/3));		//设定接收区大小
		recil.add(cleanreci,BorderLayout.WEST);										//将 发送左 部分添加进发送区容器
		cleanreci.setPreferredSize(new Dimension(150,high/4));
		textreci.setPreferredSize(new Dimension(weight-200,high/4));
		textreci.setLineWrap(true);
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
		textsend.setLineWrap(true);
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
		onoff_SP.addActionListener(new KGListener());	
		cleanreci.addActionListener(new clean());
		cleansend.addActionListener(new clean());
	}
	
//	void com_close()
//	{
//		comm.com_off();
//	}
	
	public static void main(String[] args) {
		UI02 ui = new UI02();
		ui.Sign_up();		

		
	}
}

class Com extends Thread
{
	ComUtility a_com = new ComUtility();
	String com_num = "";
	int com_spd;
	void set_comnum(String com_port)
	{
		com_num = com_port;
//		a_com.setcom(com_port);	
	}
	void set_comspd(int com_speed)
	{
		com_spd = com_speed;
//		a_com.setspd(com_speed);
	}
	boolean com_init()
	{
		a_com.setcom(com_num);	
		a_com.setspd(com_spd);
		try {
			a_com.init();
			UI02.abs.setText("关闭串口");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			UI02.abs.setText("打开串口");
			UI02.abs.setSelected(false);
			return false;
		}
	}
	void com_off()
	{
		a_com.closeSerialPort();
	}
	public void run(){
		boolean init_statu = com_init();
		boolean com_reading = false;
		while(UI02.openflag)
			{
				if(init_statu)
				{
					try {
						if (!com_reading) {
							a_com.readComm();
							com_reading = true;
						}	
						Thread.sleep(10);
						UI02.textreci.append(a_com.get_str());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
				else 
				{
				System.out.println("参数初始化失败");
				UI02.openflag = false;
				}
		}

	}
}

