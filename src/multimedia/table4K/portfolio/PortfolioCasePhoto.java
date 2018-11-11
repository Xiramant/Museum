package table4K.portfolio;

import general.ImageViewController;

import java.io.File;

import static table4K.Main4K.debuggingRatio;

public class PortfolioCasePhoto extends ImageViewController{

    //максимальная ширина фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_WIDTH_MAX = 746 / debuggingRatio;

    //максимальная высота фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_HEIGHT_MAX = 1142 / debuggingRatio;

    //отступ слева до фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_X = 36 / debuggingRatio;

    //отступ сверху до фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_y = 86 / debuggingRatio;

    public PortfolioCasePhoto(final File imageFile) {
        super(imageFile);

        this.setFitWidth(PORTFOLIO_PHOTO_WIDTH_MAX);
        this.setFitHeight(PORTFOLIO_PHOTO_HEIGHT_MAX);
        this.setPreserveRatio(true);
        this.setStyle("-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);");
        this.setLayoutX(PORTFOLIO_PHOTO_X + PORTFOLIO_PHOTO_WIDTH_MAX / 2 - this.getLayoutBounds().getWidth() / 2);
        this.setLayoutY(PORTFOLIO_PHOTO_y + PORTFOLIO_PHOTO_HEIGHT_MAX / 2 - this.getLayoutBounds().getHeight() / 2);

        this.ivcMouseDragAndDrop();
        this.ivcTouchDragAndDrop();
    }

    @Override
    public void ivcMouseDragAndDrop() {
        super.ivcMousePressed();
        super.ivcMouseDragged("-fx-effect: dropshadow(gaussian, black, 40, 0, -10, 10);");
        super.ivcMouseReleased("-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);");
    }

    @Override
    public void ivcTouchDragAndDrop() {
        super.ivcTouchPressed();
        super.ivcTouchMoved("-fx-effect: dropshadow(gaussian, black, 40, 0, -10, 10);");
        super.ivcTouchReleased("-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);");
    }

}
