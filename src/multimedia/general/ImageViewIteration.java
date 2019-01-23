package general;

import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;

import java.io.File;
import java.util.ArrayList;

import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.controller.ControllerParameters.isMinMove;

public class ImageViewIteration extends ImageViewMoved {

    //Поле, содержащее список файлов всех изображений
    // для данного экземпляра класса
    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //размеры ImageView с предыдущим изображением
    private double prevWeight = 0;
    private double prevHeight = 0;

    //флаг включения/выключения центрирования панели при смене фона
    private boolean flagCenterImageView = false;


    public void setFlagCenterImageView(final boolean flagCenterImageView) {
        this.flagCenterImageView = flagCenterImageView;
    }


    public ImageViewIteration(final ArrayList<File> imageFilesEnter) {
        super(imageFilesEnter.get(0));
        this.imageFiles.addAll(imageFilesEnter);
        this.imageFiles.setIndex(0);

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

                //данная панель используется, только если нужно листать изображения,
                // поэтому устанавливать флаг на разрешение листания и проверять его состояние
                // просто не имеет смысла
                setNextImage();

                super.releasedActionWithIteration(style);
            }
        }
    }


    //Установка следующего изображения из списка imageFiles по кругу,
    // т.е. при достижении конца списка
    // следующим устанавливается первое изображение из списка
    public void setNextImage() {
        if (imageFiles.hasNextElement()) {
            setImage(imageFiles.getNextElement());
        } else {
            setImage(imageFiles.getFirstElement());
        }
    }

    //Установка изображения
    private void setImage(final File imageFile) {
        setPrevSize();
        this.setImage(new Image("file:" + imageFile.toString()));
        saveCenterLocation();
    }

    //Метод установки значений ширины и высоты предыдущего изображения
    private void setPrevSize() {
        if (!flagCenterImageView) return;

        prevWeight = this.getLayoutBounds().getWidth();
        prevHeight = this.getLayoutBounds().getHeight();
    }

    //метод сохраняющий местоположение изображения по центру
    // при изменении его размеров при листании
    private void saveCenterLocation() {

        if (!flagCenterImageView) return;

        if (prevWeight == 0 && prevHeight == 0) return;

        if (this.getLayoutBounds().getWidth() == prevWeight &&
                this.getLayoutBounds().getHeight() == prevHeight) return;

        this.setLayoutX(this.getLayoutX() + prevWeight / 2 - this.getLayoutBounds().getWidth() / 2);
        this.setLayoutY(this.getLayoutY() + prevHeight / 2 - this.getLayoutBounds().getHeight() / 2);
    }


}
