package table4K.view.book;

import javafx.scene.image.ImageView;

import static table4K.view.MainView.DEBUGGING_RATIO;



public class Book {

    private static final double BOOK_WIDTH_MAX = 2419 / DEBUGGING_RATIO;

    private static final double BOOK_X = 819 / DEBUGGING_RATIO;
    private static final double BOOK_Y = 446 / DEBUGGING_RATIO;



    public static void setBookView(final ImageView bookViewArg) {
        bookViewArg.setFitWidth(BOOK_WIDTH_MAX);
        bookViewArg.setPreserveRatio(true);
        bookViewArg.setLayoutX(BOOK_X);
        bookViewArg.setLayoutY(BOOK_Y);
    }

}