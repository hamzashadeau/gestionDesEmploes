package com.example.stock.Utilis;

import java.security.MessageDigest;

public class HashUtil {
	public static String hash(String mdp) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(mdp.getBytes());
		byte byteData[] = md.digest();
StringBuffer hexString = new StringBuffer();
for (int i = 0; i < byteData.length; i++) {
	String hex = Integer.toHexString(0xff & byteData[i]);
	if(hex.length()==1) hexString.append('0');
	hexString.append(hex);
}
	System.out.println("En format hexa:" + hexString.toString());
	return hexString.toString();
	}
	
}
