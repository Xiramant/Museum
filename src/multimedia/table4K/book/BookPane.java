package table4K.book;

import general.ImagePaneIteration;
import general.ImageViewIteration;

import java.io.File;
import java.util.ArrayList;

public class BookPane extends ImageViewIteration {

    public BookPane (final ArrayList<File> imageFilesEnter, final double wMax) {
        super(imageFilesEnter);

        this.setFitWidth(wMax);
        this.setPreserveRatio(true);

        setMoveDisabled();
    }
}

