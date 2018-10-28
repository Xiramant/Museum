package table.model;

import java.io.File;

public class ImagePaneSection extends ImagePane {

    //Поле, содержащее список файлов всех фоновых изображений
    // для данного экземпляра панели
    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    //Поле для хранения параметров расположения панели
    // при ее перемещении методом drag and drop
    private RelocationCoordinates relocationCoordinates = new RelocationCoordinates();

    //геттеры и сеттеры полей
    public ArrayListIndex<File> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(final ArrayListIndex<File> imageFiles) {
        this.imageFiles.addAll(imageFiles);
    }

    public RelocationCoordinates getRelocationCoordinates() {
        return relocationCoordinates;
    }

    public void setRelocationCoordinates(final RelocationCoordinates relocationCoordinates) {
        this.relocationCoordinates = relocationCoordinates;
    }


    //конструктор с передаваемым списком файлов фоновых изображений
    // и ограничениями на максимальный размер
    public ImagePaneSection(final ArrayListIndex<File> imageFiles, final double wMax, final double hMax) {
        super(imageFiles.get(0), wMax, hMax);
        setImageFiles(imageFiles);
        this.imageFiles.setIndex(0);
    }

    //конструктор с передаваемым списком файлов фоновых изображений
    // без задания ограничений на максимальный размер
    public ImagePaneSection(final ArrayListIndex<File> imageFiles) {
        this(imageFiles, 0, 0);
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

    public int getBackgroundIndex() {
        return imageFiles.getCurrentIndex();
    }

    //Метод по очистке данных в поле relocationCoordinates
    public void clearTwoPoint() {
        setRelocationCoordinates(new RelocationCoordinates());
    }

}
