package general;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.File;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static table4K.Main4K.*;

public class ImagePane extends Pane {

    //Основной используемый конструктор по передаваемому изображению
    // и ограничениями на максимальный размер
    public ImagePane(final Image image, final double wMax, final double hMax) {

        this.setMaxWidth((wMax == 0d)? TABLE_WIDTH: wMax);
        this.setMaxHeight((hMax == 0d)? TABLE_HEIGHT: hMax);

        setImageBackground(image);
    }

    //Конструктор по передаваемому изображению
    // без задания ограничений на максимальный размер
    public ImagePane(final Image image) {
        this(image, 0d, 0d);
    }

    //Конструктор на основе передаваемого ключевого слова
    // и ограничениями на максимальный размер
    public ImagePane(final SectionKey sectionKey, final double widthMax, final double heightMax) {
        this(new Image("file:" + RESOURCES_PATH + sectionKey.getKeyWord() + "/" + sectionKey.getKeyWord() + "_icon.png"), widthMax, heightMax);
    }

    //Конструктор на основе передаваемого файла изображения
    // и ограничениями на максимальный размер
    public ImagePane(final File imageFile, final double wMax, final double hMax) {
        this(new Image("file:" + imageFile.toString()), wMax, hMax);
    }

    //Конструктор на основе передаваемого файла изображения
    // без задания ограничений на максимальный размер
    public ImagePane(final File imageFile) {
        this(imageFile, 0d, 0d);
    }

    //установка фона с определением размера панели
    // на основании размера изображения фона и ограничений на максимальный размер
    public void setImageBackground(final Image image) {

        this.setPrefWidth((this.getMaxWidth() < image.getWidth())? this.getMaxWidth(): image.getWidth());
        this.setPrefHeight((this.getMaxHeight() < image.getHeight())? this.getMaxHeight(): image.getHeight());

        double widthRatio = this.getMaxWidth() / image.getWidth();
        double heightRatio = this.getMaxHeight() / image.getHeight();

        if (widthRatio < 1 && widthRatio < heightRatio) {
            this.setPrefHeight(image.getHeight() * widthRatio);
        }

        if (heightRatio < 1 && heightRatio < widthRatio) {
            this.setPrefWidth(image.getWidth() * heightRatio);
        }

        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, false);
        BackgroundImage background = new BackgroundImage(image, NO_REPEAT, NO_REPEAT, CENTER, size);
        this.setBackground(new Background(background));
    }

    //установка фона по передаваемому файлу изображении фона
    public void setImageBackground(final File imageFile) {
        setImageBackground(new Image("file:" + imageFile.toString()));
    }

}
