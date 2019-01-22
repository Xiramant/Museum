package table4K.ui.icon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icon extends ImageView {

    public Icon(IconConst iconConst) {
        super(new Image(iconConst.getIconUrl()));
        this.setPreserveRatio(true);
        this.setFitWidth(iconConst.getIconWidth());
        this.setLayoutX(iconConst.getIconX());
        this.setLayoutY(iconConst.getIconY());
        this.setStyle(iconConst.getIconStyle());
    }
}
