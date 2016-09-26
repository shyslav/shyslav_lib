import lazyfunction.LazyComputerInfo;
import lazyfunction.LazyDate;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by shyslav on 9/11/16.
 */
public class LazyTest {
    @Test
    public void lazyDateTest() throws InterruptedException {
        int currentTime = LazyDate.getUnixDate();
        assertTrue(LazyDate.getMinutesFromMillis(currentTime) == 0);
        assertTrue(LazyDate.getMinutesFromMillis(currentTime - 60) == 1);
        assertTrue(LazyDate.getMinutesFromMillis(currentTime - 120) == 2);
        assertTrue(LazyDate.getMinutesFromMillis(currentTime - 180) == 3);
    }

    @Test
    public void mouseTest() {
        ArrayList<String> arrayList = LazyComputerInfo.getLinuxMouseDevice();
        assertNotNull(arrayList);
        assertTrue(arrayList.size()>0);
    }
}
