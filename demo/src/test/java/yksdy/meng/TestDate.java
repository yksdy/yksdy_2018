package test.java.yksdy.meng;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		System.out.println("hihi");
		String[] skuIdArr = "SKU2016120811373834100001*1".split("\\*");
		  System.out.println("\n\n-----4----\ntest: skuIdArr[0] "+skuIdArr[0]+" --------\n");
		  System.out.println("\n\n-----5----\ntest: skuIdArr[1] "+skuIdArr[1]+" --------\n");
		  String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		  System.out.println("\n\n-----5----\ntest: str "+str+" --------\n");
	}
}
