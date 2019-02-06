package table4K.model.book;

import table4K.model.imagePresentation.ImageViewIteration;

import static table4K.data.book.BookData.getBookPages;
import static table4K.view.book.BookView.setBookView;



public class Book {

    public static void setBookScene() {
        ImageViewIteration book = new ImageViewIteration(getBookPages());
        setBookView(book.getImageRepresentation());
    }

}
