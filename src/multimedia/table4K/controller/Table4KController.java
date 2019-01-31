package table4K.controller;

import javafx.scene.Node;
import javafx.scene.input.InputEvent;

import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;



abstract public class Table4KController {

    //метод перехода к разделу,
    // который должен быть переопределен
    // в подклассах, соответствующих разделам
    abstract protected void action();

    protected Table4KController(final Node nodeArg) {
        nodeArg.setOnMouseClicked(this::actionAfterPermission);
        nodeArg.setOnTouchReleased(this::actionAfterPermission);
    }

    private void actionAfterPermission(final InputEvent event) {
        if (isEventPermission(event)) {
            action();
            eventDelayBegin();
        }
    }

}