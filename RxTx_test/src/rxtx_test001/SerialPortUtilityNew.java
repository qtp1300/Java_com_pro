/*����ver1.0
 * ʵ�ִ��ڷ��ͽ���
 * ���޵����ַ�
 * �¸��汾ver1.1ʵ���ַ���ͨ��*/

package rxtx_test001;

import java.util.*;
import java.io.*;
import gnu.io.*;

public class SerialPortUtilityNew implements SerialPortEventListener {

	// ���ϵͳ�п��õ�ͨѶ�˿���
	private CommPortIdentifier portId;
	// Enumeration Ϊö������,��util��
	private Enumeration<CommPortIdentifier> portList;

	// ���������
	private InputStream inputStream;
	private OutputStream outputStream;

	// RS-232�Ĵ��п�
private SerialPort serialPort;
public static String test = "";//���洮�ڷ�����Ϣ
//	private static SerialPortUtilityNew uniqueInstance;//��������
//��ʼ������
public void init() {
		// ��ȡϵͳ�����е�ͨѶ�˿�
		portList = CommPortIdentifier.getPortIdentifiers();
		// ��ѭ���ṹ�ҳ�����
		while (portList.hasMoreElements()) {
			// ǿ��ת��ΪͨѶ�˿�����
			portId = (CommPortIdentifier) portList.nextElement();
			// �ж��Ƿ�Ϊ����
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
//�Ƚϴ��������Ƿ�Ϊ��COM3��
				if (portId.getName().equals("COM4")) {
					System.out.println("�ҵ�COM4");
					// �򿪴���
					try {
						// open:��Ӧ�ó�����������������������ʱ�ȴ��ĺ�������
						serialPort = (SerialPort) portId.open(
								"GSMModemTest2.0", 2000);
						// ���ô��ڼ���
						serialPort.addEventListener(new SerialPortUtilityNew());
						// ���ÿɼ���
						serialPort.notifyOnDataAvailable(true);

						/* ���ô���ͨѶ���� */
						// �����ʣ�����λ��ֹͣλ��У�鷽ʽ
						// ������2400,żУ��
						serialPort.setSerialPortParams(2400,
SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						test = "";
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
		sendMsg();
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
				System.out.println(new String(readBuffer, 0, len).trim());
				test += new String(readBuffer, 0, len).trim();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
}
/**
	 * �رմ���
	 */
//	public static void closeSerialPort() {
//		uniqueInstance.serialPort.close();
//}
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

public void send()
{
	String information = "A";//Ҫ���͵�����
	try {
		outputStream.write(information.getBytes());
		inputStream = serialPort.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}
