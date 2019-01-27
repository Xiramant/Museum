package table4K.book;

import general.FileFormat;
import table4K.view.MainView;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class Book {

    //путь к директории с файлами писем
    private static final String BOOK_PATH = RESOURCES_PATH + "book/";

    private static final double BOOK_WIDTH_MAX = 2419 / MainView.DEBUGGING_RATIO;

    //координаты начала и конца области для случайного расположения писем
    private static final double BOOK_X = 819 / MainView.DEBUGGING_RATIO;
    private static final double BOOK_Y = 446 / MainView.DEBUGGING_RATIO;

    public static void setBookScene() {

        MainView.changeRootPaneBackground(RESOURCES_PATH + "table_4K_book.jpg");
        MainView.rootPane.getChildren().clear();

        ArrayList<File> pageFiles = new ArrayList<>(getFiles(new File(BOOK_PATH), FileFormat.IMAGE));

        BookPane bookTemp = new BookPane(pageFiles, BOOK_WIDTH_MAX);
        bookTemp.setLayoutX(BOOK_X);
        bookTemp.setLayoutY(BOOK_Y);

        MainView.rootPane.getChildren().addAll(bookTemp, returnHome());
    }
}
