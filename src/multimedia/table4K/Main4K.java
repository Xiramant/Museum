package table4K;

import general.SectionKey;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import table4K.ui.Icon;

import static general.SectionKey.*;
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static table4K.film.Film.setFilmScene;
import static table4K.mail.Mail.setMailScene;
import static table4K.book.Book.setBookScene;
import static table4K.map.Map.setMapScene;
import static table4K.medal.Medal.setMedalScene;
import static table4K.portfolio.Portfolio.setPortfolioScene;
import static table4K.quiz.Quiz.setQuizScene;

public class Main4K extends Application{

    //величина на которые уменьшаются все размеры,
    // чтобы программу можно было дебажить на обычном мониторе
    public static final double DEBUGGING_RATIO = 2;

    //включение/отключение реакции на события мыши и тача
    private static final boolean MOUSE_PERMISSION = true;
    private static final boolean TOUCH_PERMISSION = true;

    //размер минимального смещения, при котором считается,
    // что изображение целеноправленно перемещалось,
    // а не сдвинулось случайно при щелчке / тапе
    private static final double MIN_MOVE = 10d;

    //путь к директории с файлами
    public static final String RESOURCES_PATH = getResourcesPath();

    //время задержки после тача
    public static final int TOUCH_TIMEOUT = 300;

    private static BorderPane root;

    //Основная панель с содержимым
    public static Pane mainPane;

    //Размеры интерактивного стола
    public static double TABLE_WIDTH = 4096 / DEBUGGING_RATIO;
    public static double TABLE_HEIGHT = 2160 / DEBUGGING_RATIO;


    private static String getResourcesPath() {
        return (isWin())?
                "C://museumResources/":
                "/home/xiramant/museumResources/";
    }

    private static Boolean isWin() {
        return System.getProperty("os.name").startsWith("Win");
    }


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

        Node mapIcon = Icon.getMap();
        mapIcon.setOnMouseClicked(event -> actionDelay(event, MAP));
        mapIcon.setOnTouchReleased(event -> actionDelay(event, MAP));

        Node mailIcon = Icon.getMail();
        mailIcon.setOnMouseClicked(event -> actionDelay(event, MAIL));
        mailIcon.setOnTouchReleased(event -> actionDelay(event, MAIL));

        Node portfolioIcon = Icon.getPortfolio();
        portfolioIcon.setOnMouseClicked(event -> actionDelay(event, PORTFOLIO));
        portfolioIcon.setOnTouchReleased(event -> actionDelay(event, PORTFOLIO));

        Node medalIcon = Icon.getMedal();
        medalIcon.setOnMouseClicked(event -> actionDelay(event, MEDAL));
        medalIcon.setOnTouchReleased(event -> actionDelay(event, MEDAL));

        Node bookIcon = Icon.getBook();
        bookIcon.setOnMouseClicked(event -> actionDelay(event, BOOK));
        bookIcon.setOnTouchReleased(event -> actionDelay(event, BOOK));

        Node quizIcon = Icon.getQuiz();
        quizIcon.setOnMouseClicked(event -> actionDelay(event, QUIZ));
        quizIcon.setOnTouchReleased(event -> actionDelay(event, QUIZ));

        Node filmIcon = Icon.getFilm();
        filmIcon.setOnMouseClicked(event -> actionDelay(event, FILM));
        filmIcon.setOnTouchReleased(event -> actionDelay(event, FILM));


        mainPane.getChildren().addAll(mapIcon, mailIcon, portfolioIcon, medalIcon, bookIcon, quizIcon, filmIcon);
    }

    //установка задержки при выборе раздела
    private static void actionDelay(final InputEvent event, final SectionKey sectionKey) {

        if (isTimeWaitEnd() && actionPermission(event)) {

            switch (sectionKey) {
                case MAP:
                    setMapScene();
                    break;
                case MAIL:
                    setMailScene();
                    break;
                case PORTFOLIO:
                    setPortfolioScene();
                    break;
                case MEDAL:
                    setMedalScene(ORDEN);
                    break;
                case BOOK:
                    setBookScene();
                    break;
                case FILM:
                    setFilmScene();
                    break;
                case QUIZ:
                    setQuizScene();
                    break;
            }

            setTimeWait();
        }
    }

    //разрешение действия
    public static boolean actionPermission(final InputEvent event) {
        if (event.getClass().equals(MouseEvent.class) && !MOUSE_PERMISSION) return false;
        if (event.getClass().equals(TouchEvent.class) && !TOUCH_PERMISSION) return false;
        return true;
    }


    //Индикация минимального перемещения изображения
    public static boolean isMinMove(final double xDelta, final double yDelta) {
        return Math.abs(xDelta) + Math.abs(yDelta) > MIN_MOVE;
    }

}