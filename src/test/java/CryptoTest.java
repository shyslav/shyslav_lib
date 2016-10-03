import lazyfunction.LazyMD5;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by shyslav on 10/3/16.
 */
public class CryptoTest {

    /**
     * @author Joe Prasanna Kumar
     * This program provides the following cryptographic functionalities
     * 1. Encryption using DES
     * 2. Decryption using DES
     * <p>
     * The following modes of DES encryption are supported by SUNJce provider
     * 1. ECB (Electronic code Book) - Every plaintext block is encrypted separately
     * 2. CBC (Cipher Block Chaining) - Every plaintext block is XORed with the previous ciphertext block
     * 3. PCBC (Propogating Cipher Block Chaining) -
     * 4. CFB (Cipher Feedback Mode) - The previous ciphertext block is encrypted and this enciphered block is XORed with the plaintext block to produce the corresponding ciphertext block
     * 5. OFB (Output Feedback Mode) -
     * <p>
     * High Level Algorithm :
     * 1. Generate a DES key
     * 2. Create the Cipher (Specify the Mode and Padding)
     * 3. To Encrypt : Initialize the Cipher for Encryption
     * 4. To Decrypt : Initialize the Cipher for Decryption
     * <p>
     * Need for Padding :
     * Block ciphers operates on data blocks on fixed size n.
     * Since the data to be encrypted might not always be a multiple of n, the remainder of the bits are padded.
     * PKCS#5 Padding is what will be used in this program
     */


    @Test
    public void test() {
        String strDataToEncrypt;
        String strCipherText;
        String strDecryptedText;

        try {
            /**
             *  Step 1. Generate a DES key using KeyGenerator
             *
             */
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            SecretKey keyWithoutMd5 = keyGen.generateKey();

            String encodedKey = Base64.getEncoder().encodeToString(keyWithoutMd5.getEncoded());
            byte[] decodedKey = Base64.getDecoder().decode(LazyMD5.md5(encodedKey));

            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
            /**
             *  Step2. Create a Cipher by specifying the following parameters
             * 			a. Algorithm name - here it is DES
             * 			b. Mode - here it is CBC
             * 			c. Padding - PKCS5Padding
             */

            Cipher desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding"); /* Must specify the mode explicitly as most JCE providers default to ECB mode!! */

            /**
             *  Step 3. Initialize the Cipher for Encryption
             */

            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            /**
             *  Step 4. Encrypt the Data
             *  		1. Declare / Initialize the Data. Here the data is of type String
             *  		2. Convert the Input Text to Bytes
             *  		3. Encrypt the bytes using doFinal method
             */
            strDataToEncrypt = "Hello World of Encryption";
            byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
            byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);
            strCipherText = new BASE64Encoder().encode(byteCipherText);
            System.out.println("Cipher Text generated using DES with CBC mode and PKCS5 Padding is " + strCipherText);

            /**
             *  Step 5. Decrypt the Data
             *  		1. Initialize the Cipher for Decryption
             *  		2. Decrypt the cipher bytes using doFinal method
             */
            desCipher.init(Cipher.DECRYPT_MODE, secretKey, desCipher.getParameters());
            //desCipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] byteDecryptedText = desCipher.doFinal(byteCipherText);
            strDecryptedText = new String(byteDecryptedText);
            System.out.println(" Decrypted Text message is " + strDecryptedText);
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
        } catch (InvalidAlgorithmParameterException invalidParam) {
            System.out.println(" Invalid Parameter " + invalidParam);
        }
    }
}