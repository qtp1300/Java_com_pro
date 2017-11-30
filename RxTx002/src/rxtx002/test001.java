package rxtx002;

public class test001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComUtility commm = new ComUtility();
		try {
			commm.init();
		} catch (ComException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commm.getportlist();
	}

}
