package common.source;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import org.testng.Assert;

public class CryptoUtility {
	private static final String key = "SeKretSalt321654"; // 128 bit key
	// Create key and cipher
	private static Key aesKey = null;
	private static Cipher cipher = null;

	
	public static String encrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, 
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String encryptedString = null;
		createCipherKey();
		// encrypt the text
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		// convert to Base64 before returning
		byte[] encodedBytes = Base64.encodeBase64(encrypted);
		encryptedString = new String(encodedBytes);
		return encryptedString;
	}

	private static void createCipherKey() throws NoSuchAlgorithmException, NoSuchPaddingException {
		// Create key and cipher
		aesKey = new SecretKeySpec(key.getBytes(), "AES");
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	}
	
	public static String decrypt(String encryptedText)  {
		// Decode to get the encrypted bytes first.
		String decryptedString = null;
		try {
			createCipherKey();
			byte[] encryptedBytes = Base64.decodeBase64(encryptedText);
			// decrypt the encrypted bytes
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decryptedString = new String(cipher.doFinal(encryptedBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	public static void main(String[] args) {
		try {
			Log.info("Running test - testEncrypt_ValidString1_Success() ...");
			testEncrypt_ValidString1_Success();
			Log.info("Running test - testDecrypt_ValidString1_Success() ...");
			testDecrypt_ValidString1_Success();
			Log.info("Running test - testEncrypt_ValidString2_Success() ...");
			testEncrypt_ValidString2_Success();
			Log.info("Running test - testDecrypt_ValidString2_Success() ...");
			testDecrypt_ValidString2_Success();			
			Log.info("Running test - testEncrypt_EmptyString_Success() ...");
			testEncrypt_EmptyString_Success();
			Log.info("Running test - testDecrypt_EmptyStringDecrypted_Success() ...");
			testDecrypt_EmptyStringDecrypted_Success();
			Log.info("Running test - testEncrypt_1CharString_Success() ...");
			testEncrypt_1CharString_Success();
			Log.info("Running test - testDecrypt_1CharStringDecrypted_Success() ...");
			testDecrypt_1CharStringDecrypted_Success();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
	}

	private static void testEncrypt_ValidString1_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypt = CryptoUtility.encrypt("sqa#Picarro$0");
		Log.info(encrypt);
		Assert.assertTrue(encrypt.equals("oeHwHqmv621dZ1MRE2BSdw=="));
	}

	private static void testDecrypt_ValidString1_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String decrypt = CryptoUtility.decrypt("oeHwHqmv621dZ1MRE2BSdw==");
		Log.info(decrypt);
		Assert.assertTrue(decrypt.equals("sqa#Picarro$0"));
	}
	
	private static void testEncrypt_ValidString2_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypt = CryptoUtility.encrypt("FastLane!911");
		Log.info(encrypt);
		Assert.assertTrue(encrypt.equals("6NZYEwkq24lm9xBekD04sg=="));
	}

	private static void testDecrypt_ValidString2_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String decrypt = CryptoUtility.decrypt("6NZYEwkq24lm9xBekD04sg==");
		Log.info(decrypt);
		Assert.assertTrue(decrypt.equals("FastLane!911"));
	}

	private static void testEncrypt_EmptyString_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypt = CryptoUtility.encrypt("");
		Log.info(encrypt);
		Assert.assertTrue(encrypt.length() > 0);
	}

	private static void testDecrypt_EmptyStringDecrypted_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String decrypt = CryptoUtility.decrypt("wOxFBd7IUOnydO7jfvSdLA==");
		Log.info(decrypt);
		Assert.assertTrue(decrypt.equals(""));
	}

	private static void testEncrypt_1CharString_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypt = CryptoUtility.encrypt("1");
		Log.info(encrypt);
		Assert.assertTrue(encrypt.equals("jNhT9691OQ8dJHYlxhMag=="));
	}

	private static void testDecrypt_1CharStringDecrypted_Success() throws InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String decrypt = CryptoUtility.decrypt("jNhT9691OQ8dJHYlxhMag==");
		Log.info(decrypt);
		Assert.assertTrue(decrypt.equals("1"));
	}
}