/*����ver1.0
 * ʵ�ִ��ڷ��ͽ���
 * ���޵����ַ�
 * �¸��汾ver1.1ʵ���ַ���ͨ��*/

package rxtx001;

import java.util.*;
import java.io.*;

import javax.management.loading.PrivateClassLoader;

import gnu.io.*;

public class CopyOfSerialPortUtilityNew_bak implements SerialPortEventListener {

	// ���ϵͳ�п��õ�ͨѶ�˿���
	private CommPortIdentifier portId;
	// Enumeration Ϊö������,��util��
	private Enumeration<CommPortIdentifier> portList;

	// ���������
	private InputStream inputStream;
	private OutputStream outputStream;

	// RS-232�Ĵ��п�
public SerialPort serialPort;					//�ĳ���publicΪ�����رմ���
public static String test = "";					//���洮�ڷ�����Ϣ
private String comnum = "";
private String speed = "";
//	private static SerialPortUtilityNew uniqueInstance;//��������

public void setcom(String comm)		//���ö˿ں�
{
	comnum = comm;
}
public void setspd(String spd)		//���ò�����
{
	speed = spd;
}
public void init() {		//��ʼ������
		// ��ȡϵͳ�����е�ͨѶ�˿�
		portList = CommPortIdentifier.getPortIdentifiers();
		// ��ѭ���ṹ�ҳ�����
		while (portList.hasMoreElements()) {
			// ǿ��ת��ΪͨѶ�˿�����
			portId = (CommPortIdentifier) portList.nextElement();
			// �ж��Ƿ�Ϊ����
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){//�Ƚϴ��������Ƿ�Ϊ��COM3��
				if (portId.getName().equals("COM4")) {
					System.out.println("�ҵ�COM4");
					// �򿪴���
					try {
						// open:��Ӧ�ó�����������������������ʱ�ȴ��ĺ�������
						serialPort = (SerialPort) portId.open("GSMModemTest2.0", 2000);
						// ���ô��ڼ���
						serialPort.addEventListener(new CopyOfSerialPortUtilityNew_bak());
						// ���ÿɼ���
						serialPort.notifyOnDataAvailable(true);

						/* ���ô���ͨѶ���� */
						// �����ʣ�����λ��ֹͣλ��У�鷽ʽ
						// ������2400,żУ��
						serialPort.setSerialPortParams(2400,SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
//						test = "";
						outputStream = serialPort.getOutputStream();
					} catch (PortInUseException e) {
						e.printStackTrace();
					} catch (TooManyListenersException e) {
						e.printStackTrace();
					} catch (UnsupportedCommOperationException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		sendMsg();
	}
/**
	 * ʵ�ֽӿ�SerialPortEventListener�еķ��� ��ȡ�Ӵ����н��յ�����
	 */
	public void serialEvent(SerialPortEvent event) {

		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE://��ȡ�����ڷ�����Ϣ
			readComm();
			break;
		default:
			break;
		}
	}
	//��ȡ���ڷ�����Ϣ
//	public void readComm() {
//		byte[] readBuffer = new byte[1024];
//
//		try {
//			inputStream = serialPort.getInputStream();
//			// ����·�϶�ȡ������
//			int len = 0;
//			while ((len = inputStream.read(readBuffer)) != -1) {
//				System.out.println(new String(readBuffer, 0, len).trim());
//				test += new String(readBuffer, 0, len).trim();
//				break;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//}
	public void readComm() {
		byte[] readBuffer = new byte[1024];

		try {
			inputStream = serialPort.getInputStream();
			// ����·�϶�ȡ������
			int len = 0;
			while ((len = inputStream.read(readBuffer)) != -1) {
//				System.out.println(inputStream.read(readBuffer));
				System.out.println(new String(readBuffer, 0, len).trim());
//				test += new String(readBuffer, 0, len).trim();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
}
/**
	 * �رմ���
	 */
	public void closeSerialPort() {
		serialPort.close();
}
//�򴮿ڷ�����Ϣ����
	
private void sendMsg(){
		String information = "Aaa";//Ҫ���͵�����
		try {
			outputStream.write(information.getBytes());
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void sendstr(String info)
{
	String information = "A";//Ҫ���͵�����
	Byte tempByte;
//	tempByte = info.getBytes();
	try {
//		if(info.getBytes())
		outputStream.write(info.getBytes());
		inputStream = serialPort.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}
