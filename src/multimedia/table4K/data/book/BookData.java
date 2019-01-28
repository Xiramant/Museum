package table4K.data.book;

import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.getImages;



public class BookData {

    //путь к директории с изображениями страниц книги
    private static final String BOOK_PATH = RESOURCES_PATH + "book/";



    public static ArrayList<Image> getBookPages() {
        return getImages(new File(BOOK_PATH));
    }

}