package table4K.view.mail;

import javafx.scene.image.ImageView;
import table4K.model.imagePresentation.Restriction;

import static table4K.view.MainView.*;

public class MailRepresentationView {

    private static final double MAIL_WIDTH_MAX = 600 / DEBUGGING_RATIO;

    private static final String MAIL_SHADOW_DEFAULT = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);";
    private static final String MAIL_SHADOW_MOVE = "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);";

    //ограничения на расположение писем
    private static final double MAIL_RESTRICTION_X_BEGIN = 900 / DEBUGGING_RATIO;
    private static final double MAIL_RESTRICTION_Y_BEGIN = 370 / DEBUGGING_RATIO;
    //DEBUGGING_RATIO для MAIL_RESTRICTION_X_END и MAIL_RESTRICTION_Y_END не используется, т.к.
    // TABLE_WIDTH и TABLE_HEIGHT уже определены с помощью DEBUGGING_RATIO
    private static final double MAIL_RESTRICTION_X_END = TABLE_WIDTH;
    private static final double MAIL_RESTRICTION_Y_END = TABLE_HEIGHT;



    public static String getMailShadowDefault() {
        return MAIL_SHADOW_DEFAULT;
    }

    public static String getMailShadowMove() {
        return MAIL_SHADOW_MOVE;
    }

    public static Restriction getMailRestriction() {
        return new Restriction.Builder()
                .xBegin(MAIL_RESTRICTION_X_BEGIN)
                .yBegin(MAIL_RESTRICTION_Y_BEGIN)
                .xEnd(MAIL_RESTRICTION_X_END)
                .yEnd(MAIL_RESTRICTION_Y_END)
                .build();
    }



    public static void setMailView(final ImageView mailViewArg) {
        mailViewArg.setFitWidth(MAIL_WIDTH_MAX);
        mailViewArg.setPreserveRatio(true);
    }

}
