package table4K.controller.portfolio;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.portfolio.Portfolio.setPortfolioScene;


public class PortfolioIconController extends IconController {

    public PortfolioIconController(final Node portfolioIconArg) {
        super(portfolioIconArg);
    }

    public void selectSection(){
        setPortfolioScene();
    }

}