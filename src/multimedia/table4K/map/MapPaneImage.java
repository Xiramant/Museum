package table4K.map;

import general.ImagePane;
import general.ImagePaneIteration;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.mainPane;
import static table4K.map.Map.*;
import static table4K.map.MapPaneText.МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER;


public class MapPaneImage extends ImagePaneIteration {

    //максимальная ширина карты
    private static final double МAP_PANE_IMAGE_WIDTH_MAX = MAP_INITIAL_AREA_X_END - MAP_INITIAL_AREA_X_BEGIN;

    //максимальная высота карты
    private static final double МAP_PANE_IMAGE_HEIGHT_MAX = MAP_INITIAL_AREA_Y_END - MAP_INITIAL_AREA_Y_BEGIN;

    //файл фона для количества страниц (карт)
    private static final File MAP_PAGE_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/map_page_background.jpg");

    //максимальная ширина фона для количества страниц (карт)
    private static final double MAP_PAGE_BACKGROUND_WIDTH_MAX = 140 / DEBUGGING_RATIO;

    //максимальная высота фона для количества страниц (карт)
    private static final double MAP_PAGE_BACKGROUND_HEIGHT_MAX = 30 / DEBUGGING_RATIO;

    //отступ слева для фона количества страниц (карт)
    private static final double MAP_PAGE_BACKGROUND_X = 16 / DEBUGGING_RATIO;

    //отступ сверху для фона количества страниц (карт)
    private static final double MAP_PAGE_BACKGROUND_Y = 10 / DEBUGGING_RATIO;

    //отображение текста количества страниц
    private Text mapCountPageText = new Text();

    //отступ сверху для блока количество карт
    private static final double MAP_PAGE_TOP_PADDING = 14 / DEBUGGING_RATIO;

    //отступ слева для блока количество карт
    private static final double MAP_PAGE_LEFT_PADDING = 24 / DEBUGGING_RATIO;

    //шрифт блока количество страниц
    private static final Font МAP_PANE_IMAGE_FONT_PAGE_NUMBER = new Font("Book Antiqua Bold Italic", МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER);

    //тень
    private static final String MAP_PANE_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 2);";


    public MapPaneImage(final ArrayList<File> imageFilesEnter) {
        super(imageFilesEnter, МAP_PANE_IMAGE_WIDTH_MAX, МAP_PANE_IMAGE_HEIGHT_MAX);

        this.setLayoutX(MAP_INITIAL_AREA_X_BEGIN / 2 + MAP_INITIAL_AREA_X_END / 2 - this.getPrefWidth() / 2);
        this.setLayoutY(MAP_INITIAL_AREA_Y_BEGIN / 2 + MAP_INITIAL_AREA_Y_END / 2 - this.getPrefHeight() / 2);

        mpiScale();

        this.setStyle(MAP_PANE_SHADOW);

        this.ipiMouseClicked();
        this.TouchReleased();

        ImagePane pageCount = new ImagePane(MAP_PAGE_BACKGROUND_FILE,
                                            MAP_PAGE_BACKGROUND_WIDTH_MAX,
                                            MAP_PAGE_BACKGROUND_HEIGHT_MAX);
        pageCount.setLayoutX(MAP_PAGE_BACKGROUND_X);
        pageCount.setLayoutY(MAP_PAGE_BACKGROUND_Y);

        mapCountPageText.setFont(МAP_PANE_IMAGE_FONT_PAGE_NUMBER);
        mapCountPageText.setX(MAP_PAGE_LEFT_PADDING);
        mapCountPageText.setTextAlignment(TextAlignment.LEFT);
        mapCountPageText.setY(MAP_PAGE_TOP_PADDING);
        mapCountPageText.setTextOrigin(VPos.TOP);

        setMapCountPageText();

        setCenterPaneFlag(true);

        this.getChildren().addAll(pageCount, mapCountPageText);
    }

    @Override
    public void ipiMouseClicked() {

        this.setOnMouseClicked(event -> {

            int prevIndex = this.getCurrentBackgroundIndex();

            //нажатие левой кнопки приводит к листанию карт вперед
            if (event.getButton() == MouseButton.PRIMARY) {
                this.setNextImageBackground();
            }

            //нажатие правой кнопки приводит к листанию карт назад
            if (event.getButton() == MouseButton.SECONDARY) {
                this.setPrevImageBackground();
            }

            //Двойной щелчок приводит к переходу к последней карте
            if (event.getClickCount() == 2) {
                this.setLastImageBackground();
            }

            //Двойной щелчок правой кнопкой приводит к переходу к первой карте
            if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                this.setFirstImageBackground();
            }

            mpiScale();

            setMapCountPageText();

            //если карта сменилась, то смена текста в текстовой панели
            if (prevIndex != this.getCurrentBackgroundIndex()) {
                ((MapPaneText)mainPane.getChildren().get(1)).setDisplayText(this.getCurrentBackgroundIndex());
            }
        });
    }

    @Override
    public void TouchReleased(final String style) {

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {

                int prevIndex = this.getCurrentBackgroundIndex();

                //перелистывание страниц письма вперед,
                this.setNextImageBackground();

                mpiScale();

                setMapCountPageText();

                //если карта сменилась, то смена текста в текстовой панели
                if (prevIndex != this.getCurrentBackgroundIndex()) {
                    ((MapPaneText)mainPane.getChildren().get(1)).setDisplayText(this.getCurrentBackgroundIndex());
                }

                setTimeWait();
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

    //установка отображения количества страниц
    private void setMapCountPageText() {
        mapCountPageText.setText("карта: " + (getCurrentBackgroundIndex() + 1) + " / " + (getLastBackgroundIndex() + 1));
    }
}
