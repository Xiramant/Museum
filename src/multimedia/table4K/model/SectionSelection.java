package table4K.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import table4K.controller.IconController;
import java.util.ArrayList;

import static table4K.controller.IconController.*;
import static table4K.data.RootPaneBackgroundData.getSectionSelectionBackground;
import static table4K.view.Icon.*;
import static table4K.view.MainView.setRootPaneScene;


//модель окна Выбор раздела
public class SectionSelection {

    public static void sectionSelectionInitialize() {

        Node bookIcon = getBook();
        IconController bookController = getBookIconController(bookIcon);

        Node filmIcon = getFilm();
        IconController filmController = getFilmIconController(filmIcon);

        Node mailIcon = getMail();
        IconController mailController = getMailIconController(mailIcon);

        Node mapIcon = getMapIcon();
        IconController mapIconController = getMapIconController(mapIcon);

        Node medalIcon = getMedal();
        IconController medalController = getMedalIconController(medalIcon);

        Node portfolioIcon = getPortfolio();
        IconController portfolioController = getPortfolioIconController(portfolioIcon);

        Node quizIcon = getQuiz();
        IconController quizController = getQuizIconController(quizIcon);


        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(bookIcon);
        graphicElements.add(filmIcon);
        graphicElements.add(mailIcon);
        graphicElements.add(mapIcon);
        graphicElements.add(medalIcon);
        graphicElements.add(portfolioIcon);
        graphicElements.add(quizIcon);

        Image background = getSectionSelectionBackground();


        setRootPaneScene(background, graphicElements);
    }

}
