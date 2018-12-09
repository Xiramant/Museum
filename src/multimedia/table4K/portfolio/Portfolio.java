package table4K.portfolio;

import general.FileFormat;
import general.SectionKey;
import general.Slider;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.portfolio.PersonalCardPane.PERSONAL_CARD_PANE_HEIGHT_MAX;

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

    private static final double MEDAL_SLIDER_WIDTH = 3250 / debuggingRatio;

    //высота слайдера для медалей
    private static final double MEDAL_SLIDER_HEIGHT = PERSONAL_CARD_PANE_HEIGHT_MAX;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей
    private static final double MEDAL_SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;

    //количество орденов, отображаемых в слайдере
    private static int medalSliderNumber = 6;

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

        ArrayList<Node> heroList = new ArrayList<>();
        for (int i = 0; i < heroImageFiles.size(); i++) {
            heroList.add(new PersonalCardPane(heroImageFiles.get(i), heroTextFiles.get(i)));
        }

        Slider sliderHero = new Slider(MEDAL_SLIDER_WIDTH,
                MEDAL_SLIDER_HEIGHT,
                MEDAL_SUBSCENE_SLIDER_WIDTH,
                medalSliderNumber,
                heroList);

        sliderHero.setLayoutX(PORTFOLIO_SLIDER_HERO_X);
        sliderHero.setLayoutY(PORTFOLIO_SLIDER_HERO_Y);

        mainPane.getChildren().addAll(sliderHero, returnHome());
    }
}
