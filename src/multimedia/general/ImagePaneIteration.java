package general;

import javafx.scene.input.InputEvent;

import java.io.File;
import java.util.ArrayList;

import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.controller.ControllerParameters.isMinMove;


public class ImagePaneIteration extends ImagePaneMoved {

    //Поле, содержащее список файлов всех фоновых изображений
    // для данного экземпляра панели
    protected ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //размеры ImageView с предыдущим изображением
    private double prevWeight = 0;
    private double prevHeight = 0;

    //флаг включения/выключения центрирования панели при смене фона
    private boolean flagCenterPane = true;


    public void setFlagCenterPane(final boolean flagCenterPane) {
        this.flagCenterPane = flagCenterPane;
    }


    //конструктор с передаваемым списком файлов фоновых изображений
    // и ограничениями на максимальный размер
    public ImagePaneIteration(final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter.get(0), wMax, hMax);
        this.imageFiles.addAll(imageFilesEnter);
        this.imageFiles.setIndex(0);

        mouseEvent();
        touchEvent();
    }

    public ImagePaneIteration(final File imageFile, final double wMax, final double hMax) {
        super(imageFile, wMax, hMax);
        this.imageFiles.add(imageFile);

        mouseEvent();
        touchEvent();
    }


    @Override
    protected void mouseEvent() {
        super.mousePressed();
        super.mouseDragged();
        this.mouseReleased();
    }

    @Override
    protected void mouseReleased(final String style) {
        this.setOnMouseReleased(event -> releasedAction(event, style));
    }

    @Override
    protected void mouseReleased() {
        mouseReleased("");
    }

    @Override
    protected void touchEvent() {
        super.touchPressed();
        super.touchMoved();
        this.touchReleased();
    }

    @Override
    protected void touchReleased(final String style) {
        this.setOnTouchReleased(event -> releasedAction(event, style));
    }

    @Override
    protected void touchReleased() {
        touchReleased("");
    }

    @Override
    protected void releasedAction(final InputEvent event, final String style) {

        if (isEventPermission(event)) {

            if (isMinMove(this.getRelocationCoordinates().getXDelta(),
                    this.getRelocationCoordinates().getYDelta())) {
                super.releasedAction(event, style);
            } else {

                if (flagPressed) {
                    //исключение перемещения панели,
                    // если она смещена меньше, чем на 10 пикселей
                    // из-за плохой работы тачпанели на мультимедиа столе
                    this.setTranslateX(0);
                    this.setTranslateY(0);
                }

                //данная панель используется не только для листания фона панели,
                // поэтому листание фона панели вынесено в отдельный метод
                // чтобы его можно было перезаписать
                setChildAction();

                super.releasedActionWithIteration(style);
            }
        }
    }

    public void setChildAction() {
        setNextBackground();
    }

    //установка следующего фонового изображения из списка imageFiles
    protected void setNextBackground() {
        if (imageFiles.size() > 1) {

            if (imageFiles.hasNextElement()) {
                setBackground(imageFiles.getNextElement());
            } else {
                setBackground(imageFiles.getFirstElement());
            }
        }
    }

    private void setBackground(final File imageFile) {
        setPrevSize();
        this.setImageBackground(imageFile);
        saveCenterLocation();
    }

    //метод установки значений ширины и высоты предыдущей панели
    // (при изменении фона панели)
    private void setPrevSize() {
        prevWeight = this.getPrefWidth();
        prevHeight = this.getPrefHeight();
    }

    //метод сохраняющий местоположение панели по центру
    // при изменении ее размеров
    private void saveCenterLocation() {

        if (!flagCenterPane) return;

        if (prevWeight == 0 && prevHeight == 0) return;

        if (this.getPrefWidth() == prevWeight &&
                this.getPrefHeight() == prevHeight) return;

        this.setLayoutX(this.getLayoutX() + prevWeight / 2 - this.getPrefWidth() / 2);
        this.setLayoutY(this.getLayoutY() + prevHeight / 2 - this.getPrefHeight() / 2);
    }

    public int getCurrentBackgroundIndex() {
        return imageFiles.getCurrentIndex();
    }

    public int getLastBackgroundIndex() {
        return imageFiles.size() - 1;
    }

}
