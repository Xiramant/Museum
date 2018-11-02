package general;

import java.io.File;
import java.util.ArrayList;

import static table.Main.RESOURCES_PATH;

public class FileProcessing {

    //метод получения списка директорий,
    // в которых содержатся файлы,
    // используемые для построения основной сцены
    public static ArrayList<File> getDirKey(final SectionKey sectionKey) {

        ArrayList<File> dirs = new ArrayList<>();

        File mainDir = new File(RESOURCES_PATH + sectionKey.getKeyWord() + "/");

        //Получение массива всех файлов из основного каталога sectionKey
        File mainDirFiles[] = mainDir.listFiles();

        if (mainDirFiles.length == 0) {

            System.out.println("В папке mail не найдено файлов");

            return new ArrayList<>();
        }

        //получение листа файлов с путями к директориям,
        // в которых содержатся файлы для построения основной сцены
        dirs.addAll(getFilterFiles(mainDirFiles, sectionKey.getKeyWord() + "_"));

        return dirs;
    }



    //Получение списка списков файлов изображений или текстовых
    public static ArrayList<ArrayList<File>> getFiles(final ArrayList<File> fileDirs, final FileFormat format) {

        ArrayList<ArrayList<File>> files = new ArrayList<>();

        for (File dir: fileDirs) {

            ArrayList<File> formatFiles = new ArrayList<>();

            File allDirFiles[] = dir.listFiles();

            if (allDirFiles.length == 0) continue;

            formatFiles.addAll(getFilterFiles(allDirFiles, format.getKeyWord() + "_"));

            files.add(formatFiles);
        }

        return files;
    }

    //Получение списка файлов изображений или текстовых из
    // указанной папки
    public static ArrayList<File> getFiles(final File dir, final FileFormat format) {

        ArrayList<File> formatFiles = new ArrayList<>();

        File allDirFiles[] = dir.listFiles();

        if (allDirFiles.length == 0) {
            return null;
        }

        formatFiles.addAll(getFilterFiles(allDirFiles, format.getKeyWord() + "_"));

        return formatFiles;
    }


    //Получение (фильтрация) из полученного списка файлов
    // только тех, которые содержат определенный блок:
    // filter + int{0, 1, ...}
    private static ArrayList<File> getFilterFiles (final File[] inputFilesList, final String filter) {

        if (inputFilesList.length == 0) return new ArrayList<>();

        ArrayList<File> outputFilesList = new ArrayList<>();

        for (int i = 0; ; i++) {

            Boolean endCycle = true;

            for (File file : inputFilesList) {

                if (file.toString().contains(filter + i)) {

                    outputFilesList.add(file);
                    endCycle = false;
                    break;
                }
            }

            if (endCycle) break;
        }

        return outputFilesList;
    }
}
