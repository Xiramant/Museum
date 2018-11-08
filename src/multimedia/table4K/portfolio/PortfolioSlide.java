package table4K.portfolio;

import general.ArrayListIndex;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.debuggingRatio;

public class PortfolioSlide extends Pane{

    private ArrayList<ArrayList<File>> imageFiles;

    private ArrayList<ArrayList<File>> textFiles;

    private ArrayListIndex<PersonalCardPane> personalCards = new ArrayListIndex<>();

    private static final double PORTFOLIO_SLIDE_WIDTH = 3600 / debuggingRatio;
    private static final double PORTFOLIO_SLIDE_HEIGHT = 278 / debuggingRatio;

    private static final double PORTFOLIO_SLIDE_VIEW_WIDTH = 2950 / debuggingRatio;
    private static final double PORTFOLIO_SLIDE_VIEW_HEIGHT = 278 / debuggingRatio;

    private static final int personalCardPaneNumber = 6;


    public PortfolioSlide(final ArrayList<ArrayList<File>> imageFilesEnter,
                          final ArrayList<ArrayList<File>> textFilesEnter) {

        this.setPrefWidth(PORTFOLIO_SLIDE_WIDTH);
        this.setPrefHeight(PORTFOLIO_SLIDE_HEIGHT);

        imageFiles = imageFilesEnter;
        textFiles = textFilesEnter;

        if (imageFiles.size() != textFiles.size()) {
            System.out.println("Количество личных дел в imageFiles и textFiles в классе PortfolioSlide не совпадают");
        }

        for (int i = 0; i < imageFiles.size(); i++) {
            personalCards.add(new PersonalCardPane(imageFiles.get(i), textFiles.get(i)));
        }

        Pane portfolioSlideView = new Pane();
        portfolioSlideView.setPrefWidth(PORTFOLIO_SLIDE_VIEW_WIDTH);
        portfolioSlideView.setPrefHeight(PORTFOLIO_SLIDE_VIEW_HEIGHT);

        double interval = (PORTFOLIO_SLIDE_VIEW_WIDTH - personalCardPaneNumber * personalCards.get(0).getPrefWidth()) / (personalCardPaneNumber);

        int beginIndex = 0;
        int endIndex = beginIndex + personalCardPaneNumber - 1;

        ArrayListIndex<PersonalCardPane> personalCardsView = new ArrayListIndex<>();

        for (int i = 0; i < personalCardPaneNumber; i++) {
            personalCardsView.add(personalCards.get(i));
        }

        for (int i = 0; i < personalCardsView.size(); i++) {
            personalCardsView.get(i).setLayoutX(interval / 2 + i * (interval + personalCardsView.get(0).getPrefWidth()));
        }

        portfolioSlideView.getChildren().addAll(personalCardsView);

        portfolioSlideView.setLayoutX(PORTFOLIO_SLIDE_WIDTH / 2 - PORTFOLIO_SLIDE_VIEW_WIDTH / 2);

        this.getChildren().add(portfolioSlideView);

    }
}
