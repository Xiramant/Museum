package table.model;

import java.io.File;
import java.util.ArrayList;

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
    public ImagePaneSection(final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter.get(0), wMax, hMax);
        this.imageFiles.addAll(imageFilesEnter);
        this.imageFiles.setIndex(0);
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

}
