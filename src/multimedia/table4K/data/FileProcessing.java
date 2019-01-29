package table4K.data;

import general.FileFormat;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;



public class FileProcessing {

    //получение изображений с названием определенного формата из указанной папки
    public static ArrayList<Image> getImages(final File dirArg) {
        File[] imageFiles = getFiles(dirArg,  FileFormat.IMAGE);
        ArrayList<Image> images = new ArrayList<>();
        
        for (File imageFile: imageFiles) {
            try {
                images.add(new Image(imageFile.toURI().toURL().toString()));
            }
            catch (MalformedURLException e) {
                System.out.println("Ошибка преобразования файла в путь");
                e.printStackTrace();
            }
        }

        return images;
    }

    //Получение списка файлов из указанной директории (первый аргумент)
    // определенного типа (второй аргумент)
    private static File[] getFiles(final File dirArg, final FileFormat formatArg) {
        return dirArg.listFiles((dirPath, fileName) -> fileName.contains(formatArg.getKeyWord() + "_"));
    }

}