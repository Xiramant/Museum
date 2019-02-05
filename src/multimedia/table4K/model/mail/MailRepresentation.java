package table4K.model.mail;

import javafx.scene.image.Image;
import table4K.model.imagePresentation.ImageViewIterationWithMove;
import table4K.model.imagePresentation.Restriction;

import java.util.ArrayList;

import static table4K.view.MainView.*;


public class MailRepresentation extends ImageViewIterationWithMove {

    private static final double MAIL_WIDTH_MAX = 600 / DEBUGGING_RATIO;

    //ограничения на расположение писем
    private static final double MAIL_RESTRICTION_X_BEGIN = 900 / DEBUGGING_RATIO;
    private static final double MAIL_RESTRICTION_Y_BEGIN = 370 / DEBUGGING_RATIO;
    //DEBUGGING_RATIO для MAIL_RESTRICTION_X_END и MAIL_RESTRICTION_Y_END не используется, т.к.
    // TABLE_WIDTH и TABLE_HEIGHT уже определены с помощью DEBUGGING_RATIO
    private static final double MAIL_RESTRICTION_X_END = TABLE_WIDTH;
    private static final double MAIL_RESTRICTION_Y_END = TABLE_HEIGHT;


    public MailRepresentation(final ArrayList<Image> imageFilesEnter) {
        super(imageFilesEnter, getMailRestriction());

        this.getImageIteration().getImageRepresentation().setFitWidth(MAIL_WIDTH_MAX);
        this.getImageIteration().getImageRepresentation().setPreserveRatio(true);
    }

    private static Restriction getMailRestriction() {
        return new Restriction.Builder()
                                        .xBegin(MAIL_RESTRICTION_X_BEGIN)
                                        .yBegin(MAIL_RESTRICTION_Y_BEGIN)
                                        .xEnd(MAIL_RESTRICTION_X_END)
                                        .yEnd(MAIL_RESTRICTION_Y_END)
                                        .build();
    }

}