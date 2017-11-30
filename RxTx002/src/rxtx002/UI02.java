/*��һ��Ľ��ƻ����շ������ϲ����������һ��״̬��*/
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
	
	/*��ʼ���ؼ�*/
	//
	static JFrame window = new JFrame("��������");				//����
	/*������*/
	static JPanel reci = new JPanel();					//������ʾ����	
	static JPanel recil = new JPanel();
	static JTextArea textreci = new JTextArea();		//�Ϸ��ı���������
	static JButton cleanreci = new JButton("���������");
	/*������*/
	static JPanel sen = new JPanel();					//������ʾ����
	static JPanel senl = new JPanel();
	static JTextArea textsend = new JTextArea();		//�Ϸ��ı���ʾ����
	static JButton cleansend = new JButton("���������");
	static JButton send = new JButton("����");
	/*������*/			
	static JPanel control = new JPanel();				//���Ʋ���	
	static JPanel set = new JPanel(new FlowLayout());
	static JPanel kg = new JPanel();
	static JToggleButton onoff_SP = new JToggleButton("�򿪴���");
	static JLabel com_text = new JLabel("�˿�");
	static JComboBox<String> com_num = new JComboBox<String>();
	static JLabel speed_text = new JLabel("����");
	static JComboBox<String> speed = new JComboBox<String>();
	static JLabel crc_text = new JLabel("У��λ");
	static JComboBox<String> crc = new JComboBox<String>();
	static JLabel stop_text = new JLabel("ֹͣλ");
	static JComboBox<String> stop = new JComboBox<String>();
	static AbstractButton abs;				//���ؼ�
	
	void Sign_up() {
		class clean implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "���������":
					textreci.setText("");					
					break;
				case "���������":
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
						System.out.println("���Դ򿪴���"+"ͨ��"+com_num.getSelectedItem()+"�˿ڣ���"+speed.getSelectedItem()+"������"+crc.getSelectedItem()+"λ"+stop.getSelectedItem()+"ֹͣλ");
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
						
						System.out.println("�رմ���");
						abs.setText("�򿪴���");
					}
				}
				else {
					openflag = false;
					abs.setText("�򿪴���");
//					System.out.println("�����̰߳�");
				}
			}			
		}
		// TODO Auto-generated method stub	
		/*������*/
		reci.setPreferredSize(new Dimension(weight,high/3));		//�趨��������С
		recil.add(cleanreci,BorderLayout.WEST);										//�� ������ ������ӽ�����������
		cleanreci.setPreferredSize(new Dimension(150,high/4));
		textreci.setPreferredSize(new Dimension(weight-200,high/4));
		textreci.setLineWrap(true);
		reci.add(recil,BorderLayout.WEST);
		reci.add(textreci,BorderLayout.EAST);
		/*������*/
		senl.setPreferredSize(new Dimension(150,high/4));		//�趨��������С
		senl.setLayout(new FlowLayout());
		senl.add(cleansend);
		senl.add(send);	
		cleansend.setPreferredSize(new Dimension(150,high/9));
		send.setPreferredSize(new Dimension(150,high/9));	
		textsend.setPreferredSize(new Dimension(weight-200,high/4));
		textsend.setLineWrap(true);
		sen.add(senl,BorderLayout.WEST);
		sen.add(textsend,BorderLayout.EAST);		
		/*������*/
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
		crc.addItem("��У��");
		crc.addItem("��У��");
		crc.addItem("żУ��");
		crc.addItem("0У��");
		crc.addItem("1У��");
		stop.addItem("1λ");
		stop.addItem("1.5λ");
		stop.addItem("2λ");
		set.add(com_text);
		set.add(com_num);
		set.add(speed_text);
		set.add(speed);
		set.add(crc_text);
		set.add(crc);
		set.add(stop_text);
		set.add(stop);
		control.setPreferredSize(new Dimension(weight,high/4));

		window.setBounds(0, 0, weight, high);		//�ܴ��ڴ�С		
		window.setLocation(100, 100);				//���ڳ���λ��	
		window.add(reci,BorderLayout.NORTH);		//������
		window.add(sen,BorderLayout.CENTER);		//������
		window.add(control,BorderLayout.SOUTH);		//������
		
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);	
		/*
		 * ����*/
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
			UI02.abs.setText("�رմ���");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			UI02.abs.setText("�򿪴���");
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
				System.out.println("������ʼ��ʧ��");
				UI02.openflag = false;
				}
		}

	}
}

