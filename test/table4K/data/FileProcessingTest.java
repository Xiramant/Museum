package table4K.data;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static table4K.data.FileProcessing.getFilterFiles;



class FileProcessingTest {

    @Test
    void getFilterFilesTest() throws IOException {
        String dirPath = "C://MuseumTest/";
        File dirForTest = new File(dirPath);
        if (!dirForTest.mkdirs()) {
            System.out.println("Не удалось создать директорию для тестирования");
        }

        File file0 = new File(dirPath + "book_img_0.jpg");
        createFileWithMessage(file0);
        File file1 = new File(dirPath + "book_img2.jpg");
        createFileWithMessage(file1);
        File file2 = new File(dirPath + "book_image_5.jpg");
        createFileWithMessage(file2);
        File file3 = new File(dirPath + "book_img_5.jpg");
        createFileWithMessage(file3);
        File file4 = new File(dirPath + "book_text_0.text");
        createFileWithMessage(file4);

        ArrayList<File> expectedValue = new ArrayList<>();
        expectedValue.add(file0);
        expectedValue.add(file3);

        ArrayList<File> actualValue = new ArrayList<>();
        if (dirForTest.listFiles() != null) {
            actualValue = getFilterFiles(dirForTest.listFiles(), "img_");
        }
        else {
            System.out.println("Список файлов директории для тестирования пуст");
        }

        deleteFileWithMessage(file0);
        deleteFileWithMessage(file1);
        deleteFileWithMessage(file2);
        deleteFileWithMessage(file3);
        deleteFileWithMessage(file4);
        deleteFileWithMessage(dirForTest);

        assertEquals(expectedValue, actualValue);
    }

    private void createFileWithMessage(final File fileArg) throws IOException{
        if (!fileArg.createNewFile()) {
            System.out.println("Не удалось создать файл: " + fileArg);
        }
    }

    private void deleteFileWithMessage(final File fileArg) {
        if (!fileArg.delete()) {
            System.out.println("Не удалось удалить директорию/файл: " + fileArg);
        }
    }
}