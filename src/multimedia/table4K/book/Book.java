package table4K.book;

import general.FileFormat;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class Book {

    //путь к директории с файлами писем
    private static final String BOOK_PATH = RESOURCES_PATH + "book/";

    private static final double BOOK_WIDTH_MAX = 2419 / DEBUGGING_RATIO;

    //координаты начала и конца области для случайного расположения писем
    private static final double BOOK_X = 819 / DEBUGGING_RATIO;
    private static final double BOOK_Y = 446 / DEBUGGING_RATIO;

    public static void setBookScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_book.jpg");
        mainPane.getChildren().clear();

        ArrayList<File> pageFiles = new ArrayList<>(getFiles(new File(BOOK_PATH), FileFormat.IMAGE));

        BookPane bookTemp = new BookPane(pageFiles, BOOK_WIDTH_MAX);
        bookTemp.setLayoutX(BOOK_X);
        bookTemp.setLayoutY(BOOK_Y);

        mainPane.getChildren().addAll(bookTemp, returnHome());
    }
}
