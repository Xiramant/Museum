package table4K.mail;

import general.ImagePaneIteration;
import general.RestrictionCoordinates;

import java.io.File;
import java.util.ArrayList;

import static table4K.mail.Mail.*;

public class MailPane extends ImagePaneIteration {

    private static final String SHADOW_NORMAL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);";
    private static final String SHADOW_DRAG_AND_DROP = "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);";

    public MailPane (final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter, wMax, hMax);

        //!!! setRestrCoor должно определяться
        // до вызова обработки событий щелчка мыши и тача
        this.setRestrCoor(MAIL_LEFT, MAIL_TOP, MAIL_RIGHT, MAIL_BOTTOM);

        this.ipiMouseDragAndDrop();
        this.ipiMouseClicked();
        this.ipiTouch();

        this.setStyle(SHADOW_NORMAL);

        setCenterPaneFlag(true);
    }

    @Override
    public void ipiMouseDragAndDrop() {
        super.mousePressed();
        super.mouseDragged(SHADOW_DRAG_AND_DROP);
        super.mouseReleased(SHADOW_NORMAL);
    }

    @Override
    public void ipiTouch() {
        super.TouchPressed();
        super.TouchMoved(SHADOW_DRAG_AND_DROP);
        super.TouchReleased(SHADOW_NORMAL);
    }

}
