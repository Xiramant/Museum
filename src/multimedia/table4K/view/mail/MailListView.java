package table4K.view.mail;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import table4K.model.imagePresentation.ImageViewIterationWithMove;

import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.mail.MailData.getMailListBackground;
import static table4K.view.Location.setRandomLocationInArea;
import static table4K.view.MainView.DEBUGGING_RATIO;
import static table4K.view.MainView.setMainScene;



public class MailListView {
    //координаты начала и конца области для первоначального расположения писем
    private static final double MAIL_AREA_BEGIN_X = 2909 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_BEGIN_Y = 441 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_END_X = 3490 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_END_Y = 1128 / DEBUGGING_RATIO;

    //координаты первоначального расположения 4-х писем
    private static final double MAIL_FIRST_X = 993 / DEBUGGING_RATIO;
    private static final double MAIL_FIRST_Y = 916 / DEBUGGING_RATIO;

    private static final double MAIL_SECOND_X = 1670 / DEBUGGING_RATIO;
    private static final double MAIL_SECOND_Y = 448 / DEBUGGING_RATIO;

    private static final double MAIL_THIRD_X = 2374 / DEBUGGING_RATIO;
    private static final double MAIL_THIRD_Y = 861 / DEBUGGING_RATIO;

    private static final double MAIL_FORTH_X = 2000 / DEBUGGING_RATIO;
    private static final double MAIL_FORTH_Y = 1550 / DEBUGGING_RATIO;


    
    public static void setMailListView(final ArrayList<ImageViewIterationWithMove> mailListArg) {

        ArrayList<Node> mailRepresentationList = getMailRepresentationList(mailListArg);
        if (mailRepresentationList.size() > 0) {
            setRandomLocationInArea(mailRepresentationList, getMailInitialLocationArea());
        }

        setLocationIndividualMail(mailListArg);

        setMailListScene(mailRepresentationList);
    }

    private static ArrayList<Node> getMailRepresentationList(final ArrayList<ImageViewIterationWithMove> mailListArg) {
        ArrayList<Node> out = new ArrayList<>();
        for (ImageViewIterationWithMove mail : mailListArg) {
            out.add(mail.getImageIteration().getImageRepresentation());
        }
        return out;
    }

    //Переопределение координат первых четырех писем:
    // первые 3 письма открываются на 2 странице
    // и все 4 письма располагаются по заданным координатам.
    private static void setLocationIndividualMail(final ArrayList<ImageViewIterationWithMove> mailListArg) {

        if (mailListArg.size() == 0) {
            return;
        }
        setMailLocationAndNextPage(mailListArg.get(0), MAIL_FIRST_X, MAIL_FIRST_Y);

        if (mailListArg.size() == 1) {
            return;
        }
        setMailLocationAndNextPage(mailListArg.get(1), MAIL_SECOND_X, MAIL_SECOND_Y);

        if (mailListArg.size() == 2) {
            return;
        }
        setMailLocationAndNextPage(mailListArg.get(2), MAIL_THIRD_X, MAIL_THIRD_Y);

        if (mailListArg.size() == 3) {
            return;
        }
        setMailLocation(mailListArg.get(3), MAIL_FORTH_X, MAIL_FORTH_Y);

    }

    private static void setMailLocation(final ImageViewIterationWithMove mailArg,
                                        final double mailXArg,
                                        final double mailYArg) {
        mailArg.getImageIteration().getImageRepresentation().setLayoutX(mailXArg);
        mailArg.getImageIteration().getImageRepresentation().setLayoutY(mailYArg);
    }

    private static void setMailLocationAndNextPage(final ImageViewIterationWithMove mailArg,
                                                   final double mailXArg,
                                                   final double mailYArg) {
        mailArg.getImageIteration().setNextImage();
        setMailLocation(mailArg, mailXArg, mailYArg);
    }

    private static void setMailListScene(final ArrayList<Node> mailRepresentationListArg) {
        ArrayList<Node> graphicElements = new ArrayList<>();
        //Объединение графического предсталвения писем в группу,
        // перед добавлением в graphicElements,
        // нужно, чтобы они при перемещении и помещении на передний слой командой toFront
        // все-равно оставались ниже кнопки "Перейти к разделам"
        Group mailRepresentationGroup = new Group(mailRepresentationListArg);
        graphicElements.add(mailRepresentationGroup);
        graphicElements.add(returnHome());

        setMainScene(getMailListBackground(), graphicElements);
    }

    private static Rectangle getMailInitialLocationArea() {
        return new Rectangle(MAIL_AREA_BEGIN_X,
                            MAIL_AREA_BEGIN_Y,
                            MAIL_AREA_END_X - MAIL_AREA_BEGIN_X,
                            MAIL_AREA_END_Y - MAIL_AREA_BEGIN_Y);
    }
}
