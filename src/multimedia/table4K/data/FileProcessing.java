package table4K.data;

import general.FileFormat;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static table4K.Main4K.RESOURCES_PATH;



public class FileProcessing {

    private static final String FILE_NAME_EXTENSION_SEPARATOR = ".";



    //получение изображений с названием определенного формата из указанной папки
    public static ArrayList<Image> getImages(final File dirArg) {
        File[] imageFiles = getFilesWithCertainName(dirArg,  FileFormat.IMAGE);
        ArrayList<Image> images = new ArrayList<>();
        
        for (File imageFile: imageFiles) {
            images.add(new Image(imageFile.toURI().toString()));
        }

        return images;
    }

    //Получение списка файлов из указанной директории (первый аргумент)
    // с названием, которое содержит ключевое слово (второй аргумент)
    private static File[] getFilesWithCertainName(final File dirArg, final FileFormat formatArg) {
        return dirArg.listFiles((dirPath, fileName) -> fileName.contains(formatArg.getKeyWord() + "_"));
    }

    public static ArrayList<File> getFilesCertainType(final File dirArg, final FileType fileTypeArg) {
        ArrayList<File> out = new ArrayList<>();
        for (String fileFormat: fileTypeArg.getFileFormats()) {
            Collections.addAll(out, getFilesCertainFormat(dirArg, fileFormat));
        }
        return out;
    }

    private static File[] getFilesCertainFormat(final File dirArg, final String fileFormatArg){
        return dirArg.listFiles((dirPath, fileName) -> fileName.endsWith(fileFormatArg));
    }

    public static String getName(final File fileArg) {
        String name = fileArg.getName();
        return name.substring(0, name.lastIndexOf(FILE_NAME_EXTENSION_SEPARATOR));
    }



    //Получение изображение из части пути не включающего RESOURCES_PATH
    public static Image createImage(final String pathArg) {
        return new Image(new File(pathArg).toURI().toString());
    }
}