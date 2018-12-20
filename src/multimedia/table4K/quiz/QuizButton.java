package table4K.quiz;

import general.ArrayListIndex;
import general.ImagePane;
import general.ImagePaneIteration;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;

public class QuizButton extends ImagePane {

    //флаг нажата ли кнопка
    private boolean onPush = false;

    //месторасположение скина тв кнопки
    private static final File BUTTON_BACKGROUND = new File(RESOURCES_PATH + "quiz/tv_button.png");

    //ширина тв кнопки
    private static final double BUTTON_WIDTH =  226 / debuggingRatio;

    private static final Font BUTTON_NAME_FONT = Font.font("Arial", FontWeight.BOLD, 83/debuggingRatio);

    private static final Color BUTTON_NAME_COLOR = Color.rgb(30, 30, 30);


    public boolean isOnPush() {
        return onPush;
    }

    public void setOnPush(final boolean onPush) {
        this.onPush = onPush;
        setButtonSkin();
    }

    public void setOnPushInvert() {
        this.onPush = !onPush;
        setButtonSkin();
    }

    public QuizButton(final String name) {
        super(BUTTON_BACKGROUND, BUTTON_WIDTH, 0);

        Text text = new Text(name);
        text.setFont(BUTTON_NAME_FONT);
        text.setFill(BUTTON_NAME_COLOR);
        text.setLayoutX(this.getPrefWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setLayoutY(this.getPrefHeight() / 2 - text.getLayoutBounds().getHeight() / 2);
        text.setTextOrigin(VPos.TOP);

        this.getChildren().add(text);
    }

    public QuizButton(final File imageFile) {
        super(BUTTON_BACKGROUND, BUTTON_WIDTH, 0);

        ImageView iv = new ImageView(new Image("file:" + imageFile));
        iv.setFitWidth(iv.getLayoutBounds().getWidth() / debuggingRatio);
        iv.setPreserveRatio(true);
        iv.setLayoutX(this.getPrefWidth() / 2 - iv.getLayoutBounds().getWidth() / 2);
        iv.setLayoutY(this.getPrefHeight() / 2 - iv.getLayoutBounds().getHeight() / 2);

        this.getChildren().add(iv);
    }

    public void setButtonSkin() {
        if (isOnPush()) {
            this.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button_shadow.png"));
        } else {
            this.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button.png"));
        }
    }

}
