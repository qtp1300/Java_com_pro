/*����ver1.1
 * ʵ�ִ��ڷ��ͽ���
 * ���޵����ַ�
 * �¸��汾ver1.1ʵ���ַ���ͨ��,�����ͷ�����װ*/
package rxtx001;

import rxtx001.SerialPortUtilityNew;

public class RxTx001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialPortUtilityNew porr = new SerialPortUtilityNew();
		porr.setcom("COM6");
		porr.setspd(2400);
		try {
			porr.init();
		} catch (ComException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
		{
		try {
//				porr.sendstr("s");
				porr.readComm();
				Thread.sleep(1000);
//				porr.closeSerialPort();
			} catch (InterruptedException e) 
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
//		porr.closeSerialPort();
		

	}

}


