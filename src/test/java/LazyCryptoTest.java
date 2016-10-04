import crypto.LazyCrypto;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shyslav on 10/4/16.
 */
public class LazyCryptoTest {
    @Test
    public void lazyCryptTest() {
        byte[] encode = LazyCrypto.cryptString("Hello vasya pupkin");
        String test = LazyCrypto.decryptText(encode);
        assertNotNull(encode);
        assertNotNull(test);
    }
}
