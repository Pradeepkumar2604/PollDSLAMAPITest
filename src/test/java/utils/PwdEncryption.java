package utils;

import java.util.Base64;

public class PwdEncryption {

	public static String Encodepwd(String password)
	{
		byte[] endpwd = Base64.getEncoder().encode(password.getBytes());
		return new String(endpwd);
 	}
	
	public static String Decodepwd(String Encryptedpassword)
	{
		byte[] decodepwd = Base64.getDecoder().decode(Encryptedpassword);
		return new String(decodepwd);
	}
}
