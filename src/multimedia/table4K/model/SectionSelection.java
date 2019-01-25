package table4K.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import table4K.Main4K;
import table4K.controller.IconController;
import table4K.ui.IIcon;
import table4K.ui.Icon;
import java.util.ArrayList;

import static table4K.ui.MainView.setRootPaneScene;


//модель окна Выбор раздела
public class SectionSelection {

    public static void sectionSelectionInitialize() {

        IIcon mapIcon = Icon.getMap();
        IconController mapController = new IconController(mapIcon);

        IIcon mailIcon = Icon.getMail();
        IconController mailController = new IconController(mailIcon);

        IIcon portfolioIcon = Icon.getPortfolio();
        IconController portfolioController = new IconController(portfolioIcon);

        IIcon medalIcon = Icon.getMedal();
        IconController medalController = new IconController(medalIcon);

        IIcon bookIcon = Icon.getBook();
        IconController bookController = new IconController(bookIcon);

        IIcon quizIcon = Icon.getQuiz();
        IconController quizController = new IconController(quizIcon);

        IIcon filmIcon = Icon.getFilm();
        IconController filmController = new IconController(filmIcon);

        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(mapIcon.getIconImage());
        graphicElements.add(mailIcon.getIconImage());
        graphicElements.add(portfolioIcon.getIconImage());
        graphicElements.add(medalIcon.getIconImage());
        graphicElements.add(bookIcon.getIconImage());
        graphicElements.add(quizIcon.getIconImage());
        graphicElements.add(filmIcon.getIconImage());

        Image background = new Image("file:///" + Main4K.RESOURCES_PATH + "table_with_lamp.jpg");

        setRootPaneScene(background, graphicElements);
    }

}
