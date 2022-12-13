package util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrip {
	
	private SecretKey clau;
	private Cipher cipher;
	private String type = "AES";
	private int keysize = 16;
	
	public Encrip() {
	}

	public void afegirClau(String valor) {
		byte[] valuebytes = valor.getBytes();
		clau = new SecretKeySpec(Arrays.copyOf(valuebytes, keysize), type);
	}
	
	public byte[] encriptar(String crida) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		cipher = Cipher.getInstance(type);
		cipher.init(Cipher.ENCRYPT_MODE, clau);
		byte[] cridaABytes = crida.getBytes();
		byte[] cipherbytes = cipher.doFinal(cridaABytes);
		return cipherbytes;
	}
	
	public String desencriptar(byte[] crida) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		cipher = Cipher.getInstance(type);
		cipher.init(Cipher.DECRYPT_MODE, clau);
		byte[] cipherbytes = cipher.doFinal(crida);
		return new String(cipherbytes);
	}
}
