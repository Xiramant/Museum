package table4K.portfolio;

import general.FileFormat;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

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

    //отступы слайдера для выбора личного дела героя Советского Союза
    public static double PORTFOLIO_SLIDER_HERO_X = 800 / debuggingRatio;
    public static double PORTFOLIO_SLIDER_HERO_Y = 970 / debuggingRatio;

    public static void setPortfolioScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_portfolio_initial.jpg");
        mainPane.getChildren().clear();

        //лист директорий героев Советского Союза,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileHeroDirs = new ArrayList<>(getDirKey(PORTFOLIO_KEY, "hero"));

        heroImageFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.IMAGE));
        heroTextFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.TEXT));

        PortfolioSlider sliderHero = new PortfolioSlider(heroImageFiles, heroTextFiles);
        sliderHero.setLayoutX(PORTFOLIO_SLIDER_HERO_X);
        sliderHero.setLayoutY(PORTFOLIO_SLIDER_HERO_Y);

        mainPane.getChildren().addAll(sliderHero, returnHome());

    }
}
