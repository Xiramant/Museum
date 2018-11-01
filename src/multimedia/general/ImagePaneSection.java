package general;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;

import java.io.File;
import java.util.ArrayList;

public class ImagePaneSection extends ImagePane {

    //Поле, содержащее список файлов всех фоновых изображений
    // для данного экземпляра панели
    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //флаг перемещения ImagePaneSection
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
    public ImagePaneSection(final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter.get(0), wMax, hMax);
        this.imageFiles.addAll(imageFilesEnter);
        this.imageFiles.setIndex(0);

        mouseEvent();
        touchEvent();
    }

    //конструктор с передаваемым списком файлов фоновых изображений
    // без задания ограничений на максимальный размер
    public ImagePaneSection(final ArrayList<File> imageFiles) {
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
    private void mouseEvent() {

        //перемещение письма
        this.setOnMousePressed(mouseEvent -> {
            this.toFront();
            this.setCursor(Cursor.MOVE);

            this.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            this.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });

        this.setOnMouseDragged(mouseEvent -> {

            this.getRelocationCoordinates().setXDelta(mouseEvent.getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(mouseEvent.getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        this.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
            );
            this.clearRelocationCoordinates();
        });


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
    private void touchEvent() {

        //перемещение письма
        this.setOnTouchPressed(event -> {
            this.toFront();
            this.setCursor(Cursor.MOVE);

            this.getRelocationCoordinates().setXBegin(event.getTouchPoint().getSceneX());
            this.getRelocationCoordinates().setYBegin(event.getTouchPoint().getSceneY());
        });

        this.setOnTouchMoved(event -> {

            this.getRelocationCoordinates().setXDelta(event.getTouchPoint().getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(event.getTouchPoint().getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        this.setOnTouchReleased(event -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
            );
            this.clearRelocationCoordinates();

            //перелистывание страниц письма вперед,
            // если оно не перемещалось
            if (!isDragAndDrop) {
                this.setNextImageBackground();
            }
            isDragAndDrop = false;
        });

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
