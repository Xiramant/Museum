package table4K.model.mail;

import javafx.scene.image.Image;
import table4K.model.imagePresentation.ImageViewIterationWithMove;

import java.util.ArrayList;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class MailRepresentation extends ImageViewIterationWithMove {

    private static final double MAIL_WIDTH_MAX = 600 / DEBUGGING_RATIO;



    public MailRepresentation(final ArrayList<Image> imageFilesEnter) {
        super(imageFilesEnter);

        this.getImageIteration().getImageRepresentation().setFitWidth(MAIL_WIDTH_MAX);
        this.getImageIteration().getImageRepresentation().setPreserveRatio(true);
    }

}