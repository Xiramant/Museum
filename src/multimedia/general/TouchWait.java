package general;

import static table4K.Main4K.TOUCH_TIMEOUT;

public class TouchWait {

    private static long timeWait = 0;

    public static boolean isTimeWaitEnd() {
        return (System.currentTimeMillis() > timeWait);
    }

    public static void setTimeWait() {
        TouchWait.timeWait = System.currentTimeMillis() + TOUCH_TIMEOUT;
    }

}
