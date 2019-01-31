package table4K.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import table4K.controller.IconController;
import java.util.ArrayList;

import static table4K.controller.IconController.*;
import static table4K.data.RootPaneBackgroundData.getSectionSelectionBackground;
import static table4K.view.Icon.*;
import static table4K.view.MainView.setMainScene;


//модель окна Выбор раздела
public class SectionSelection {

    public static void setSectionSelection() {

        Node bookIcon = getBookIcon();
        IconController bookController = getBookIconController(bookIcon);

        Node videoIcon = getVideoIcon();
        IconController videoController = getVideoIconController(videoIcon);

        Node mailIcon = getMailIcon();
        IconController mailController = getMailIconController(mailIcon);

        Node mapIcon = getMapIcon();
        IconController mapIconController = getMapIconController(mapIcon);

        Node medalIcon = getMedalIcon();
        IconController medalController = getMedalIconController(medalIcon);

        Node portfolioIcon = getPortfolioIcon();
        IconController portfolioController = getPortfolioIconController(portfolioIcon);

        Node quizIcon = getQuizIcon();
        IconController quizController = getQuizIconController(quizIcon);


        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(bookIcon);
        graphicElements.add(videoIcon);
        graphicElements.add(mailIcon);
        graphicElements.add(mapIcon);
        graphicElements.add(medalIcon);
        graphicElements.add(portfolioIcon);
        graphicElements.add(quizIcon);

        Image background = getSectionSelectionBackground();


        setMainScene(background, graphicElements);
    }

}
