package table4K.model.mail;

import javafx.scene.image.Image;
import java.util.ArrayList;

import table4K.model.imagePresentation.ImageViewIteration;
import table4K.model.imagePresentation.ImageViewIterationWithMove;

import static table4K.view.mail.MailRepresentationView.*;


public class MailRepresentation {

    public static ImageViewIterationWithMove getMail(final ArrayList<Image> imageFilesEnterArg) {
        ImageViewIterationWithMove mail = new ImageViewIterationWithMove.Builder(new ImageViewIteration(imageFilesEnterArg))
                                                                        .restrictionArea(getMailRestriction())
                                                                        .styleDefault(getMailShadowDefault())
                                                                        .styleMove(getMailShadowMove())
                                                                        .build();

        setMailView(mail.getImageIteration().getImageRepresentation());

        return mail;
    }

}