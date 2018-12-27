package table4K.quiz;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.mainPane;

public class QuizButtonMedia extends QuizButton {

    private String media;

    private Group groupMedia;

    //место расположения фонов
    private static final Image BLACK_BACKGROUND_IMAGE = new Image("file:" + RESOURCES_PATH + "table_4K_black.jpg");
    private static final Image TV_SCREEN_IMAGE = new Image("file:" + RESOURCES_PATH + "quiz/quiz_tv_screen.png");

    //размеры экрана тв
    private static final double TV_SCREEN_WIDTH = 2431 / DEBUGGING_RATIO;
    private static final double TV_SCREEN_HEIGHT = 1386 / DEBUGGING_RATIO;

    //максимальные размеры медиа (картинки)
    private static final double MEDIA_WIDTH_MAX = 2100 / DEBUGGING_RATIO;
    private static final double MEDIA_HEIGHT_MAX = 1196 / DEBUGGING_RATIO;

    //расположение группы из фонов и медиа
    private static final double GROUP_X = 401 / DEBUGGING_RATIO;
    private static final double GROUP_Y = 218 / DEBUGGING_RATIO;


    QuizButtonMedia(final File imageFile, final String mediaEnter) {
        super(imageFile);

        media = mediaEnter;

        if (media.length() != 0) {

            groupMedia = new Group();

            ImageView blackBackground = new ImageView(BLACK_BACKGROUND_IMAGE);
            blackBackground.setPreserveRatio(false);
            blackBackground.setFitWidth(TV_SCREEN_WIDTH);
            blackBackground.setFitHeight(TV_SCREEN_HEIGHT);


            ImageView tvScreen = new ImageView(TV_SCREEN_IMAGE);
            tvScreen.setFitWidth(TV_SCREEN_WIDTH);
            tvScreen.setPreserveRatio(true);


            ImageView ivMedia = new ImageView(new Image("file:" + RESOURCES_PATH + "quiz/media/" + media));
            double ratioX = ivMedia.getLayoutBounds().getWidth() / MEDIA_WIDTH_MAX;
            double ratioY = ivMedia.getLayoutBounds().getHeight() / MEDIA_HEIGHT_MAX;
            if (ratioX > ratioY) {
                ivMedia.setFitWidth(MEDIA_WIDTH_MAX);
            } else {
                ivMedia.setFitHeight(MEDIA_HEIGHT_MAX);
            }
            ivMedia.setPreserveRatio(true);
            ivMedia.setLayoutX(tvScreen.getLayoutBounds().getWidth() / 2 - ivMedia.getLayoutBounds().getWidth() / 2);
            ivMedia.setLayoutY(tvScreen.getLayoutBounds().getHeight() / 2 - ivMedia.getLayoutBounds().getHeight() / 2);

            groupMedia.getChildren().addAll(blackBackground, ivMedia, tvScreen);
        }

        this.setOnMouseClicked(event -> {
            QuizButtonMediaAction();
        });

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                QuizButtonMediaAction();
                setTimeWait();
            }
        });
    }

    //действия при нажатии на кнопку Медиа
    private void QuizButtonMediaAction() {

        if (media.length() != 0) {
            setOnPushInvert();

            if (isOnPush()) {
                groupMedia.setLayoutX(GROUP_X);
                groupMedia.setLayoutY(GROUP_Y);
                mainPane.getChildren().add(groupMedia);
            } else {
                mainPane.getChildren().remove(groupMedia);
            }
        }
    }

    //Установка кнопки Медиа в ненажатое состояние
    public void setQuizButtonMediaNonPush() {

        if (isOnPush()) {
            setOnPush(false);
            mainPane.getChildren().remove(groupMedia);
        }
    }


}
