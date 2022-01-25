package it.unisa.servlet;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

	public class PasswordHasher {
	        private static final String ALGORITHM = "SHA-1";
		
		//---------------------------------------------------------------------------------
		// CODIFICA PASSWORD
		//---------------------------------------------------------------------------------
		public static String scramble(String password) 
		{
			try {
				MessageDigest md = MessageDigest.getInstance( ALGORITHM );
				md.update( password.getBytes("UTF-8") );
				return toHex( md.digest() );
			} 
			catch (UnsupportedEncodingException e) {
				return null;
			} catch (NoSuchAlgorithmException e)
			{
				return null;
			}
		}

	        //---------------------------------------------------------------------------------
		// AUTHENTICATE PASSWORD
		//---------------------------------------------------------------------------------
		public static boolean authenticate( String password, String hash ) {
			return hash.equals( scramble( password ) );
		}

	        //---------------------------------------------------------------------------------
		// PRIVATE METHODS
		//---------------------------------------------------------------------------------
		private static String toHex(byte[] data)  {
			StringBuffer sb = new StringBuffer();
			for (byte b : data) {
				String digit = Integer.toString(b & 0xFF, 16);

				if (digit.length() == 1) {
					sb.append("0");
				}
				sb.append(digit);
			}
			return sb.toString();
		}
}

