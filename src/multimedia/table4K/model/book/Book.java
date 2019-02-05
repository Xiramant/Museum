package table4K.model.book;

import table4K.model.imagePresentation.ImageViewIteration;
import javafx.scene.Node;
import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.RootPaneBackgroundData.getBookBackground;
import static table4K.data.book.BookData.getBookPages;
import static table4K.view.MainView.setMainScene;
import static table4K.view.book.Book.setBookView;



public class Book {

    public static void setBookScene() {
        ImageViewIteration book = new ImageViewIteration(getBookPages());

        setBookView(book.getImageRepresentation());

        setBookIntoRootPaneScene(book.getImageRepresentation());
    }

    private static void setBookIntoRootPaneScene(final Node bookPagesArg) {
        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(bookPagesArg);
        graphicElements.add(returnHome());

        setMainScene(getBookBackground(), graphicElements);
    }

}
