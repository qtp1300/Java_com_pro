/*串口ver1.0
 * 实现串口发送接收
 * 仅限单个字符
 * 下个版本ver1.1实现字符串通信*/

package rxtx_test001;

import java.util.*;
import java.io.*;
import gnu.io.*;

public class SerialPortUtilityNew implements SerialPortEventListener {

	// 检测系统中可用的通讯端口类
	private CommPortIdentifier portId;
	// Enumeration 为枚举型类,在util中
	private Enumeration<CommPortIdentifier> portList;

	// 输入输出流
	private InputStream inputStream;
	private OutputStream outputStream;

	// RS-232的串行口
private SerialPort serialPort;
public static String test = "";//保存串口返回信息
//	private static SerialPortUtilityNew uniqueInstance;//单例创建
//初始化串口
public void init() {
		// 获取系统中所有的通讯端口
		portList = CommPortIdentifier.getPortIdentifiers();
		// 用循环结构找出串口
		while (portList.hasMoreElements()) {
			// 强制转换为通讯端口类型
			portId = (CommPortIdentifier) portList.nextElement();
			// 判断是否为串口
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
//比较串口名称是否为“COM3”
				if (portId.getName().equals("COM4")) {
					System.out.println("找到COM4");
					// 打开串口
					try {
						// open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
						serialPort = (SerialPort) portId.open(
								"GSMModemTest2.0", 2000);
						// 设置串口监听
						serialPort.addEventListener(new SerialPortUtilityNew());
						// 设置可监听
						serialPort.notifyOnDataAvailable(true);

						/* 设置串口通讯参数 */
						// 波特率，数据位，停止位和校验方式
						// 波特率2400,偶校验
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
	 * 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
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
		case SerialPortEvent.DATA_AVAILABLE://获取到串口返回信息
			readComm();
			break;
		default:
			break;
		}
	}
	//读取串口返回信息
//	public void readComm() {
//		byte[] readBuffer = new byte[1024];
//
//		try {
//			inputStream = serialPort.getInputStream();
//			// 从线路上读取数据流
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
			// 从线路上读取数据流
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
	 * 关闭串口
	 */
//	public static void closeSerialPort() {
//		uniqueInstance.serialPort.close();
//}
//向串口发送信息方法
private void sendMsg(){
		String information = "Aaa";//要发送的内容
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
	String information = "A";//要发送的内容
	try {
		outputStream.write(information.getBytes());
		inputStream = serialPort.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}
