package table4K.view.video;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import table4K.model.Dimension;

import static table4K.data.video.VideoData.getVideoBoxImage;
import static table4K.view.MainView.DEBUGGING_RATIO;



public class VideoBox extends Group implements Dimension {

    private static final Image VIDEO_BOX_ICON = getVideoBoxImage();
    private static final double VIDEO_BOX_WIDTH = 600 / DEBUGGING_RATIO;
    private static final String VIDEO_BOX_SHADOW = "-fx-effect: dropshadow(gaussian, black, 20, 0.3, -3, 10);";

    private static final Font VIDEO_NAME_FONT = Font.font("Arial Narrow", FontWeight.BOLD, 36 / DEBUGGING_RATIO);
    private static final Color VIDEO_NAME_COLOR = Color.rgb(52, 54, 70);
    private static final double VIDEO_NAME_WIDTH_MAX = 240 / DEBUGGING_RATIO;
    private static final double VIDEO_NAME_X = 309 / DEBUGGING_RATIO;
    private static final double VIDEO_NAME_Y = 294 / DEBUGGING_RATIO;



    public VideoBox(final String videoNameArg){
        ImageView videoBoxIcon = getVideoBoxImageView();
        Text videoNameText = getVideoNameText(videoNameArg);
        this.getChildren().addAll(videoBoxIcon, videoNameText);
    }

    private static ImageView getVideoBoxImageView() {
        ImageView out = new ImageView(VIDEO_BOX_ICON);
        out.setPreserveRatio(true);
        out.setFitWidth(VIDEO_BOX_WIDTH);
        out.setStyle(VIDEO_BOX_SHADOW);
        return out;
    }

    private static Text getVideoNameText(final String videoNameArg) {
        Text out = new Text(videoNameArg);
        out.setFont(VIDEO_NAME_FONT);
        out.setFill(VIDEO_NAME_COLOR);
        out.setWrappingWidth(VIDEO_NAME_WIDTH_MAX);
        out.setTextAlignment(TextAlignment.CENTER);
        out.setTextOrigin(VPos.CENTER);
        out.setX(VIDEO_NAME_X - out.getLayoutBounds().getWidth() / 2);
        out.setY(VIDEO_NAME_Y);
        return out;
    }

    @Override
    public double getWidthWithScaling() {
        return this.getLayoutBounds().getWidth() * this.getScaleX();
    }

    @Override
    public double getHeightWithScaling() {
        return this.getLayoutBounds().getHeight() * this.getScaleY();
    }

    @Override
    public void setX(final double xArg) {
        this.setLayoutX(xArg);
    }

    @Override
    public void setY(final double yArg) {
        this.setLayoutY(yArg);
    }

    @Override
    public void setScaling(final double scaleArg) {
        this.setScaleX(scaleArg);
        this.setScaleY(scaleArg);
    }
}