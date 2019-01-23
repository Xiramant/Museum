package table4K.quiz;

import general.ImagePane;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.ui.MainView.DEBUGGING_RATIO;

public class QuizButton extends ImagePane {

    //флаг состояния кнопки (нажата кнопка или нет)
    private boolean onPush = false;

    //месторасположение скина тв кнопки не нажатой
    private static final File BUTTON_BACKGROUND = new File(RESOURCES_PATH + "quiz/tv_button.png");

    //месторасположение скина тв кнопки нажатой
    private static final File BUTTON_BACKGROUND_PUSH = new File(RESOURCES_PATH + "quiz/tv_button_shadow.png");

    //параметры тв кнопки: ширина, шрифт надписи внутри кнопки, цвет надписи внутри кнопки
    private static final double BUTTON_WIDTH =  226 / DEBUGGING_RATIO;
    private static final Font BUTTON_NAME_FONT = Font.font("Arial", FontWeight.BOLD, 83/ DEBUGGING_RATIO);
    private static final Color BUTTON_NAME_COLOR = Color.rgb(30, 30, 30);


    //получение состояния кнопки
    boolean isOnPush() {
        return onPush;
    }

    //установка состояния кнопки по переданному значению
    void setOnPush(final boolean onPush) {
        this.onPush = onPush;
        setButtonSkin();
    }

    //инвертирование состояния кнопки
    void setOnPushInvert() {
        this.onPush = !onPush;
        setButtonSkin();
    }


    //конструктор для кнопки с надписью
    QuizButton(final String name) {
        super(BUTTON_BACKGROUND, BUTTON_WIDTH, 0);

        Text text = new Text(name);
        text.setFont(BUTTON_NAME_FONT);
        text.setFill(BUTTON_NAME_COLOR);
        text.setLayoutX(this.getPrefWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setLayoutY(this.getPrefHeight() / 2 - text.getLayoutBounds().getHeight() / 2);
        text.setTextOrigin(VPos.TOP);

        this.getChildren().add(text);
    }

    //конструктор для кнопки с изображением
    QuizButton(final File imageFile) {
        super(BUTTON_BACKGROUND, BUTTON_WIDTH, 0);

        ImageView iv = new ImageView(new Image("file:" + imageFile));
        iv.setFitWidth(iv.getLayoutBounds().getWidth() / DEBUGGING_RATIO);
        iv.setPreserveRatio(true);
        iv.setLayoutX(this.getPrefWidth() / 2 - iv.getLayoutBounds().getWidth() / 2);
        iv.setLayoutY(this.getPrefHeight() / 2 - iv.getLayoutBounds().getHeight() / 2);

        this.getChildren().add(iv);
    }

    //установка скина кнопки в зависимости от состояния
    private void setButtonSkin() {
        if (isOnPush()) {
            this.setImageBackground(BUTTON_BACKGROUND_PUSH);
        } else {
            this.setImageBackground(BUTTON_BACKGROUND);
        }
    }

}
