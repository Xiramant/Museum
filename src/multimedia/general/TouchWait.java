package general;

import static table4K.controller.ControllerParameters.TOUCH_TIMEOUT;

public class TouchWait {

    private static long timeWait = 0;

    public static boolean isDelayEnd() {
        return (System.currentTimeMillis() > timeWait);
    }

    public static void eventDelayBegin(final long wait) {
        TouchWait.timeWait = System.currentTimeMillis() + wait;
    }

    public static void eventDelayBegin() {
        eventDelayBegin(TOUCH_TIMEOUT);
    }

}
