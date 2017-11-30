package rxtx_test001;

import rxtx_test001.SerialPortUtilityNew;

public class RxTx_test001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialPortUtilityNew porr = new SerialPortUtilityNew();
		porr.init();
		porr.send();
		porr.readComm();
//		porr.closeSerialPort();
		

	}

}


