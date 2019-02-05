package table4K.model.imagePresentation;

import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;

abstract public class ImageIterationWithMove<E extends Node> {

    private static final double MIN_MOVED_DISTANCE = 10d;

    private final Restriction RESTRICTION_AREA;

    private ImageIteration<E> imageIteration;

    private Relocation relocation = new Relocation();

    private String styleDefault = "";

    private String styleMove = "";



    public ImageIteration<E> getImageIteration() {
        return imageIteration;
    }



    protected ImageIterationWithMove(final ImageIteration<E> imageIterationArg,
                                     final Restriction restrictionAreaArg) {
        imageIteration = imageIterationArg;
        RESTRICTION_AREA = restrictionAreaArg;
        setEvent();
    }

    protected ImageIterationWithMove(final ImageIteration<E> imageIterationArg) {
        this(imageIterationArg, null);
    }



    private void setEvent() {
        setMouseEvent();
        setTouchEvent();
    }

    private void setMouseEvent() {
        mousePressed();
        mouseDragged();
        mouseReleased();
    }

    private void mousePressed() {
        imageIteration.getImageRepresentation().setOnMousePressed(this :: pressedAction);
    }

    private void mouseDragged() {
        imageIteration.getImageRepresentation().setOnMouseDragged(this :: movedAction);
    }

    private void mouseReleased() {
        imageIteration.getImageRepresentation().setOnMouseReleased(this :: releasedAction);
    }

    private void setTouchEvent() {
        touchPressed();
        touchMoved();
        touchReleased();
    }

    private void touchPressed() {
        imageIteration.getImageRepresentation().setOnTouchPressed(this :: pressedAction);
    }

    private void touchMoved() {
        imageIteration.getImageRepresentation().setOnTouchMoved(this :: movedAction);
    }

    private void touchReleased() {
        imageIteration.getImageRepresentation().setOnTouchReleased(this :: releasedAction);
    }



    //Выполнение действий по событию Нажатие кнопки мыши или Нажатие тача
    private void pressedAction(final InputEvent eventArg) {
        if (isEventPermission(eventArg)) {
            imageIteration.getImageRepresentation().toFront();

            relocation.setXBegin(getX(eventArg));
            relocation.setYBegin(getY(eventArg));

            imageIteration.getImageRepresentation().setStyle(styleMove);
        }
    }

    //Выполнение действий по событию Перемещение мышью или Перемещение тачем
    private void movedAction(final InputEvent eventArg) {

        if (isEventPermission(eventArg)) {

            relocation.setXDelta(getX(eventArg) - relocation.getXBegin());
            imageIteration.getImageRepresentation().setTranslateX(relocation.getXDelta());

            relocation.setYDelta(getY(eventArg) - relocation.getYBegin());
            imageIteration.getImageRepresentation().setTranslateY(relocation.getYDelta());

            imageIteration.getImageRepresentation().setStyle(styleMove);
        }
    }

    private void releasedAction(final InputEvent eventArg) {

        if (isEventPermission(eventArg)) {

            if (moved()) {
                imageIteration.getImageRepresentation().setLayoutX(imageIteration.getImageRepresentation().getLayoutX()
                        + imageIteration.getImageRepresentation().getTranslateX());
                imageIteration.getImageRepresentation().setLayoutY(imageIteration.getImageRepresentation().getLayoutY()
                        + imageIteration.getImageRepresentation().getTranslateY());
            }
            else {
                imageIteration.clickAction(eventArg);
            }

            imageIteration.getImageRepresentation().setTranslateX(0);
            imageIteration.getImageRepresentation().setTranslateY(0);

            relocation = new Relocation();

            movedToRestrictionArea();

            imageIteration.getImageRepresentation().setStyle(styleDefault);

            eventDelayBegin();
        }
    }

    private boolean moved() {
        return (Math.abs(imageIteration.getImageRepresentation().getTranslateX())
                + Math.abs(imageIteration.getImageRepresentation().getTranslateY()))
                > MIN_MOVED_DISTANCE;
    }

    //получение положения X по событию мыши или тача
    private double getX(final InputEvent eventArg) {
        if (eventArg.getClass().equals(MouseEvent.class)) {
            return ((MouseEvent)eventArg).getSceneX();
        }

        if (eventArg.getClass().equals(TouchEvent.class)) {
         return ((TouchEvent)eventArg).getTouchPoint().getSceneX();
        }

        return 0;
    }

    //получение положения Y по событию мыши или тача
    private double getY(final InputEvent eventArg) {
        if (eventArg.getClass().equals(MouseEvent.class)) {
            return ((MouseEvent)eventArg).getSceneY();
        }

        if (eventArg.getClass().equals(TouchEvent.class)) {
            return ((TouchEvent)eventArg).getTouchPoint().getSceneY();
        }

        return 0;
    }

    //установка стиля объекта данного класса при производстве действий с ним
    private void setActionStyle(final String style) {
        if (style.length() > 0) {
            imageIteration.getImageRepresentation().setStyle(style);
        }
    }

    private void movedToRestrictionArea() {

//        if (determined(RESTRICTION_AREA.getLeft()) && leftBorderBeyond()) {
//            imageIteration.getImageRepresentation().setLayoutX(RESTRICTION_AREA.getLeft());
//        }
//
//        if (determined(RESTRICTION_AREA.getTop()) && topBorderBeyond()) {
//            imageIteration.getImageRepresentation().setLayoutY(RESTRICTION_AREA.getTop());
//        }
//
//        if (determined(RESTRICTION_AREA.getRight()) && rightBorderBeyond()) {
//            imageIteration.getImageRepresentation().setLayoutX(RESTRICTION_AREA.getRight()
//                                                            - imageIteration.getImageRepresentation().getLayoutBounds().getWidth());
//        }
//
//        if (determined(RESTRICTION_AREA.getBottom()) && bottomBorderBeyond()) {
//            imageIteration.getImageRepresentation().setLayoutY(RESTRICTION_AREA.getBottom()
//                                                            - imageIteration.getImageRepresentation().getLayoutBounds().getHeight());
//        }

    }

    private boolean leftBorderBeyond() {
        return  imageIteration.getImageRepresentation().getLayoutX() < RESTRICTION_AREA.getLeft();
    }

    private boolean topBorderBeyond() {
        return  imageIteration.getImageRepresentation().getLayoutY() < RESTRICTION_AREA.getTop();
    }

    private boolean rightBorderBeyond() {
        return  imageIteration.getImageRepresentation().getLayoutX()
                + imageIteration.getImageRepresentation().getLayoutBounds().getWidth()
                < RESTRICTION_AREA.getRight();
    }

    private boolean bottomBorderBeyond() {
        return  imageIteration.getImageRepresentation().getLayoutY()
                + imageIteration.getImageRepresentation().getLayoutBounds().getHeight()
                < RESTRICTION_AREA.getBottom();
    }

}
