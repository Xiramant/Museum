package table4K.map;

import general.ImagePaneIteration;
import javafx.scene.input.MouseButton;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.mainPane;
import static table4K.map.Map.*;

public class MapPaneImage extends ImagePaneIteration {

    public static double МAP_PANE_IMAGE_WIDTH_MAX = MAP_INITIAL_AREA_X_END - MAP_INITIAL_AREA_X_BEGIN;

    public static double МAP_PANE_IMAGE_HEIGHT_MAX = MAP_INITIAL_AREA_Y_END - MAP_INITIAL_AREA_Y_BEGIN;

    public MapPaneImage(final ArrayList<File> imageFilesEnter) {
        super(imageFilesEnter, МAP_PANE_IMAGE_WIDTH_MAX, МAP_PANE_IMAGE_HEIGHT_MAX);

        this.setLayoutX(MAP_INITIAL_AREA_X_BEGIN / 2 + MAP_INITIAL_AREA_X_END / 2 - this.getPrefWidth() / 2);
        this.setLayoutY(MAP_INITIAL_AREA_Y_BEGIN / 2 + MAP_INITIAL_AREA_Y_END / 2 - this.getPrefHeight() / 2);

        mpiScale();

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 2);");

        this.ipiMouseClicked();
        super.TouchReleased();

        setCenterPaneFlag(true);
    }

    @Override
    public void ipiMouseClicked() {

        this.setOnMouseClicked(event -> {

            int prevIndex = this.getCurrentBackgroundIndex();

            //нажатие левой кнопки приводит к листанию страниц письма вперед
            if (event.getButton() == MouseButton.PRIMARY) {
                this.setNextImageBackground();
            }

            //нажатие правой кнопки приводит к листанию страниц письма назад
            if (event.getButton() == MouseButton.SECONDARY) {
                this.setPrevImageBackground();
            }

            //Двойной щелчок приводит к переходу на последнюю страницу
            if (event.getClickCount() == 2) {
                this.setLastImageBackground();
            }

            //Двойной щелчок правой кнопкой приводит к возвращению письма на первую страницу
            if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                this.setFirstImageBackground();
            }

            mpiScale();

            if (prevIndex != this.getCurrentBackgroundIndex()) {
                ((MapPaneText)mainPane.getChildren().get(1)).setDisplayText(this.getCurrentBackgroundIndex());
            }

        });
    }

    //Масштабабирование панели,
    // если размер карты слишком маленький
    private void mpiScale(){

        this.setScaleX(1);
        this.setScaleY(1);

        double xScale = МAP_PANE_IMAGE_WIDTH_MAX / this.getPrefWidth();
        double yScale = МAP_PANE_IMAGE_HEIGHT_MAX / this.getPrefHeight();

        if (xScale > 1.01 && yScale > 1.01) {
            if (xScale < yScale) {
                this.setScaleX(xScale);
                this.setScaleY(xScale);
            } else {
                this.setScaleX(yScale);
                this.setScaleY(yScale);
            }
        }
    }

}
