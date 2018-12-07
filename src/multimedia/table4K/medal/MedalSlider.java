package table4K.medal;

import general.SliderIndex;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;
import static table4K.medal.MedalImage.MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

public class MedalSlider extends Pane {

    //ширина слайдера для медалей
    private static final double SLIDER_WIDTH = 3250 / debuggingRatio;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей
    private static final double SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;

    //высота слайдера
    private static final double SLIDER_HEIGHT = MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

    //количество орденов, отображаемых в слайдере
    private static final int sliderNumber = 6;

    //список файлов изображений орденов
    private ArrayList<ArrayList<File>> imageFiles;

    //список файлов с текстом для орденов
    private ArrayList<ArrayList<File>> textFiles;

    //слайдер индекс для получения индекса ордена
    // отображаемого в слайдере при пролистывании
    private SliderIndex sliderIndex;

    public MedalSlider(final ArrayList<ArrayList<File>> imageFilesEnter,
                           final ArrayList<ArrayList<File>> textFilesEnter) {

        imageFiles = imageFilesEnter;
        textFiles = textFilesEnter;

        if (imageFiles.size() != textFiles.size()) {
            System.out.println("Количество личных дел в imageFiles и textFiles в классе PortfolioSlider не совпадают");
        }

        sliderIndex = new SliderIndex(imageFiles.size(), sliderNumber);

        //добавление орденов в группу, отображаемую в слайдере
        Group grSliderView = new Group();
        for (int i = 0; i < sliderNumber; i++) {
            grSliderView.getChildren().add(new MedalImage(imageFiles.get(i), textFiles.get(i)));
        }

        //задание положения элементов в сабсцене
        for (int i = 0; i < grSliderView.getChildren().size(); i++) {
            grSliderView.getChildren().get(i).setLayoutX((0.5 + i) * (SUBSCENE_SLIDER_WIDTH / sliderNumber) -
                                                         grSliderView.getChildren().get(i).getLayoutBounds().getWidth() / 2);
            grSliderView.getChildren().get(i).setLayoutY((SLIDER_HEIGHT - grSliderView.getChildren().get(i).getLayoutBounds().getHeight()) / 2);
        }

        //левая стрелка листания слайдера
        ImageView arrowLeft = createSliderArrow("file:" + RESOURCES_PATH + "medal/arrow_left.png");

        //сабсцена для группы медалей в слайдере
        // которая играет роль маски видимости
        // при их перелистывании
        SubScene subScene = new SubScene(grSliderView, SUBSCENE_SLIDER_WIDTH, SLIDER_HEIGHT);
        subScene.setLayoutX((SLIDER_WIDTH - SUBSCENE_SLIDER_WIDTH) / 2);
        subScene.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);");

        //правая стрелка листания слайдера
        ImageView arrowRight = createSliderArrow("file:" + RESOURCES_PATH + "medal/arrow_right.png");
        arrowRight.setLayoutX(SLIDER_WIDTH - arrowLeft.getLayoutBounds().getWidth());

        this.getChildren().addAll(arrowLeft, subScene, arrowRight);


        arrowLeft.setOnMouseClicked(event -> {
            arrowClick(grSliderView, -(SUBSCENE_SLIDER_WIDTH / sliderNumber));
        });

        arrowRight.setOnMouseClicked(event -> {
            arrowClick(grSliderView,SUBSCENE_SLIDER_WIDTH / sliderNumber);
        });

        arrowLeft.setOnTouchReleased(event -> {
            arrowClick(grSliderView, -(SUBSCENE_SLIDER_WIDTH / sliderNumber));
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе MedalSlider при отпускании тача");
            }
        });

        arrowRight.setOnTouchReleased(event -> {
            arrowClick(grSliderView, SUBSCENE_SLIDER_WIDTH / sliderNumber);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе MedalSlider при отпускании тача");
            }
        });
    }

    //создание стрелок для слайдера
    private ImageView createSliderArrow(final String imagePath) {

        ImageView arrow = new ImageView(imagePath);
        arrow.setFitHeight(SLIDER_HEIGHT * 0.8);
        arrow.setLayoutY((SLIDER_HEIGHT - arrow.getLayoutBounds().getHeight()) / 2);
        arrow.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);");

        return arrow;
    }

    //Действия по клику (тапу) на стрелке
    //Какая стрелка нажата определяется знаком передаваемого сдвига - displacement
    // если он положительный, значит нажата стрелка Вправо,
    // если отрицательный - значит Влево
    private void arrowClick(final Group grSliderView, final double displacement) {

        int newSliderIndex = (displacement < 0)? sliderIndex.getNextSliderIndex(): sliderIndex.gePrevSliderIndex();

        MedalImage miTemp = new MedalImage(imageFiles.get(newSliderIndex),
                textFiles.get(newSliderIndex));

        if (displacement < 0) {
            miTemp.setLayoutX((0.5 + sliderNumber) * (SUBSCENE_SLIDER_WIDTH / sliderNumber) -
                              miTemp.getLayoutBounds().getWidth() / 2);
            grSliderView.getChildren().add(miTemp);
        } else {
            miTemp.setLayoutX(- (0.5 * (SUBSCENE_SLIDER_WIDTH / sliderNumber) +
                                 miTemp.getLayoutBounds().getWidth() / 2));
            grSliderView.getChildren().add(0, miTemp);
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
                    (displacement < 0)?
                            0:
                            grSliderView.getChildren().get(grSliderView.getChildren().size() - 1));

            for (int i = 0; i < grSliderView.getChildren().size(); i++) {
                grSliderView.getChildren().get(i).setLayoutX(grSliderView.getChildren().get(i).getLayoutX() + displacement);
            }
        });
    }
}
