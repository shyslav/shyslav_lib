package lazydata;

/**
 * Created by shyslav on 9/10/16.
 */
public class LazyDate {
    public static int getUnixDate(){
        return (int) (System.currentTimeMillis() / 1000);
    }
}
