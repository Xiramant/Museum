package general;

import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;

import java.io.File;
import java.util.ArrayList;

import static general.TouchWait.isTimeWaitEnd;
import static table4K.Main4K.actionPermission;

public class ImageViewIteration extends ImageViewController {

    //размер минимального смещения, при котором считается,
    // что изображение целеноправленно перемещалось,
    // а не сдвинулось случайно при щелчке / тапе
    private static final double MIN_MOVE = 10d;

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

        ivcMouseEvent();
        ivcTouchEvent();
    }

    @Override
    protected void ivcMouseEvent() {
        super.ivcMousePressed();
        super.ivcMouseDragged();
        this.ivcMouseReleased();
    }

    @Override
    protected void ivcMouseReleased(final String style) {
        this.setOnMouseReleased(event -> releasedAction(event, style));
    }
    @Override
    protected void ivcMouseReleased() {
        ivcMouseReleased("");
    }

    @Override
    protected void ivcTouchEvent() {
        super.ivcTouchPressed();
        super.ivcTouchMoved();
        this.ivcTouchReleased();
    }

    @Override
    protected void ivcTouchReleased(final String style) {
        this.setOnTouchReleased(event -> releasedAction(event, style));
    }

    @Override
    protected void ivcTouchReleased() {
        ivcTouchReleased("");
    }

    @Override
    protected void releasedAction(final InputEvent event, final String style) {

        if (isTimeWaitEnd() && actionPermission(event)) {

            if (isMinMove()) {
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

    //Индикация минимального перемещения изображения
    private boolean isMinMove() {
        return Math.abs(this.getRelocationCoordinates().getXDelta()) +
                Math.abs(this.getRelocationCoordinates().getYDelta()) > MIN_MOVE;
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
