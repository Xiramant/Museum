package table4K;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static general.SectionKey.*;
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static table4K.film.Film.setFilmScene;
import static table4K.mail.Mail.setMailScene;
import static table4K.book.Book.setBookScene;
import static table4K.map.Map.setMapScene;
import static table4K.medal.Medal.setOrdenScene;
import static table4K.portfolio.Portfolio.setPortfolioScene;
import static table4K.quiz.Quiz.setQuizScene;

public class Main4K extends Application{

    //величина на которые уменьшаются все размеры,
    // чтобы программу можно было дебажить на обычном мониторе
    public static final double debuggingRatio = 2.133;

    //путь к директории с файлами
    public static final String RESOURCES_PATH = "C://museumResources/";

    //время задержки после тача
    public static final int TOUCH_TIMEOUT = 1000;

    private static BorderPane root;

    //Основная панель с содержимым
    public static Pane mainPane;

    //Размеры интерактивного стола
    public static double TABLE_WIDTH = 4096 / debuggingRatio;
    public static double TABLE_HEIGHT = 2160 / debuggingRatio;

    //Иконка раздела Карты
    private static final String MAP_URL = "file:///" + RESOURCES_PATH + "icon/map_icon.png";
    private static final double MAP_ICON_WIDTH = 1547 / debuggingRatio;
    private static final double MAP_ICON_HEIGHT = 972 / debuggingRatio;
    private static final double MAP_ICON_X = 0 / debuggingRatio;
    private static final double MAP_ICON_Y = 107 / debuggingRatio;
    private static final String MAP_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";

    //Иконка раздела Письма
    private static final String MAIL_URL = "file:///" + RESOURCES_PATH + "icon/mail_icon.png";
    private static final double MAIL_ICON_WIDTH = 579 / debuggingRatio;
    private static final double MAIL_ICON_HEIGHT = 693 / debuggingRatio;
    private static final double MAIL_ICON_X = 748 / debuggingRatio;
    private static final double MAIL_ICON_Y = 1242 / debuggingRatio;
    private static final String MAIL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";

    //Иконка раздела Личные дела
    private static final String PORTFOLIO_URL = "file:///" + RESOURCES_PATH + "icon/portfolio_icon.png";
    private static final double PORTFOLIO_ICON_WIDTH = 752 / debuggingRatio;
    private static final double PORTFOLIO_ICON_HEIGHT = 977 / debuggingRatio;
    private static final double PORTFOLIO_ICON_X = 1899 / debuggingRatio;
    private static final double PORTFOLIO_ICON_Y = 556 / debuggingRatio;
    private static final String PORTFOLIO_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 5);";

    //Иконка раздела Медали
    private static final String MEDAL_URL = "file:///" + RESOURCES_PATH + "icon/medal_icon.png";
    private static final double MEDAL_ICON_WIDTH = 526 / debuggingRatio;
    private static final double MEDAL_ICON_HEIGHT = 424 / debuggingRatio;
    private static final double MEDAL_ICON_X = 66 / debuggingRatio;
    private static final double MEDAL_ICON_Y = 1653 / debuggingRatio;
    private static final String MEDAL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";

    //Иконка раздела Книги
    private static final String BOOK_URL = "file:///" + RESOURCES_PATH + "icon/book_icon.png";
    private static final double BOOK_ICON_WIDTH = 672 / debuggingRatio;
    private static final double BOOK_ICON_HEIGHT = 895 / debuggingRatio;
    private static final double BOOK_ICON_X = 3169 / debuggingRatio;
    private static final double BOOK_ICON_Y = 1129 / debuggingRatio;
    private static final String BOOK_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 8, 8);";

    //Иконка раздела Фильмы
    private static final String FILM1_URL = "file:///" + RESOURCES_PATH + "icon/film1_icon.png";
    private static final String FILM2_URL = "file:///" + RESOURCES_PATH + "icon/film2_icon.png";
    private static final double FILM1_ICON_WIDTH = 584 / debuggingRatio;
    private static final double FILM2_ICON_WIDTH = 536 / debuggingRatio;
    private static final double FILM2_ICON_X = 178 / debuggingRatio;
    private static final double FILM2_ICON_Y = 223 / debuggingRatio;
    private static final double FILM_ICON_X = 3244 / debuggingRatio;
    private static final double FILM_ICON_Y = 146 / debuggingRatio;
    private static final String FILM_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";

    //Иконка раздела Викторина
    private static final String QUIZ_URL = "file:///" + RESOURCES_PATH + "icon/quiz_icon.png";
    private static final double QUIZ_ICON_WIDTH = 617 / debuggingRatio;
    private static final double QUIZ_ICON_X = 1669 / debuggingRatio;
    private static final double QUIZ_ICON_Y = 1697 / debuggingRatio;
    private static final String QUIZ_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, -2, 8);";
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //создание родительской панели
        root = new BorderPane();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setPrefWidth(TABLE_WIDTH);
        root.setPrefHeight(TABLE_HEIGHT);

        mainPane = new Pane();
        mainPane.setPrefWidth(TABLE_WIDTH);
        mainPane.setPrefHeight(TABLE_HEIGHT);
        root.setCenter(mainPane);
        mainPane.setLayoutX(0);
        mainPane.setLayoutY(0);

        //создание сцены для родительской панели и отображение
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        //установка элементов основной сцены
        setMainScene();
    }

    public static void changeRootBackground(final String imagePath) {
        root.setBackground(new Background(new BackgroundImage(
                new Image("file:" + imagePath), NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

    public static void setMainScene() {

        changeRootBackground(RESOURCES_PATH + "table_with_lamp.jpg");

        mainPane.getChildren().clear();

        //создание иконок для разделов
        ImageView map = new ImageView(new Image(MAP_URL));
        map.setPreserveRatio(true);
        map.setFitWidth(MAP_ICON_WIDTH);
        map.setLayoutX(MAP_ICON_X);
        map.setLayoutY(MAP_ICON_Y);
        map.setStyle(MAP_SHADOW);

        map.setOnMouseClicked(event -> setMapScene());
        map.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setMapScene();
                setTimeWait();
            }
        });

        ImageView mail = new ImageView(new Image(MAIL_URL));
        mail.setPreserveRatio(true);
        mail.setFitWidth(MAIL_ICON_WIDTH);
        mail.setLayoutX(MAIL_ICON_X);
        mail.setLayoutY(MAIL_ICON_Y);
        mail.setStyle(MAIL_SHADOW);

        mail.setOnMouseClicked(event -> setMailScene());
        mail.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setMailScene();
                setTimeWait();
            }
        });

        ImageView portfolio = new ImageView(new Image(PORTFOLIO_URL));
        portfolio.setPreserveRatio(true);
        portfolio.setFitWidth(PORTFOLIO_ICON_WIDTH);
        portfolio.setLayoutX(PORTFOLIO_ICON_X);
        portfolio.setLayoutY(PORTFOLIO_ICON_Y);
        portfolio.setStyle(PORTFOLIO_SHADOW);

        portfolio.setOnMouseClicked(event -> setPortfolioScene());
        portfolio.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setPortfolioScene();
                setTimeWait();
            }
        });

        ImageView medal = new ImageView(new Image(MEDAL_URL));
        medal.setPreserveRatio(true);
        medal.setFitWidth(MEDAL_ICON_WIDTH);
        medal.setLayoutX(MEDAL_ICON_X);
        medal.setLayoutY(MEDAL_ICON_Y);
        medal.setStyle(MEDAL_SHADOW);

        medal.setOnMouseClicked(event -> setOrdenScene(ORDEN));
        medal.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setOrdenScene(ORDEN);
                setTimeWait();
            }
        });

        ImageView book = new ImageView(new Image(BOOK_URL));
        book.setPreserveRatio(true);
        book.setFitWidth(BOOK_ICON_WIDTH);
        book.setLayoutX(BOOK_ICON_X);
        book.setLayoutY(BOOK_ICON_Y);
        book.setStyle(BOOK_SHADOW);

        book.setOnMouseClicked(event -> setBookScene());
        book.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setBookScene();
                setTimeWait();
            }
        });

        ImageView film1 = new ImageView(new Image(FILM1_URL));
        film1.setFitWidth(FILM1_ICON_WIDTH);
        film1.setPreserveRatio(true);
        film1.setStyle(FILM_SHADOW);
        ImageView film2 = new ImageView(new Image(FILM2_URL));
        film2.setFitWidth(FILM2_ICON_WIDTH);
        film2.setPreserveRatio(true);
        film2.setLayoutX(FILM2_ICON_X);
        film2.setLayoutY(FILM2_ICON_Y);
        film2.setStyle(FILM_SHADOW);
        Group film = new Group(film1, film2);
        film.setLayoutX(FILM_ICON_X);
        film.setLayoutY(FILM_ICON_Y);

        film.setOnMouseClicked(event -> setFilmScene());
        film.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setFilmScene();
                setTimeWait();
            }
        });

        ImageView quiz = new ImageView(new Image(QUIZ_URL));
        quiz.setPreserveRatio(true);
        quiz.setFitWidth(QUIZ_ICON_WIDTH);
        quiz.setLayoutX(QUIZ_ICON_X);
        quiz.setLayoutY(QUIZ_ICON_Y);
        quiz.setStyle(QUIZ_SHADOW);

        quiz.setOnMouseClicked(event -> setQuizScene());
        quiz.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setQuizScene();
                setTimeWait();
            }
        });


        mainPane.getChildren().addAll(map, mail, portfolio, medal, book, film, quiz);
    }

}
