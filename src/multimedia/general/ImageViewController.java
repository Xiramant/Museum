package general;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.net.MalformedURLException;

import static general.RestrictionCoordinates.getNotDetermined;

public class ImageViewController extends ImageView {

    //Поле для хранения параметров расположения панели
    // при ее перемещении методом drag and drop
    protected RelocationCoordinates relocationCoordinates = new RelocationCoordinates();

    //Поле для хранения ограничения положения панели
    private RestrictionCoordinates restrCoor = new RestrictionCoordinates();

    public RelocationCoordinates getRelocationCoordinates() {
        return relocationCoordinates;
    }

    private boolean flagDragAndDrop = false;

    private boolean flagLocationRestriction = false;

    public void setFlagDragAndDrop(final boolean flagDragAndDrop) {
        this.flagDragAndDrop = flagDragAndDrop;

        if (flagDragAndDrop) {
            ivcMouseDragAndDrop();
            ivcTouchDragAndDrop();
        }
    }

    public void setRelocationCoordinates(final RelocationCoordinates relocationCoordinates) {
        this.relocationCoordinates = relocationCoordinates;
    }

    public ImageViewController(final File imageFile) {
//        super(new Image(imageFile.toURI().toURL().toString()));
        super(new Image("file:" + imageFile.toString()));
    }


    protected void ivcMouseDragAndDrop() {
        ivcMousePressed();
        ivcMouseDragged();
        ivcMouseReleased();
    }

    protected void ivcMousePressed() {
        this.setOnMousePressed(mouseEvent -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            this.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });
    }

    protected void ivcMouseDragged(final String style) {

        this.setOnMouseDragged(mouseEvent -> {

            this.getRelocationCoordinates().setXDelta(mouseEvent.getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(mouseEvent.getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            if (style.length() > 0) {
                this.setStyle(style);
            }
        });
    }

    protected void ivcMouseDragged() {
        ivcMouseDragged("");
    }

    protected void ivcMouseReleased(final String style) {

        this.setOnMouseReleased(mouseEvent -> {

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();

            if (style.length() > 0) {
                this.setStyle(style);
            }

            if (flagLocationRestriction) {
                setLocationRestriction();
            }
        });
    }

    protected void ivcMouseReleased() {
        ivcMouseReleased("");
    }


    //Метод обработки действий по тач касаниям
    protected void ivcTouchDragAndDrop() {
        ivcTouchPressed();
        ivcTouchMoved();
        ivcTouchReleased();
    }

    protected void ivcTouchPressed() {

        this.setOnTouchPressed(event -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(event.getTouchPoint().getSceneX());
            this.getRelocationCoordinates().setYBegin(event.getTouchPoint().getSceneY());
        });
    }

    protected void ivcTouchMoved(final String style) {

        this.setOnTouchMoved(event -> {

            this.getRelocationCoordinates().setXDelta(event.getTouchPoint().getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(event.getTouchPoint().getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            if (style.length() > 0) {
                this.setStyle(style);
            }
        });
    }

    protected void ivcTouchMoved() {
        ivcTouchMoved("");
    }


    protected void ivcTouchReleased(final String style) {

        this.setOnTouchReleased(event -> {

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();


            if (style.length() > 0) {
                this.setStyle(style);
            }

            if (flagLocationRestriction) {
                setLocationRestriction();
            }

            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе ImageViewController при отпускании тача");
            }
        });
    }

    protected void ivcTouchReleased() {
        ivcTouchReleased("");
    }

    protected void clearRelocationCoordinates() {
        setRelocationCoordinates(new RelocationCoordinates());
    }

    //установка значений области ограничивающей положение панели
    public void setRestrCoor(final double left, final double top, final double right, final double bottom) {

        restrCoor.setRestrictionCoordinates(left, top, right, bottom);

        this.flagLocationRestriction = true;
    }

    //метод устанавливающий ограничения на местоположения панели
    // в соответствии с областью, заданной в restrCoor
    private void setLocationRestriction() {

        if (Math.abs(restrCoor.getLeft() - getNotDetermined()) > 0.1) {
            if (this.getLayoutX() < restrCoor.getLeft()) {
                this.setLayoutX(restrCoor.getLeft());
            }
        }

        if (Math.abs(restrCoor.getTop() - getNotDetermined()) > 0.1) {
            if (this.getLayoutY() < restrCoor.getTop()) {
                this.setLayoutY(restrCoor.getTop());
            }
        }

        if (Math.abs(restrCoor.getRight() - getNotDetermined()) > 0.1) {
            if (this.getLayoutX() + this.getLayoutBounds().getWidth() > restrCoor.getRight()) {
                this.setLayoutX(restrCoor.getRight() - this.getLayoutBounds().getWidth());
            }
        }

        if (Math.abs(restrCoor.getBottom() - getNotDetermined()) > 0.1) {
            if (this.getLayoutY() + this.getLayoutBounds().getHeight() > restrCoor.getBottom()) {
                this.setLayoutY(restrCoor.getBottom() - this.getLayoutBounds().getHeight());
            }
        }
    }
}