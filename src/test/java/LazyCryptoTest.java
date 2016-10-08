import crypto.LazyCrypto;
import database.configuration.DatabaseConnection;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shyslav on 10/4/16.
 */
public class LazyCryptoTest {
    @Test
    public void lazyCryptTest() {
        String codeString = "jdbc.drivers=com.mysql.jdbc.Driver\n" +
                "jdbc.url=jdbc:mysql://127.0.0.1:3306/protect_information?useUnicode=true&characterEncoding=utf-8\n" +
                "jdbc.username=root\n" +
                "jdbc.password=12345";
        byte[] encode = LazyCrypto.cryptString(codeString);
        String decodedString = new BASE64Encoder().encode(encode);
        String test = LazyCrypto.decryptText(encode);
        assertNotNull(encode);
        assertNotNull(test);
        assertEquals(test, codeString);
    }

    @Test
    public void lazyDatabaseCryptoConnectionTest() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.openConnection();
    }
}
