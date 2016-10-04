package crypto;

import lazyfunction.LazyMD5;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by shyslav on 10/4/16.
 */
public class LazyCrypto {
    private static final String encryptionCode = LazyMD5.md5("ThisIsSparta");
    /**
     * Algorithm name "DES,AES" / Mode / Padding - PKCS5Padding
     */
    private static final String algorithm = "DESede/CFB/PKCS5Padding";
    private static final String cipher = "DESede";

    public static byte[] cryptString(String incoming) {
        //transform md5 hash string to bytes
        byte[] arrayBytes = encryptionCode.getBytes();
        try {
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(cipher);
            SecretKey secretKey = skf.generateSecret(ks);

            Cipher desCipher = Cipher.getInstance(algorithm);
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] byteDataToEncrypt = incoming.getBytes();
            byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);
            String strCipherText = new BASE64Encoder().encode(byteCipherText);
            System.out.println("Cipher Text generated using DES with CBC mode and PKCS5 Padding is " + strCipherText);

            return byteCipherText;
        } catch (NoSuchAlgorithmException noSuchAlgo) {
            System.out.println(" No Such Algorithm exists " + noSuchAlgo);
        } catch (NoSuchPaddingException noSuchPad) {
            System.out.println(" No Such Padding exists " + noSuchPad);
        } catch (InvalidKeyException invalidKey) {
            System.out.println(" Invalid Key " + invalidKey);
        } catch (BadPaddingException badPadding) {
            System.out.println(" Bad Padding " + badPadding);
        } catch (IllegalBlockSizeException illegalBlockSize) {
            System.out.println(" Illegal Block Size " + illegalBlockSize);
        } catch (InvalidKeySpecException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decryptText(byte[] cryptedString) {
        byte[] arrayBytes = encryptionCode.getBytes();
        try {
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(cipher);
            SecretKey secretKey = skf.generateSecret(ks);
            Cipher desCipher = Cipher.getInstance(algorithm);

            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            desCipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            //desCipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] byteDecryptedText = desCipher.doFinal(cryptedString);
            String strDecryptedText = new String(byteDecryptedText);
            System.out.println(" Decrypted Text message is " + strDecryptedText);
            return strDecryptedText;
        } catch (NoSuchAlgorithmException noSuchAlgo) {
            System.out.println(" No Such Algorithm exists " + noSuchAlgo);
        } catch (NoSuchPaddingException noSuchPad) {
            System.out.println(" No Such Padding exists " + noSuchPad);
        } catch (InvalidKeyException invalidKey) {
            System.out.println(" Invalid Key " + invalidKey);
        } catch (BadPaddingException badPadding) {
            System.out.println(" Bad Padding " + badPadding);
        } catch (IllegalBlockSizeException illegalBlockSize) {
            System.out.println(" Illegal Block Size " + illegalBlockSize);
        } catch (InvalidKeySpecException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
