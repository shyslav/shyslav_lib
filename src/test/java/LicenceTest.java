import lazyfunction.LazyComputerInfo;
import lazyfunction.LazyWriter;
import licenseframe.InitialLicence;
import licenseframe.users.LicensedUsers;
import org.junit.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by shyslav on 9/25/16.
 */
public class LicenceTest {
    @Test
    public void initializeLicense() {
        InitialLicence licence = new InitialLicence();
        LicensedUsers user = licence.get(0);
        assertEquals(user.getComputerName(), LazyComputerInfo.getComputerName());
    }

    @Test
    public void writeLicenseFileTest() throws IOException, URISyntaxException {
        InitialLicence licence = new InitialLicence();
        LazyWriter.licenceWriteObject(licence);
    }
}
