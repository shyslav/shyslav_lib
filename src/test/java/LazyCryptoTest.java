import crypto.LazyCrypto;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shyslav on 10/4/16.
 */
public class LazyCryptoTest {
    @Test
    public void lazyCryptTest() {
        String codeString = "Hello vasya pupkin";
        byte[] encode = LazyCrypto.cryptString(codeString);
        String test = LazyCrypto.decryptText(encode);
        assertNotNull(encode);
        assertNotNull(test);
        assertEquals(test,codeString);
    }
}
