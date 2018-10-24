package table.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.File;

import static table.Main.RESOURCES_PATH;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class ImagePane extends Pane {

    //Дефолтный конструктор
    // (нужен, чтобы не ругалась реализация расширяющего класса - ImagePaneSection)
    public ImagePane() {
        super();
    }

    //Основной используемый конструктор по передаваемому изображению
    public ImagePane(final Image image) {

        setImageBackground(image);
    }

    //Конструктор на основе передаваемого ключевого слова
    public ImagePane(final SectionKey sectionKey) {

        this(new Image("file:" + RESOURCES_PATH + sectionKey.getKeyWord() + "/" + sectionKey.getKeyWord() + "_icon.png"));
    }

    //Конструктор на основе передаваемого файла изображения для фона
    public ImagePane(final File imageFile) {

        this(new Image("file:" + imageFile.toString()));
    }

    //установка фона с определенными параметрами по передаваемому изображению
    public void setImageBackground(final Image image) {

        this.setPrefWidth(image.getWidth());
        this.setWidth(this.getPrefWidth());
        this.setMinWidth(this.getPrefWidth());
        this.setMaxWidth(this.getPrefWidth());
        this.setPrefHeight(image.getHeight());
        this.setHeight(this.getPrefHeight());
        this.setMinHeight(this.getPrefHeight());
        this.setMaxHeight(this.getPrefHeight());

        BackgroundSize size = new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, false);
        BackgroundImage background = new BackgroundImage(image, NO_REPEAT, NO_REPEAT, CENTER, size);
        this.setBackground(new Background(background));

        this.setStyle(
                "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
        );
    }

    //установка фона по передаваемому файлу изображении фона
    public void setImageBackground(final File imageFile) {

        setImageBackground(new Image("file:" + imageFile.toString()));
    }

}
