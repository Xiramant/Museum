package table4K.portfolio;

import general.FileFormat;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.TextProcessing.readingFirstStokeFromFile;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.changeRootBackground;
import static table4K.Main4K.mainPane;

public class Portfolio {

    private static final SectionKey PORTFOLIO_KEY = SectionKey.PORTFOLIO;

    //Личные дела героев Советского союза:
    //внешний лист - личное дело;
    //внутренний лист - фото из личного дела.
    private static ArrayList<ArrayList<File>> heroImageFiles;

    //Личные дела героев Советского союза:
    //внешний лист - личное дело;
    //внутренний лист - текст из личного дела.
    private static ArrayList<ArrayList<File>> heroTextFiles;

    //Личные дела ветеранов ВОВ:
    //внешний лист - личное дело;
    //внутренний лист - фото из личного дела.
    private static ArrayList<ArrayList<File>> veteranImageFiles;

    //Личные дела ветеранов ВОВ:
    //внешний лист - личное дело;
    //внутренний лист - текст из личного дела.
    private static ArrayList<ArrayList<File>> veteranTextFiles;



    public static double PORTFOLIO_HERO_X = 960;

    public static double PORTFOLIO_HERO_Y = 860;

    public static double PORTFOLIO_VETERAN_X = 2560;

    public static double PORTFOLIO_VETERAN_Y = 950;

    public static void setPortfolioScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_portfolio.jpg");
        mainPane.getChildren().clear();

        //лист директорий героев Советского Союза,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileHeroDirs = new ArrayList<>(getDirKey(PORTFOLIO_KEY, "hero"));

        heroImageFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.IMAGE));
        heroTextFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.TEXT));

//        //Список строк героев Советского Союза
//        // с ФИО и г.р. для личной карточки
//        ArrayList<String> personalCardInfoList = new ArrayList<>();
//        for (int i = 0; i < fileHeroDirs.size(); i++) {
//            personalCardInfoList.add(readingFirstStokeFromFile(heroTextFiles.get(i).get(0)));
//        }

        PortfolioSlide temp = new PortfolioSlide(heroImageFiles, heroTextFiles);
        temp.setLayoutX(600/2);
        temp.setLayoutY(1100/2);
        mainPane.getChildren().addAll(temp, returnHome());



//        PortfolioPane hero = new PortfolioPane(new File(RESOURCES_PATH + "portfolio/portfolio_hero.png"));
//        PortfolioPane veteran = new PortfolioPane(new File(RESOURCES_PATH + "portfolio/portfolio_veteran.png"));
//
//        hero.setLayoutX(PORTFOLIO_HERO_X);
//        hero.setLayoutY(PORTFOLIO_HERO_Y);
//
//        veteran.setLayoutX(PORTFOLIO_VETERAN_X);
//        veteran.setLayoutY(PORTFOLIO_VETERAN_Y);
//
//        mainPane.getChildren().addAll(hero, veteran, returnHome());

    }
}
