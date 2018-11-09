package table4K.portfolio;

import general.ArrayListIndex;
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

public class PortfolioSlide extends Pane{

    private ArrayList<ArrayList<File>> imageFiles;

    private ArrayList<ArrayList<File>> textFiles;

    private ArrayListIndex<PersonalCardPane> personalCards = new ArrayListIndex<>();

    //размеры сабсцены, играющей роль маски видимости
    // для объектов PersonalCardPane
    private static final double SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;
    private static final double SUBSCENE_SLIDER_HEIGHT = 290 / debuggingRatio;

    private static final int personalCardPaneNumber = 6;


    public PortfolioSlide(final ArrayList<ArrayList<File>> imageFilesEnter,
                          final ArrayList<ArrayList<File>> textFilesEnter) {

        imageFiles = imageFilesEnter;
        textFiles = textFilesEnter;

        if (imageFiles.size() != textFiles.size()) {
            System.out.println("Количество личных дел в imageFiles и textFiles в классе PortfolioSlide не совпадают");
        }

        for (int i = 0; i < imageFiles.size(); i++) {
            personalCards.add(new PersonalCardPane(imageFiles.get(i), textFiles.get(i)));
        }

        double interval = (SUBSCENE_SLIDER_WIDTH - personalCardPaneNumber * personalCards.get(0).getPrefWidth()) / (personalCardPaneNumber);

        int beginIndex = 0;
        int endIndex = beginIndex + personalCardPaneNumber - 1;

        ArrayListIndex<PersonalCardPane> personalCardsView = new ArrayListIndex<>();

        for (int i = 0; i < personalCardPaneNumber; i++) {
            personalCardsView.add(personalCards.get(i));
        }

        for (int i = 0; i < personalCardsView.size(); i++) {
            personalCardsView.get(i).setLayoutX(interval / 2 + i * (interval + personalCardsView.get(0).getPrefWidth()));
        }

        Group gr = new Group();
        gr.getChildren().addAll(personalCardsView);
        double cardPaneHeight = ((PersonalCardPane)gr.getChildren().get(0)).getPrefHeight();

        ImageView arrowLeft = new ImageView(new File("file:" + RESOURCES_PATH + "portfolio/arrow_left.png").toString());
        arrowLeft.setFitHeight(cardPaneHeight * 0.9);
        arrowLeft.setLayoutY(cardPaneHeight * 0.05);
        arrowLeft.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 2);");

        SubScene subScene = new SubScene(gr, SUBSCENE_SLIDER_WIDTH, SUBSCENE_SLIDER_HEIGHT);
        subScene.setLayoutX(interval / 2 + arrowLeft.getLayoutBounds().getWidth());

        ImageView arrowRight = new ImageView(new File("file:" + RESOURCES_PATH + "portfolio/arrow_right.png").toString());
        arrowRight.setFitHeight(cardPaneHeight * 0.9);
        arrowRight.setLayoutY(cardPaneHeight * 0.05);
        arrowRight.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 1, 2);");
        arrowRight.setLayoutX(subScene.getWidth() + interval / 2 * 2 + arrowLeft.getLayoutBounds().getWidth());

        this.getChildren().addAll(arrowLeft, subScene, arrowRight);

        arrowLeft.setOnMouseClicked(event -> {
            PersonalCardPane temp = new PersonalCardPane(imageFiles.get(6), textFiles.get(6));
            temp.setLayoutX(interval / 2 + 6 * (interval + personalCardsView.get(0).getPrefWidth()));
            gr.getChildren().add(temp);

            TranslateTransition tt1 = new TranslateTransition();
            tt1.setDuration(Duration.millis(150));
            tt1.setNode(gr);
            tt1.setByX(-(interval + personalCardsView.get(0).getPrefWidth()));

            TranslateTransition tt2 = new TranslateTransition();
            tt2.setDuration(Duration.millis(1));
            tt2.setNode(gr);
            tt2.setByX(interval + personalCardsView.get(0).getPrefWidth());

            SequentialTransition st = new SequentialTransition();
            st.getChildren().addAll(tt1, tt2);
            st.play();

            tt1.setOnFinished(event1 -> {
                gr.getChildren().remove(0);
                for (int i = 0; i < gr.getChildren().size(); i++) {
                    gr.getChildren().get(i).setLayoutX(interval / 2 + i * (interval + personalCardsView.get(0).getPrefWidth()));
                }
            });
        });

    }
    
}
