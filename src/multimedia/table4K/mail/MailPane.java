package table4K.mail;

import general.ImagePaneIteration;
import general.ImageViewIteration;
import general.RestrictionCoordinates;

import java.io.File;
import java.util.ArrayList;

import static table4K.mail.Mail.*;

public class MailPane extends ImageViewIteration {

    private static final String SHADOW_NORMAL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);";
    private static final String SHADOW_DRAG_AND_DROP = "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);";

    public MailPane (final ArrayList<File> imageFilesEnter, final double wMax) {
        super(imageFilesEnter);

        this.setFitWidth(wMax);
        this.setPreserveRatio(true);

        //!!! setRestrCoor должно определяться
        // до вызова обработки событий щелчка мыши и тача
        this.setRestrCoor(MAIL_LEFT, MAIL_TOP, MAIL_RIGHT, MAIL_BOTTOM);

        this.ivcMouseEvent();
        this.ivcTouchEvent();

        this.setStyle(SHADOW_NORMAL);

        setFlagCenterImageView(true);
    }

    @Override
    protected void ivcMouseEvent() {
        super.ivcMousePressed();
        super.ivcMouseDragged(SHADOW_DRAG_AND_DROP);
        super.ivcMouseReleased(SHADOW_NORMAL);
    }

    @Override
    protected void ivcTouchEvent() {
        super.ivcTouchPressed();
        super.ivcTouchMoved(SHADOW_DRAG_AND_DROP);
        this.ivcTouchReleased(SHADOW_NORMAL);
    }

}
