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

    private ArrayListIndex<File> imageFiles = new ArrayListIndex<>();

    private TwoPoint twoPoint= new TwoPoint();

    public TwoPoint getTwoPoint() {
        return twoPoint;
    }

    public void setTwoPoint(final TwoPoint twoPoint) {
        this.twoPoint = twoPoint;
    }

    public void clearTwoPoint() {
        setTwoPoint(new TwoPoint());
    }


    public ArrayListIndex<File> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(final ArrayListIndex<File> imageFiles) {
        this.imageFiles.addAll(imageFiles);
    }

    public ImagePane(final ArrayListIndex<File> imageFiles) {

        setImageFiles(imageFiles);

        this.setNextImageBackground();
    }

    public ImagePane(final Image image) {

        Image iconImage = image;

        setImageBackground(image);
    }

    public ImagePane(final SectionKey sectionKey) {

        this(new Image("file:" + RESOURCES_PATH + sectionKey.getKeyWord() + "/" + sectionKey.getKeyWord() + "_icon.png"));
    }

    public ImagePane(final File imageFile) {

        this(new Image("file:" + imageFile.toString()));
    }

    
    public void setImageBackground(final File imageFile) {

        setImageBackground(new Image("file:" + imageFile.toString()));
    }

    private void setImageBackground(final Image image) {

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

    public void setNextImageBackground() {

        if (imageFiles.hasNextElement()) {
            setImageBackground(new Image("file:" + imageFiles.getNextElement().toString()));
        }
    }

    public void setPrevImageBackground() {

        if (imageFiles.hasPrevElement()) {
            setImageBackground(new Image("file:" + imageFiles.getPrevElement().toString()));
        }
    }


}
