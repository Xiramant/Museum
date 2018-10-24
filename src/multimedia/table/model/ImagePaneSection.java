package table.model;

import javafx.scene.image.Image;

import java.io.File;

public class ImagePaneSection extends ImagePane {

    //Поле, содержащее список файлов всех фоновых изображений
    // для данного экземпляра панели
    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //Поле с классом TwoPoint,
    // использующееся для хранения параметров расположения панели
    // при ее перемещении методом drag and drop
    private TwoPoint twoPoint= new TwoPoint();

    //геттеры и сеттеры полей
    public ArrayListIndex<File> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(final ArrayListIndex<File> imageFiles) {
        this.imageFiles.addAll(imageFiles);
    }

    public TwoPoint getTwoPoint() {
        return twoPoint;
    }

    public void setTwoPoint(final TwoPoint twoPoint) {
        this.twoPoint = twoPoint;
    }

    //конструктор с передаваемым списком файлов фоновых изображений
    public ImagePaneSection(final ArrayListIndex<File> imageFiles) {

        setImageFiles(imageFiles);
        this.setFirstImageBackground();
    }

    //установка следующего фонового изображения из списка imageFiles
    public void setNextImageBackground() {

        if (imageFiles.hasNextElement()) {
            this.setImageBackground(imageFiles.getNextElement());
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

    //Метод по очистке данных в поле twoPoint
    public void clearTwoPoint() {
        setTwoPoint(new TwoPoint());
    }

}
