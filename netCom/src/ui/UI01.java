/*��һ��Ľ��ƻ����շ������ϲ����������һ��״̬��*/
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
		/*��ʼ���ؼ�*/
		JFrame window = new JFrame("��������");				//����
		/*������*/
		JPanel reci = new JPanel();					//������ʾ����	
		JPanel recil = new JPanel();
		JTextArea textreci = new JTextArea();		//�Ϸ��ı���������
		JButton cleanreci = new JButton("���������");
		/*������*/
		JPanel sen = new JPanel();					//������ʾ����
		JPanel senl = new JPanel();
		JTextArea textsend = new JTextArea();		//�Ϸ��ı���ʾ����
		JButton cleansend = new JButton("���������");
		JButton send = new JButton("����");
		/*������*/			
		JPanel control = new JPanel();				//���Ʋ���	
		JPanel set = new JPanel(new FlowLayout());
		JPanel kg = new JPanel();
		JToggleButton onoff_SP = new JToggleButton("�򿪴���");
		JLabel com_text = new JLabel("�˿�");
		JComboBox<String> com_num = new JComboBox<String>();
		JLabel speed_text = new JLabel("����");
		JComboBox<String> speed = new JComboBox<String>();
		JLabel crc_text = new JLabel("У��λ");
		JComboBox<String> crc = new JComboBox<String>();
		JLabel stop_text = new JLabel("ֹͣλ");
		JComboBox<String> stop = new JComboBox<String>();		
		
		/*������*/
		reci.setPreferredSize(new Dimension(weight,high/3));		//�趨��������С
		recil.add(cleanreci,BorderLayout.WEST);										//�� ������ ������ӽ�����������
		cleanreci.setPreferredSize(new Dimension(150,high/4));
		textreci.setPreferredSize(new Dimension(weight-200,high/4));
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
		class Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abs = (AbstractButton)e.getSource();
				if(abs.isSelected())
				{
					System.out.println("���Դ򿪴���"+"ͨ��"+com_num.getSelectedItem()+"�˿ڣ���"+speed.getSelectedItem()+"������"+crc.getSelectedItem()+"λ"+stop.getSelectedItem()+"ֹͣλ");
					
					try {		
						
						porr.setcom(com_num.getSelectedItem().toString());
						porr.setspd(Integer.parseInt(speed.getSelectedItem().toString()));												
						porr.init();
						abs.setText("�رմ���");
						tempflag = true;
						porr.readComm();
//						System.out.println(porr.get_str());
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("��ͼ���ô��ڲ�����������ԭ��"+e2);
						abs.setText("�򿪴���");
						abs.setSelected(false);
						tempflag = false;
					} 				
				}
				else {
					System.out.println("�رմ���");
					abs.setText("�򿪴���");
				}
			}			
		}
		onoff_SP.addActionListener(new Listener());
		
	}
}

