package table4K.portfolio;

import java.io.File;

import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.changeRootBackground;
import static table4K.Main4K.mainPane;

public class Portfolio {



    public static double PORTFOLIO_HERO_X = 960;

    public static double PORTFOLIO_HERO_Y = 860;

    public static double PORTFOLIO_VETERAN_X = 2560;

    public static double PORTFOLIO_VETERAN_Y = 950;

    public static void setPortfolioScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_portfolio.jpg");
        mainPane.getChildren().clear();

        PortfolioPane hero = new PortfolioPane(new File(RESOURCES_PATH + "portfolio/portfolio_hero.png"));
        PortfolioPane veteran = new PortfolioPane(new File(RESOURCES_PATH + "portfolio/portfolio_veteran.png"));

        hero.setLayoutX(PORTFOLIO_HERO_X);
        hero.setLayoutY(PORTFOLIO_HERO_Y);

        veteran.setLayoutX(PORTFOLIO_VETERAN_X);
        veteran.setLayoutY(PORTFOLIO_VETERAN_Y);

        mainPane.getChildren().addAll(hero, veteran, returnHome());

    }
}
