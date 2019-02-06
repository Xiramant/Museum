package table4K.view.book;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.RootPaneBackgroundData.getBookBackground;
import static table4K.view.MainView.DEBUGGING_RATIO;
import static table4K.view.MainView.setMainScene;


public class BookView {

    private static final double BOOK_WIDTH_MAX = 2419 / DEBUGGING_RATIO;

    private static final double BOOK_X = 819 / DEBUGGING_RATIO;
    private static final double BOOK_Y = 446 / DEBUGGING_RATIO;



    public static void setBookView(final ImageView bookViewArg) {
        setPageParameters(bookViewArg);
        setBookIntoRootPaneScene(bookViewArg);
    }

    private static void setPageParameters(final ImageView bookViewArg) {
        bookViewArg.setFitWidth(BOOK_WIDTH_MAX);
        bookViewArg.setPreserveRatio(true);
        bookViewArg.setLayoutX(BOOK_X);
        bookViewArg.setLayoutY(BOOK_Y);
    }

    private static void setBookIntoRootPaneScene(final Node bookPagesArg) {
        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(bookPagesArg);
        graphicElements.add(returnHome());

        setMainScene(getBookBackground(), graphicElements);
    }

}