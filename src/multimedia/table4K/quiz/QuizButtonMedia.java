package table4K.quiz;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;
import static table4K.Main4K.mainPane;

public class QuizButtonMedia extends QuizButton {

    private String media;

    private Group groupMedia;

    QuizButtonMedia(final File imageFile, final String mediaEnter) {
        super(imageFile);

        media = mediaEnter;

        if (media.length() != 0) {

            groupMedia = new Group();

            ImageView whiteBackground = new ImageView(new Image("file:" + RESOURCES_PATH + "table_4K_black.jpg"));
            whiteBackground.setPreserveRatio(false);
            whiteBackground.setFitWidth(2429 / debuggingRatio);
            whiteBackground.setFitHeight(1384 / debuggingRatio);
            whiteBackground.setLayoutX(1 / debuggingRatio);
            whiteBackground.setLayoutY(1 / debuggingRatio);

            ImageView tvScreen = new ImageView(new Image("file:" + RESOURCES_PATH + "quiz/quiz_tv_screen.png"));
            tvScreen.setFitWidth(2431 / debuggingRatio);
            tvScreen.setPreserveRatio(true);
            tvScreen.setLayoutX(0 / debuggingRatio);
            tvScreen.setLayoutY(0 / debuggingRatio);

            ImageView ivMedia = new ImageView(new Image("file:" + RESOURCES_PATH + "quiz/media/" + media));
            double ratioX = ivMedia.getLayoutBounds().getWidth() / 3223;
            double ratioY = ivMedia.getLayoutBounds().getHeight() / 1196;
            if (ratioX > ratioY) {
                ivMedia.setFitWidth(3223 / debuggingRatio);
            } else {
                ivMedia.setFitHeight(1196 / debuggingRatio);
            }
            ivMedia.setPreserveRatio(true);
            ivMedia.setLayoutX(tvScreen.getLayoutBounds().getWidth() / 2 - ivMedia.getLayoutBounds().getWidth() / 2);
            ivMedia.setLayoutY(tvScreen.getLayoutBounds().getHeight() / 2 - ivMedia.getLayoutBounds().getHeight() / 2);

            groupMedia.getChildren().addAll(whiteBackground, ivMedia, tvScreen);
        }

        this.setOnMouseClicked(event -> {

            QuizButtonMediaAction();

        });
    }

    private void QuizButtonMediaAction() {

        if (media.length() != 0) {
            setOnPushInvert();

            if (isOnPush()) {
                groupMedia.setLayoutX(401 / debuggingRatio);
                groupMedia.setLayoutY(218 / debuggingRatio);
                mainPane.getChildren().add(groupMedia);
            } else {
                mainPane.getChildren().remove(groupMedia);
            }
        }
    }

    public void setQuizButtonMediaNonPush() {

        if (isOnPush()) {
            setOnPush(false);
            mainPane.getChildren().remove(groupMedia);
        }
    }


}
