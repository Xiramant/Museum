package table4K.data;

import general.FileFormat;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;



public class FileProcessing {

    //получение изображений с названием определенного формата из указанной папки
    public static ArrayList<Image> getImages(final File dirArg) {
        ArrayList<File> imageFiles = getFiles(dirArg,  FileFormat.IMAGE);
        ArrayList<Image> images = new ArrayList<>();
        for (File imageFile: imageFiles) {
            images.add(new Image("file:///" + imageFile));
        }

        return images;
    }

    //Получение списка файлов из указанной директории (первый аргумент)
    // определенного типа (второй аргумент)
    private static ArrayList<File> getFiles(final File dirArg, final FileFormat formatArg) {
        File[] allDirFiles = dirArg.listFiles();
        if (allDirFiles.length == 0) {
            return null;
        }

        return getFilterFiles(allDirFiles, formatArg.getKeyWord() + "_");
    }

    //Фильтрация файлов по переданному формату
    private static ArrayList<File> getFilterFiles (final File[] inputFilesListArg, final String filterArg) {
        if (inputFilesListArg.length == 0) {
            return null;
        }

        ArrayList<File> outputFilesList = new ArrayList<>();
        for (File inputFile: inputFilesListArg) {
            if (inputFile.toString().contains(filterArg)) {
                outputFilesList.add(inputFile);
            }
        }

        return outputFilesList;
    }

}