package general;

import javafx.scene.input.MouseButton;

import java.io.File;
import java.util.ArrayList;

public class ImagePaneIteration extends ImagePane {

    //Поле, содержащее список файлов всех фоновых изображений
    // для данного экземпляра панели
    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //флаг регистрации перемещения ImagePaneIteration
    // если параметр в состоянии true, значит панель перемещали
    private boolean isDragAndDrop = false;

    //Поле для хранения параметров расположения панели
    // при ее перемещении методом drag and drop
    private RelocationCoordinates relocationCoordinates = new RelocationCoordinates();

    public RelocationCoordinates getRelocationCoordinates() {
        return relocationCoordinates;
    }

    public void setRelocationCoordinates(final RelocationCoordinates relocationCoordinates) {
        this.relocationCoordinates = relocationCoordinates;
    }


    //конструктор с передаваемым списком файлов фоновых изображений
    // и ограничениями на максимальный размер
    public ImagePaneIteration(final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter.get(0), wMax, hMax);
        this.imageFiles.addAll(imageFilesEnter);
        this.imageFiles.setIndex(0);
    }

    //конструктор с передаваемым списком файлов фоновых изображений
    // без задания ограничений на максимальный размер
    public ImagePaneIteration(final ArrayList<File> imageFiles) {
        this(imageFiles, 0, 0);
    }

    //установка следующего фонового изображения из списка imageFiles
    public void setNextImageBackground() {
        if (imageFiles.hasNextElement()) {
            this.setImageBackground(imageFiles.getNextElement());
        } else {
            this.setFirstImageBackground();
        }
    }

    //установка предыдущего фонового изображения из списка imageFiles
    public void setPrevImageBackground() {
        if (imageFiles.hasPrevElement()) {
            setImageBackground(imageFiles.getPrevElement());
        }
    }

    //установка первого фонового изображения из списка imageFiles
    public void setFirstImageBackground() {
        if (imageFiles.size() != 0) {
            setImageBackground(imageFiles.getFirstElement());
        }
    }

    //установка последнего фонового изображения из списка imageFiles
    public void setLastImageBackground() {
        if (imageFiles.size() != 0) {
            setImageBackground(imageFiles.getLastElement());
        }
    }

    public int getCurrentBackgroundIndex() {
        return imageFiles.getCurrentIndex();
    }

    public int getLastBackgroundIndex() {
        return imageFiles.size() - 1;
    }

    //Метод по очистке данных в поле relocationCoordinates
    public void clearRelocationCoordinates() {
        setRelocationCoordinates(new RelocationCoordinates());
    }


    //Метод обработки действий по щелчку мыши
    public void ipiMouseDragAndDrop() {
        mousePressed();
        mouseDragged();
        mouseReleased();
    }

    public void mousePressed() {
        this.setOnMousePressed(mouseEvent -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            this.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });
    }

    public void mouseDragged(final String style) {

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

    public void mouseDragged() {
        mouseDragged("");
    }

    public void mouseReleased(final String style) {

        this.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();

            if (style.length() > 0) {
                this.setStyle(style);
            }
        });
    }

    public void mouseReleased() {
        mouseReleased("");
    }


    public void ipiMouseClicked() {
        //перелистывание страниц письма
        this.setOnMouseClicked(event -> {

            if (!isDragAndDrop) {

                //нажатие левой кнопки приводит к листанию страниц письма вперед
                if (event.getButton() == MouseButton.PRIMARY) {
                    this.setNextImageBackground();
                }

                //нажатие правой кнопки приводит к листанию страниц письма назад
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.setPrevImageBackground();
                }

                //Двойной щелчок приводит к возвращению письма на первую страницу
                if (event.getClickCount() == 2) {
                    this.setFirstImageBackground();
                }
            }

            isDragAndDrop = false;
        });
    }


    //Метод обработки действий по тач касаниям
    public void ipiTouch() {
        TouchPressed();
        TouchMoved();
        TouchReleased();
    }

    public void TouchPressed() {

        this.setOnTouchPressed(event -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(event.getTouchPoint().getSceneX());
            this.getRelocationCoordinates().setYBegin(event.getTouchPoint().getSceneY());
        });
    }

    public void TouchMoved(final String style) {

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

    public void TouchMoved() {
        TouchMoved("");
    }


    public void TouchReleased(final String style) {

        this.setOnTouchReleased(event -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            } else {
                //исключение перемещения панели,
                // если она смещена меньше, чем на 10 пикселей
                // из-за плохой работы тачпанели на мультимедиа столе
                this.setTranslateX(0);
                this.setTranslateY(0);
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();

            //перелистывание страниц письма вперед,
            // если оно не перемещалось
            if (!isDragAndDrop) {
                this.setNextImageBackground();
            }
            isDragAndDrop = false;

            if (style.length() > 0) {
                this.setStyle(style);
            }
        });
    }

    public void TouchReleased() {
        TouchReleased("");
    }


    //!!! На тачстоле свайпы похоже не работают
    //Метод обработки действий по тач свайпам
    public void ipiTouchSwipe() {

        //перелистывание страниц письма
        this.setOnSwipeLeft(event -> {
            if (!isDragAndDrop) {
                this.setNextImageBackground();
            }
            isDragAndDrop = false;
        });

        this.setOnSwipeRight(event -> {
            if (!isDragAndDrop) {
                this.setPrevImageBackground();
            }
            isDragAndDrop = false;
        });

        this.setOnSwipeUp(event -> {
            if (!isDragAndDrop) {
                this.setFirstImageBackground();
            }
            isDragAndDrop = false;
        });

        this.setOnSwipeDown(event -> {
            if (!isDragAndDrop) {
                this.setLastImageBackground();
            }
            isDragAndDrop = false;
        });
    }

}
