package table4K.book;

import general.ImagePaneIteration;

import java.io.File;
import java.util.ArrayList;

public class BookPane extends ImagePaneIteration {

    public BookPane (final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter, wMax, hMax);

        ipiMouseClicked();
        ipiTouch();
    }
}
