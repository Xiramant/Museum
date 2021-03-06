package general;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;


import java.io.File;

import static general.RestrictionCoordinates.getNotDetermined;
import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;


public class ImageViewMoved extends ImageView {

    //Поле для хранения параметров расположения ImageView
    // при его перемещении
    private RelocationCoordinates relocationCoordinates = new RelocationCoordinates();

    //Поле для хранения ограничения положения ImageView
    private RestrictionCoordinates restrictionCoordinates = new RestrictionCoordinates();

    //флаг разрешения на перемещение изображения
    private boolean permissionMove = true;

    //Флаг разрешения на ограничение перемещения ImageView
    private boolean permissionLocationRestriction = false;

    //Флаг нажатия мышки или тача
    //Требуется, чтобы событие mouseDragged / touchMoved (а также mouseReleased / touchReleased)
    // можно было регистрировать только после события mousePressed / touchPressed
    protected boolean flagPressed = false;


    public void setMoveDisabled() {
        this.permissionMove = false;

        mouseEvent();
        touchEvent();
    }

    protected RelocationCoordinates getRelocationCoordinates() {
        return relocationCoordinates;
    }

    private void setRelocationCoordinates(final RelocationCoordinates relocationCoordinates) {
        this.relocationCoordinates = relocationCoordinates;
    }


    public ImageViewMoved(final File imageFile) {
//        super(new Image(imageFile.toURI().toURL().toString()));
        super(new Image("file:" + imageFile.toString()));

        mouseEvent();
        touchEvent();
    }


    protected void mouseEvent() {
        mousePressed();
        mouseDragged();
        mouseReleased();
    }

    protected void mousePressed() {
        this.setOnMousePressed(event -> pressedAction(event));
    }

    protected void mouseDragged(final String style) {
        this.setOnMouseDragged(event -> movedAction(event, style));
    }

    protected void mouseDragged() {
        mouseDragged("");
    }

    protected void mouseReleased(final String style) {
        this.setOnMouseReleased(event -> releasedAction(event, style));
    }

    protected void mouseReleased() {
        mouseReleased("");
    }


    //Метод обработки действий по тач касаниям
    protected void touchEvent() {
        touchPressed();
        touchMoved();
        touchReleased();
    }

    protected void touchPressed() {
        this.setOnTouchPressed(event -> pressedAction(event));
    }

    protected void touchMoved(final String style) {
        this.setOnTouchMoved(event -> movedAction(event, style));
    }

    protected void touchMoved() {
        touchMoved("");
    }

    protected void touchReleased(final String style) {
        this.setOnTouchReleased(event -> releasedAction(event, style));
    }

    protected void touchReleased() {
        touchReleased("");
    }


    //Выполнение действий по событию Нажатие кнопки мыши или Нажатие тача
    private void pressedAction(final InputEvent event) {

        if (isEventPermission(event) && permissionMove) {

                this.toFront();

                this.getRelocationCoordinates().setXBegin(getX(event));
                this.getRelocationCoordinates().setYBegin(getY(event));

                flagPressed = true;
        }
    }

    //Выполнение действий по событию Перемещение мышью или Перемещение тачем
    private void movedAction(final InputEvent event, final String style) {

        if (flagPressed && isEventPermission(event) && permissionMove) {

            this.getRelocationCoordinates().setXDelta(getX(event) - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(getY(event) - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            setActionStyle(style);
        }
    }

    protected void releasedAction(final InputEvent event, final String style) {

        if (flagPressed && isEventPermission(event)) {

            if (permissionMove) {
                this.setLayoutX(this.getLayoutX() + this.getTranslateX());
                this.setLayoutY(this.getLayoutY() + this.getTranslateY());
                this.setTranslateX(0);
                this.setTranslateY(0);

                this.clearRelocationCoordinates();

                releasedActionWithIteration(style);
            }
        }
    }

    protected void releasedActionWithIteration(final String style) {
        setActionStyle(style);

        if (permissionLocationRestriction) {
            setLocationRestriction();
        }

        flagPressed = false;

        TouchWait.eventDelayBegin();
    }

    //получение положения X по событию мыши или тача
    private double getX(final InputEvent event) {
        return event.getClass().equals(MouseEvent.class)?
                ((MouseEvent)event).getSceneX():
                ((TouchEvent)event).getTouchPoint().getSceneX();
    }

    //получение положения Y по событию мыши или тача
    private double getY(final InputEvent event) {
        return event.getClass().equals(MouseEvent.class)?
                ((MouseEvent)event).getSceneY():
                ((TouchEvent)event).getTouchPoint().getSceneY();
    }

    //установка стиля объекта данного класса при производстве действий с ним
    private void setActionStyle(final String style) {
        if (style.length() > 0) {
            this.setStyle(style);
        }
    }


    private void clearRelocationCoordinates() {
        setRelocationCoordinates(new RelocationCoordinates());
    }

    //установка значений области ограничивающей положение панели
    public void setRestrCoor(final double left, final double top, final double right, final double bottom) {

        restrictionCoordinates.setRestrictionCoordinates(left, top, right, bottom);

        this.permissionLocationRestriction = true;
    }

    //метод устанавливающий ограничения на местоположения панели
    // в соответствии с областью, заданной в restrictionCoordinates
    private void setLocationRestriction() {

        if (isDetermined(restrictionCoordinates.getLeft())) {
            if (this.getLayoutX() < restrictionCoordinates.getLeft()) {
                this.setLayoutX(restrictionCoordinates.getLeft());
            }
        }

        if (isDetermined(restrictionCoordinates.getTop())) {
            if (this.getLayoutY() < restrictionCoordinates.getTop()) {
                this.setLayoutY(restrictionCoordinates.getTop());
            }
        }

        if (isDetermined(restrictionCoordinates.getRight())) {
            if (this.getLayoutX() + this.getLayoutBounds().getWidth() > restrictionCoordinates.getRight()) {
                this.setLayoutX(restrictionCoordinates.getRight() - this.getLayoutBounds().getWidth());
            }
        }

        if (isDetermined(restrictionCoordinates.getBottom())) {
            if (this.getLayoutY() + this.getLayoutBounds().getHeight() > restrictionCoordinates.getBottom()) {
                this.setLayoutY(restrictionCoordinates.getBottom() - this.getLayoutBounds().getHeight());
            }
        }
    }

    //Выявление - определена ли координата ограничения
    private boolean isDetermined(final double restrictionCoordinate) {
        return (Math.abs(restrictionCoordinate - getNotDetermined()) > 0.1);
    }
}
