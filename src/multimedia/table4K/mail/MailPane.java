package table4K.mail;

import general.ImagePaneIteration;

import java.io.File;
import java.util.ArrayList;

public class MailPane extends ImagePaneIteration {

    public MailPane (final ArrayList<File> imageFilesEnter, final double wMax, final double hMax) {
        super(imageFilesEnter, wMax, hMax);

        this.ipiMouseDragAndDrop();
        this.ipiMouseClicked();
        this.ipiTouch();

        this.setStyle(
                "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
        );
    }

    @Override
    public void ipiMouseDragAndDrop() {
        super.mousePressed();
        super.mouseDragged("-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);");
        super.mouseReleased("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);");
    }

    @Override
    public void ipiTouch() {
        super.TouchPressed();
        super.TouchMoved("-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);");
        super.TouchReleased("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);");
    }

}
