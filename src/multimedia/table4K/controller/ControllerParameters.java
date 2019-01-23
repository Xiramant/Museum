package table4K.controller;

import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

import static general.TouchWait.*;


public class ControllerParameters {

    //включение/отключение реакции на события мыши и тача
    private static final boolean MOUSE_PERMISSION = true;
    private static final boolean TOUCH_PERMISSION = true;

    //размер минимального смещения, при котором считается,
    // что изображение целеноправленно перемещалось,
    // а не сдвинулось случайно при щелчке / тапе
    private static final double MIN_MOVE = 10d;

    //время задержки после тача
    public static final int TOUCH_TIMEOUT = 300;



    //разрешено ли переданное событие (клик мыши или тач касание)
    public static boolean isEventPermission(final InputEvent event) {
        return (isDelayEnd() && isMouseOrTouchPermission(event));
    }

    //разрешено ли переданное событие (клик мыши или тач касание)
    public static boolean isMouseOrTouchPermission(final InputEvent event) {
        return (isMouseEventPermission(event) || isTouchEventPermission((event)));
    }

    private static boolean isMouseEventPermission(final InputEvent event) {
        return (isMouseEvent(event) && MOUSE_PERMISSION);
    }

    private static boolean isMouseEvent(final InputEvent event) {
        return event.getClass().equals(MouseEvent.class);
    }

    private static boolean isTouchEventPermission(final InputEvent event) {
        return (isTouchEvent(event) && TOUCH_PERMISSION);
    }

    private static boolean isTouchEvent(final InputEvent event) {
        return event.getClass().equals(TouchEvent.class);
    }


    //Индикация минимального перемещения изображения
    public static boolean isMinMove(final double xDelta, final double yDelta) {
        return Math.abs(xDelta) + Math.abs(yDelta) > MIN_MOVE;
    }

}
