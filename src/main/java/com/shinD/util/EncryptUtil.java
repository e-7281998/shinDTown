package com.shinD.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptUtil {
	public static String getSalt() {
		SecureRandom secure = new SecureRandom();
		byte[] salt = new byte[20];
		
		//난수 생성
		//nextBytes() 바이트 배열을 랜덤하게 채운다.
		secure.nextBytes(salt);
		
		//byte to String (10진수 분자열로 변경)
		StringBuffer sb = new StringBuffer();
		for(byte b:salt) {
			sb.append(String.format("%02x", b));
		}
 		
		return sb.toString(); 
	}
	
	//Hash 생성
	public static String getEncrypt(String pwd, String salt) {
		String result = "";
		try {
			//SHA-256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			//pwd+salt 문자열에 SHA 256 적용
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt = md.digest();

			StringBuffer sb = new StringBuffer();
			for(byte b: pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			result = sb.toString();
 		
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
