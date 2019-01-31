package table4K.controller.book;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.model.book.Book.setBookScene;


public class BookIconController extends IconController {

    public BookIconController(final Node bookIconArg) {
        super(bookIconArg);
    }

    public void action(){
        setBookScene();
    }

}