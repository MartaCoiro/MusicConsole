package it.unisa.servlet;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Encryption {

	 public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) 
			 throws NoSuchAlgorithmException, InvalidKeySpecException {
		 System.out.println("ok");
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	        System.out.println("ok1");
	        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
	        System.out.println("ok2");
	        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
	        System.out.println("ok3");
	        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	    }

	    public static String encrypt(String dataToEncrypt, SecretKeySpec key) 
	    		throws GeneralSecurityException, UnsupportedEncodingException {
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
	        AlgorithmParameters parameters = pbeCipher.getParameters();
	        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
	        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
	        byte[] iv = ivParameterSpec.getIV();
	        return base64Encode(iv) + ":" + base64Encode(cryptoText);
	    }

	    private static String base64Encode(byte[] bytes) {
	        return Base64.getEncoder().encodeToString(bytes);
	    }

	    public static String decrypt(String string, SecretKey key) 
	    //public static String decrypt(String string, SecretKeySpec key) 
	    		throws GeneralSecurityException, IOException {
	    	//copiato
	    	byte[] raw = key.getEncoded();
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        System.out.println("entra1");
	        String iv = string.split(":")[0];
	        String property = string.split(":")[1];
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        System.out.println("entra2");
	        //copiato
	        pbeCipher.init(Cipher.DECRYPT_MODE, skeySpec);
	        
	        //pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
	        //return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	        return new String(pbeCipher.doFinal(fromHexString(string)), "UTF-8");
	 	   
	    }
	    
	    public static byte[] fromHexString(String s) {//copiato
	        int len = s.length();
	        byte[] data = new byte[len / 2];
	        for (int i = 0; i < len; i += 2) {
	            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                                 + Character.digit(s.charAt(i+1), 16));
	        }
	        return data;
	    }
	    
	    private static byte[] base64Decode(String property) throws IOException {
	        return Base64.getDecoder().decode(property);
	    }
	}

