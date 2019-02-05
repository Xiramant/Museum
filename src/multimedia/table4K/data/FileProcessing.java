package table4K.data;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;



public class FileProcessing {

    private static final String FILE_NAME_EXTENSION_SEPARATOR = ".";



    //получение изображений с названием определенного формата из указанной папки
    public static ArrayList<Image> getImages(final File dirArg) {
        ArrayList<File> imageFiles = getFilesCertainType(dirArg,  FileType.IMAGE);
        ArrayList<Image> out = new ArrayList<>();
        
        for (File imageFile: imageFiles) {
            out.add(new Image(imageFile.toURI().toString()));
        }

        return out;
    }

    //Получение списка файлов из указанной директории (первый аргумент)
    // с названием, которое содержит ключевое слово (второй аргумент)
    public static File[] getFilesWithCertainName(final File dirArg, final String filterArg) {
        return dirArg.listFiles((dirPath, fileName) -> fileName.contains(filterArg));
    }

    public static ArrayList<File> getFilesCertainType(final File dirArg, final FileType fileTypeArg) {
        ArrayList<File> out = new ArrayList<>();
        for (String fileFormat: fileTypeArg.getFileFormats()) {
            Collections.addAll(out, getFilesCertainFormat(dirArg, fileFormat));
        }
        return out;
    }

    //От метода "getFilesWithCertainName" отличается тем,
    // что вхождение фильтра проеряется в КОНЦЕ названия.
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