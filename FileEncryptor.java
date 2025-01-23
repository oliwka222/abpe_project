import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptor {

    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8");
        key = java.security.MessageDigest.getInstance("SHA-256").digest(key);
        return new SecretKeySpec(key, 0, 16, ALGORITHM);
    }

    public static byte[] encryptData(byte[] data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decryptData(byte[] encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }
}