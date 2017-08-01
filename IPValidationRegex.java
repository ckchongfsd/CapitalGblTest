package com.capitalgbl.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPValidationRegex {

	/* define pattern*/
	public static final String pattern = "^([01]?\\d\\d|2[0-4]\\d|25[0-5]|\\d{2}|\\d{1})\\." +
			"([01]?\\d\\d|2[0-4]\\d|25[0-5]|\\d{2}|\\d{1})\\." +
					 "([01]?\\d\\d|2[0-4]\\d|25[0-5]|\\d{2}|\\d{1})\\."+
					"([01]?\\d\\d|2[0-4]\\d|25[0-5]|\\d{2}|\\d{1})";
	public static String pattern2 = "\\d+"; //do check for all number
	private static String fileName ="IpTestFile.txt";
	public static void main(String[] args) {
		//String test = "266.253.255.122";
		String filepath = "c:/test/"+fileName;

		//IPValidationRegex IpValidate = new IPValidationRegex();
		startIpValidate(filepath);
		//isValidIPAddress(test);

	}
	
	public static void startIpValidate(String path) {
		// read from file
		FileInputStream fis = null;
		BufferedReader br = null;
		
		try {
			fis = new FileInputStream(new File(path));
			br = new BufferedReader(new InputStreamReader(fis)); 
			String line;
			for(line = br.readLine(); line!=null; line = br.readLine()){
//				if (isValidIPAddress(line)){
//					System.out.println("Valid is "+line);
//				}

				if (line.matches(pattern)){
					System.out.println("Valid is "+line);
				}
			}			
		}catch 	(IOException ioe){
			
			ioe.printStackTrace();
		}
	}
	

	public static Boolean isValidIPAddress(String inIPAddr){
		
		Boolean bResult = true;
		//split with .
		String separator = "\\.";
		String[] noms=inIPAddr.split(separator);
		//System.out.println("the length is "+noms.length);
		if (noms.length != 4 ) return false;
		for(String part : noms){
			
			bResult = part.matches(pattern2) && part.length() <= 3 && Integer.parseInt(part)<=255 ;
			//System.out.println(" value "+part);
			if (!bResult) return false;
			
		}
		
		return bResult;
	}

}