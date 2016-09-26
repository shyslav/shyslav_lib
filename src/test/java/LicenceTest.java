import licenseframe.InitialLicence;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by shyslav on 9/25/16.
 */
public class LicenceTest {
    @Test
    public void initializeLicense() {
        InitialLicence licence = InitialLicence.getLicence();
        assertNotNull(licence);
        assertTrue(licence.size()>0);
    }

    @Test
    public void checkLicenseKey(){
        InitialLicence licence = InitialLicence.getLicence();
        assertTrue(licence.checkLicense());
    }
}
