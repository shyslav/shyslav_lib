package lazyfunction;

/**
 * Created by shyslav on 9/10/16.
 */
public class LazyDate {
    /**
     * Get current time millis casted to int
     *
     * @return current time millis
     */
    public static int getUnixDate() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * How much time passed from the time
     *
     * @param time check time
     * @return minutes
     */
    public static int getMinutesFromMillis(int time) {
        return (getUnixDate() - time) / 60;
    }
}
