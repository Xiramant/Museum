package general;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

import static general.TouchWait.eventDelayBegin;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.controller.ControllerParameters.isEventPermission;

public class Slider extends Pane{

    //ширина слайдера
    private final double SLIDER_WIDTH;

    //высота слайдера
    private final double SLIDER_HEIGHT;

    //пути к левой и правой стрелкам слайдера
    private final static String LEFT_ARROW_PATH = "file:" + RESOURCES_PATH + "arrow_left.png";
    private final static String RIGHT_ARROW_PATH = "file:" + RESOURCES_PATH + "arrow_right.png";

    private final static String SLIDER_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);";

    //высота стрелок слайдера в процентах от высоты слайдера
    private final double ARROW_HEIGHT = 0.8;

    //ширина сабсцены, играющей роль маски видимости
    private final double SUBSCENE_SLIDER_WIDTH;

    //количество элементов, отображаемых в слайдере
    private final int SLIDER_NUMBER;

    //слайдер индекс для получения индекса ордена
    // отображаемого в слайдере при пролистывании
    private SliderIndex sliderIndex;

    private ArrayList<Node> sliderElements = new ArrayList<>();

    public Slider(final double sliderWidthEnter,
                  final double sliderHeightEnter,
                  final double subsceneSliderWidthEnter,
                  final int sliderNumberEnter,
                  final ArrayList<Node> sliderElementsEnter) {

        this.SLIDER_WIDTH = sliderWidthEnter;
        this.SLIDER_HEIGHT = sliderHeightEnter;
        this.SUBSCENE_SLIDER_WIDTH = subsceneSliderWidthEnter;
        this.SLIDER_NUMBER = sliderNumberEnter;
        this.sliderElements = sliderElementsEnter;

        this.sliderIndex = new SliderIndex(sliderElements.size(), SLIDER_NUMBER);

        //добавление элементов в группу, отображаемую в слайдере
        Group grSliderView = new Group();
        for (int i = 0; i < SLIDER_NUMBER; i++) {
            Node temp = sliderElements.get(i);
            grSliderView.getChildren().add(temp);
        }

        //необходимо для получения правильного размера высоты элементов grSliderView
        // без данной конструкции getLayoutBounds().getHeight() выдаст 0
        grSliderView.layout();

        //задание положения элементов на сабсцене
        for (int i = 0; i < grSliderView.getChildren().size(); i++) {
            grSliderView.getChildren().get(i).setLayoutX((0.5 + i) * (SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER) -
                    grSliderView.getChildren().get(i).getLayoutBounds().getWidth() / 2);
            grSliderView.getChildren().get(i).setLayoutY((SLIDER_HEIGHT - grSliderView.getChildren().get(i).getLayoutBounds().getHeight()) / 2);
        }

        //левая стрелка листания слайдера
        ImageView arrowLeft = createSliderArrow(LEFT_ARROW_PATH);

        //сабсцена для группы элементов в слайдере,
        // которая играет роль маски видимости при их перелистывании
        SubScene subScene = new SubScene(grSliderView, SUBSCENE_SLIDER_WIDTH, SLIDER_HEIGHT);
        subScene.setLayoutX((SLIDER_WIDTH - SUBSCENE_SLIDER_WIDTH) / 2);
        subScene.setStyle(SLIDER_SHADOW);

        //правая стрелка листания слайдера
        ImageView arrowRight = createSliderArrow(RIGHT_ARROW_PATH);
        arrowRight.setLayoutX(SLIDER_WIDTH - arrowLeft.getLayoutBounds().getWidth());

        this.getChildren().addAll(arrowLeft, subScene, arrowRight);

        arrowLeft.setOnMouseClicked(event -> {
            arrowClick(event, grSliderView, -(SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER));
        });

        arrowRight.setOnMouseClicked(event -> {
            arrowClick(event, grSliderView,SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER);
        });

        arrowLeft.setOnTouchReleased(event -> {
            arrowClick(event, grSliderView, -(SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER));
        });

        arrowRight.setOnTouchReleased(event -> {
                arrowClick(event, grSliderView, SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER);
        });
    }

    //создание стрелок для слайдера
    private ImageView createSliderArrow(final String imagePath) {

        ImageView arrow = new ImageView(imagePath);
        arrow.setFitHeight(SLIDER_HEIGHT * ARROW_HEIGHT);
        arrow.setLayoutY((SLIDER_HEIGHT - arrow.getLayoutBounds().getHeight()) / 2);
        arrow.setStyle(SLIDER_SHADOW);

        return arrow;
    }

    //Действия по клику (тапу) на стрелке
    //Какая стрелка нажата определяется знаком передаваемого сдвига - displacement
    // если он положительный, значит нажата стрелка Вправо,
    // если отрицательный - значит Влево
    private void arrowClick(final InputEvent event, final Group grSliderView, final double displacement) {

        if (isEventPermission(event)) {

            int newSliderIndex = (displacement < 0) ? sliderIndex.getNextSliderIndex() : sliderIndex.gePrevSliderIndex();

            Node miTemp = sliderElements.get(newSliderIndex);

            if (displacement < 0) {
                grSliderView.getChildren().add(miTemp);
            } else {
                grSliderView.getChildren().add(0, miTemp);
            }

            grSliderView.layout();

            if (displacement < 0) {
                miTemp.setLayoutX((0.5 + SLIDER_NUMBER) * (SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER) -
                        miTemp.getLayoutBounds().getWidth() / 2);
            } else {
                miTemp.setLayoutX(-(0.5 * (SUBSCENE_SLIDER_WIDTH / SLIDER_NUMBER) +
                        miTemp.getLayoutBounds().getWidth() / 2));
            }

            miTemp.setLayoutY((SLIDER_HEIGHT - miTemp.getLayoutBounds().getHeight()) / 2);

            TranslateTransition tt1 = new TranslateTransition();
            tt1.setDuration(Duration.millis(150));
            tt1.setNode(grSliderView);
            tt1.setByX(displacement);

            TranslateTransition tt2 = new TranslateTransition();
            tt2.setDuration(Duration.millis(1));
            tt2.setNode(grSliderView);
            tt2.setByX(-displacement);

            SequentialTransition st = new SequentialTransition();
            st.getChildren().addAll(tt1, tt2);
            st.play();

            //действия при завершении анимации перемещения слайдера
            tt1.setOnFinished(event1 -> {
                grSliderView.getChildren().remove(
                        (displacement < 0) ?
                                0 :
                                SLIDER_NUMBER);

                for (int i = 0; i < grSliderView.getChildren().size(); i++) {
                    grSliderView.getChildren().get(i).setLayoutX(grSliderView.getChildren().get(i).getLayoutX() + displacement);
                }
            });

            TouchWait.eventDelayBegin();
        }
    }
}
