package ui;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.List;

import javax.swing.JOptionPane;

import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.ReadDataFromSerialPortFailure;
import serialException.SerialPortInputStreamCloseFailure;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;
import serialPort.SerialTool;
//import serialPort.DataView.SerialListener;


public class Main01 {

	private static List<String> commList = null;	//保存可用端口号
	private static SerialPort serialPort = null;	//保存串口对象
	
	public static void main(String[] args) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse {
		// TODO Auto-generated method stub
		commList = SerialTool.findPort();	//程序初始化时就扫描一次有效串口
		System.out.println(commList);
		serialPort = SerialTool.openPort("COM6", 9600);
		
		
		try {
			SerialTool.addListener(serialPort, new SerialPortEventListener() {
				
				@Override
				public void serialEvent(SerialPortEvent event) {
					// TODO Auto-generated method stub
			        switch (event.getEventType()) {

		            case SerialPortEvent.BI: // 10 通讯中断
		            	JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
		            	break;

		            case SerialPortEvent.OE: // 7 溢位（溢出）错误

		            case SerialPortEvent.FE: // 9 帧错误

		            case SerialPortEvent.PE: // 8 奇偶校验错误

		            case SerialPortEvent.CD: // 6 载波检测

		            case SerialPortEvent.CTS: // 3 清除待发送数据

		            case SerialPortEvent.DSR: // 4 待发送数据准备好了

		            case SerialPortEvent.RI: // 5 振铃指示

		            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
		            	break;
		            
		            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
		            	
		            	//System.out.println("found data");
						byte[] data = null;
						
						try {
							if (serialPort == null) {
								JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								data = SerialTool.readFromPort(serialPort);	//读取数据，存入字节数组
								//System.out.println(new String(data));
								
								//自定义解析过程
								if (data == null || data.length < 1) {	//检查数据是否读取正确	
									JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
									System.exit(0);
								}
								else {
									String dataOriginal = new String(data);	//将字节数组数据转换位为保存了原始数据的字符串
									String dataValid = "";	//有效数据（用来保存原始数据字符串去除最开头*号以后的字符串）
									String[] elements = null;	//用来保存按空格拆分原始字符串后得到的字符串数组	
									//解析数据
									if (dataOriginal.charAt(0) == '*') {	//当数据的第一个字符是*号时表示数据接收完成，开始解析							
										dataValid = dataOriginal.substring(1);
										elements = dataValid.split(" ");
										if (elements == null || elements.length < 1) {	//检查数据是否解析正确
											JOptionPane.showMessageDialog(null, "数据解析过程出错，请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
											System.exit(0);
										}
										else {
											try {
												//更新界面Label值
												/*for (int i=0; i<elements.length; i++) {
													System.out.println(elements[i]);
												}*/
												//System.out.println("win_dir: " + elements[5]);
//												tem.setText(elements[0] + " ℃");
//												hum.setText(elements[1] + " %");
//												pa.setText(elements[2] + " hPa");
//												rain.setText(elements[3] + " mm");
//												win_sp.setText(elements[4] + " m/s");
//												win_dir.setText(elements[5] + " °");
											} catch (ArrayIndexOutOfBoundsException e) {
												JOptionPane.showMessageDialog(null, "数据解析过程出错，更新界面数据失败！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
												System.exit(0);
											}
										}	
									}
								}
								
							}						
							
						} catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
							JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);	//发生读取错误时显示错误信息后退出系统
						}	
			            
						break;
		
		        }

		    }
				}
					
				}
			});
		} catch (TooManyListeners e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 以内部类形式创建一个串口监听类
	 * @author zhong
	 *
	 */
	private class SerialListener implements SerialPortEventListener {
		
		/**
		 * 处理监控到的串口事件
		 */
	    public void serialEvent(SerialPortEvent serialPortEvent) {
	    	
	    	
	    	
	    	
	    	

	    
	    
	    
	    

	}

}
