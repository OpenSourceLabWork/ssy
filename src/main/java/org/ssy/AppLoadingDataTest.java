package org.ssy;

import java.util.List;

public class AppLoadingDataTest {
	
	public static  List<String> overalldata;

	public AppLoadingDataTest() {
		
	}
	
	
	public static void main(String[] args) {
		
		overalldata = DataLoader.loadXmlDataTest("exp(1)_1000.xml");
		
		for(String data : overalldata){
			System.out.println("1 :"+ data);
		}
	}
}
