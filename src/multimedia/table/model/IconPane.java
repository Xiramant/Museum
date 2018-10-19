package table.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import static table.Main.RESOURCES_PATH;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class IconPane extends Pane {

    public IconPane(final String sectionKeyWord) {

        Image iconImage = new Image("file:" + RESOURCES_PATH + sectionKeyWord + "/" + sectionKeyWord + "_icon.png");

        this.setPrefWidth(iconImage.getWidth());
        this.setMinWidth(this.getPrefWidth());
        this.setMaxWidth(this.getPrefWidth());
        this.setPrefHeight(iconImage.getHeight());
        this.setMinHeight(this.getPrefHeight());
        this.setMaxHeight(this.getPrefHeight());

        BackgroundSize iconBackgroundSize = new BackgroundSize(iconImage.getWidth(), iconImage.getHeight(), false, false, false, false);
        BackgroundImage iconBackground = new BackgroundImage(iconImage, NO_REPEAT, NO_REPEAT, CENTER, iconBackgroundSize);
        this.setBackground(new Background(iconBackground));
    }
}
