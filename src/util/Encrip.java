package util;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrip {
	
	public final static String ALGORITME = "AES";
	public final static String CLAU = "IOC";
	
	private SecretKey clau;
	private Cipher cipher;
	private int keysize = 16;
	
	public Encrip() {
		clau = new SecretKeySpec(Arrays.copyOf(CLAU.getBytes(), keysize), ALGORITME);
	}
	
	public String encriptar(String crida){
		String encriptado = null;
		try {
			cipher = Cipher.getInstance(ALGORITME);
			cipher.init(Cipher.ENCRYPT_MODE, clau);
			byte[] cipherbytes = cipher.doFinal(crida.getBytes());
			encriptado = Base64.getEncoder().encodeToString(cipherbytes);			
		} catch (Exception e) {
			
		}
		return encriptado;
	}
	
	public String desencriptar(String crida){
		byte[] cipherbytes = null;
			try {
				cipher = Cipher.getInstance(ALGORITME);
				cipher.init(Cipher.DECRYPT_MODE, clau);
				byte[] bytesEncriptats = Base64.getDecoder().decode(crida);
				cipherbytes = cipher.doFinal(bytesEncriptats);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new String(cipherbytes);
	}
}
