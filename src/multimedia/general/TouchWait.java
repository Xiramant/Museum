package general;

import static table4K.Main4K.TOUCH_TIMEOUT;

public class TouchWait {

    private static long timeWait = 0;

    public static boolean isTimeWaitEnd() {
        return (System.currentTimeMillis() > timeWait);
    }

    public static void setTimeWait(final long wait) {
        TouchWait.timeWait = System.currentTimeMillis() + wait;
    }

    public static void setTimeWait() {
        setTimeWait(TOUCH_TIMEOUT);
    }

}
