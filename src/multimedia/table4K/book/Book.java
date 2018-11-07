package table4K.book;

import general.FileFormat;
import general.ImagePaneIteration;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.changeRootBackground;
import static table4K.Main4K.mainPane;

public class Book {

    //путь к директории с файлами писем
    private static final String BOOK_PATH = RESOURCES_PATH + "book/";

    public static double BOOK_WIDTH_MAX = 2419;

    //координаты начала и конца области для случайного расположения писем
    public static double BOOK_X = 819;
    public static double BOOK_Y = 446;

    public static void setBookScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_book.jpg");
        mainPane.getChildren().clear();

        ArrayList<File> pageFiles = new ArrayList<>(getFiles(new File(BOOK_PATH), FileFormat.IMAGE));

        BookPane bookTemp = new BookPane(pageFiles, BOOK_WIDTH_MAX, 0);
        bookTemp.setLayoutX(BOOK_X);
        bookTemp.setLayoutY(BOOK_Y);

        mainPane.getChildren().add(bookTemp);

        mainPane.getChildren().add(returnHome());
    }
}
