package table4K.portfolio;

import general.SliderIndex;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;

public class PortfolioSlider extends Pane{

    //ширина сабсцены, играющей роль маски видимости
    // для объектов PersonalCardPane
    private static final double SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;

    //количество личных карточек, отображаемых в слайдере
    private static final int sliderNumber = 6;

    //список файлов изображений из личных дел
    private ArrayList<ArrayList<File>> imageFiles;

    //список файлов с текстом из личных дел
    private ArrayList<ArrayList<File>> textFiles;

    //слайдер индекс для получения индекса личной карточки
    // отображаемой в слайдере при пролистывании
    private SliderIndex sliderIndex;


    public PortfolioSlider(final ArrayList<ArrayList<File>> imageFilesEnter,
                           final ArrayList<ArrayList<File>> textFilesEnter) {

        imageFiles = imageFilesEnter;
        textFiles = textFilesEnter;

        if (imageFiles.size() != textFiles.size()) {
            System.out.println("Количество личных дел в imageFiles и textFiles в классе PortfolioSlider не совпадают");
        }

        sliderIndex = new SliderIndex(imageFiles.size(), sliderNumber);

        //добавление личных карточек в группу, отображаемую в слайдере
        Group grSliderView = new Group();
        for (int i = 0; i < sliderNumber; i++) {
            grSliderView.getChildren().add(new PersonalCardPane(imageFiles.get(i), textFiles.get(i)));
        }

        //определение текущей длины и высоты личной карточки
        double cardPaneWidth = ((PersonalCardPane)grSliderView.getChildren().get(0)).getPrefWidth();
        double cardPaneHeight = ((PersonalCardPane)grSliderView.getChildren().get(0)).getPrefHeight();

        //определение интервалов между личными карточками,
        // отображаемых в слайдере
        double interval = (SUBSCENE_SLIDER_WIDTH - sliderNumber * cardPaneWidth) / (sliderNumber);

        //установка X для личных карточек в слайдере
        for (int i = 0; i < grSliderView.getChildren().size(); i++) {
            grSliderView.getChildren().get(i).setLayoutX(interval / 2 + i * (interval + cardPaneWidth));
        }

        //левая стрелка листания слайдера
        ImageView arrowLeft = createSliderArrow("file:" + RESOURCES_PATH + "portfolio/arrow_left.png", cardPaneHeight);

        //сабсцена для группы личных карточек в слайдере
        // которая играет роль маски видимости для личных карточек
        // при их перелистывании
        SubScene subScene = new SubScene(grSliderView, SUBSCENE_SLIDER_WIDTH, cardPaneHeight);
        subScene.setLayoutX(interval / 2 + arrowLeft.getLayoutBounds().getWidth());
        subScene.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);");

        //правая стрелка листания слайдера
        ImageView arrowRight = createSliderArrow("file:" + RESOURCES_PATH + "portfolio/arrow_right.png", cardPaneHeight);
        arrowRight.setLayoutX(subScene.getWidth() + interval / 2 * 2 + arrowLeft.getLayoutBounds().getWidth());

        this.getChildren().addAll(arrowLeft, subScene, arrowRight);

        arrowLeft.setOnMouseClicked(event -> {
            arrowClick(grSliderView, -(interval + cardPaneWidth));
        });

        arrowRight.setOnMouseClicked(event -> {
            arrowClick(grSliderView, interval + cardPaneWidth);
        });

        arrowLeft.setOnTouchReleased(event -> {
            arrowClick(grSliderView, -(interval + cardPaneWidth));
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе PortfolioSlider при отпускании тача");
            }
        });

        arrowRight.setOnTouchReleased(event -> {
            arrowClick(grSliderView, interval + cardPaneWidth);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе PortfolioSlider при отпускании тача");
            }
        });

    }

    //создание стрелок для слайдера
    private ImageView createSliderArrow(final String imagePath, final double sliderHeight) {

        ImageView arrow = new ImageView(imagePath);
        arrow.setFitHeight(sliderHeight * 0.9);
        arrow.setLayoutY(sliderHeight * 0.05);
        arrow.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);");

        return arrow;
    }

    //Действия по клику (тапу) на стрелке
    //Какая стрелка нажата определяется знаком передаваемого сдвига - displacement
    // если он положительный, значит нажата стрелка Вправо,
    // если отрицательный - значит Влево
    private void arrowClick(final Group grSliderView, final double displacement) {

        int newSliderIndex = (displacement < 0)? sliderIndex.getNextSliderIndex(): sliderIndex.gePrevSliderIndex();

        PersonalCardPane pcpTemp = new PersonalCardPane(imageFiles.get(newSliderIndex),
                                                        textFiles.get(newSliderIndex));

        if (displacement < 0) {
            pcpTemp.setLayoutX(grSliderView.getChildren().get(grSliderView.getChildren().size() - 1).getLayoutX() - displacement);
            grSliderView.getChildren().add(pcpTemp);
        } else {
            pcpTemp.setLayoutX(- displacement);
            grSliderView.getChildren().add(0, pcpTemp);
        }

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
            System.out.println();

            for (int i = 0; i < grSliderView.getChildren().size(); i++) {
                grSliderView.getChildren().get(i).setLayoutX(grSliderView.getChildren().get(i).getLayoutX() + displacement);
            }
        });
    }
    
}