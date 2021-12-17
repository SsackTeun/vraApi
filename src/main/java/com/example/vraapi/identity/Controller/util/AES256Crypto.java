package com.example.vraapi.identity.Controller.util;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

@Component
public class AES256Crypto {
	
	/*32 bit key : 32 �ڸ�*/
	final static String secretKey = "123kjasaldkvnasdlfqwerdfadsfasda";
	
	/*volatile : �޸𸮿� ������ �ε�*/
	private static volatile AES256Crypto INSTANCE;
	
	/*IV option : 16bit*/
	static String IV = "";
	
	/* ��ü �ν��Ͻ� �������� */
	public static AES256Crypto getInstance() {
		if(INSTANCE == null) {
			synchronized (AES256Crypto.class) {
				if(INSTANCE == null) {
					INSTANCE = new AES256Crypto();
				}
			}
		}
		return INSTANCE;
	}
	
	private AES256Crypto() {
        IV = secretKey.substring(0, 16);
    }
	
	public String encryptAES256(String plainText) 
			throws NoSuchAlgorithmException, 
			NoSuchPaddingException, 
			InvalidKeyException, 
			InvalidAlgorithmParameterException, 
			IllegalBlockSizeException, 
			BadPaddingException,
			UnsupportedEncodingException {
		byte[] key = secretKey.getBytes();
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes()));
		
		byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        
		return enStr;
	}
	
	public String decryptAES256(String encryptedText) 
			throws NoSuchAlgorithmException, 
			NoSuchPaddingException, 
			UnsupportedEncodingException, 
			IllegalBlockSizeException, 
			BadPaddingException, 
			InvalidKeyException, 
			InvalidAlgorithmParameterException {
		
		byte[] keyData = secretKey.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));
        byte[] byteStr = Base64.decodeBase64(encryptedText.getBytes());
        return new String(cipher.doFinal(byteStr), "UTF-8");
	}
	
	/*
	 * public static void main(String[] args) throws InvalidKeyException,
	 * NoSuchAlgorithmException, NoSuchPaddingException,
	 * InvalidAlgorithmParameterException, IllegalBlockSizeException,
	 * BadPaddingException, UnsupportedEncodingException { AES256Crypto aes256Crypto
	 * = AES256Crypto.getInstance(); String encrypt = aes256Crypto.encryptAES256(
	 * "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjY4NjcwNjk5MTAyMTQ2MTQwNDYifQ.eyJpc3MiOiJDTj1QcmVsdWRlIElkZW50aXR5IFNlcnZpY2UsT1U9Q01CVSxPPVZNd2FyZSxMPVNvZmlhLFNUPVNvZmlhLEM9QkciLCJpYXQiOjE2MjY0MDg5MDMsImV4cCI6MTYyNjQzNzcwMywianRpIjoiZmU4Y2QxMDAtNWE5Yi00NWExLTg2OTYtM2RkZTI1YWNhZjczIiwiY29udGV4dCI6Ilt7XCJtdGRcIjpcInVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphYzpjbGFzc2VzOlBhc3N3b3JkUHJvdGVjdGVkVHJhbnNwb3J0XCIsXCJpYXRcIjoxNjI2NDA4OTAzLFwiaWRcIjoxNX1dIiwiYXpwIjoicHJlbHVkZS11c2VyLTNrRDJXNVd3QTEiLCJzdWIiOiJzZGRjLmxvY2FsOjVlN2Q4MTY0LTM4OWEtNDdiNC1iNjI2LWFiZTZkMWYxYjQ0MCIsImRvbWFpbiI6InNkZGMubG9jYWwiLCJ1c2VybmFtZSI6Imp5IiwicGVybXMiOlsiY3NwOm9yZ19vd25lciIsImV4dGVybmFsLzdlOWNlYzVjLTZiMjAtNGY3MS04ZWU4LWExNTgxMmVmZGU2MS9jYXRhbG9nOmFkbWluIiwiZXh0ZXJuYWwvYTVlYjg1NDQtMTM0ZC00ZDFjLWJlYTQtYzM4YzQyYzc1ZjFlL2F1dG9tYXRpb25zZXJ2aWNlOmNsb3VkX2FkbWluIl0sImNvbnRleHRfbmFtZSI6IjI5NGI2Yjk5LTE0MGMtNDA1Yi1iYTIzLTU3MDkzNmJhOGEyZCIsImFjY3QiOiJqeSJ9.Ycdkce9AMhVVYhshm9YufxNf-hGdiOFsx5ujQv7CO8PW410Ln2owCtrOM_2hd0V3GvyFC3oet77C0TRlZYjLtyOMuEJXYmCeSOKibceK9eDGQ3u8XKwlswBRe_BxQkzfj5QJQbu9v93CSYx6u8wkn6HY-ejIAlh8ib0FEhpu2cxyt2az8oPDIFMiFUnhIGCFLpthd8oTqtubdpfFgBBx-2ykmb46iqVBdnpy8L8uM0MC7Q2FbHTqzdGmf__rlQdOePAhafYen-5qSvy0Bm1EFz9FIkraYjyaV9WzKafsHaWnNgku3xUwXyVlojMdP4qtV3uGZwkM9et8lXTym_pwfA"
	 * ); System.out.println("enc : " + encrypt);
	 * 
	 * String decrypt = aes256Crypto.decryptAES256(encrypt);
	 * System.out.println("dec : " + decrypt); }
	 */
}