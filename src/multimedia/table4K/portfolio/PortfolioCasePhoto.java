package table4K.portfolio;

import general.ImageViewMoved;

import java.io.File;

import static general.InitialLocation.randomInRange;
import static table4K.ui.MainView.TABLE_HEIGHT;
import static table4K.ui.MainView.TABLE_WIDTH;
import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.portfolio.PortfolioCasePane.PORTFOLIO_CASE_X;
import static table4K.portfolio.PortfolioCasePane.PORTFOLIO_CASE_Y;

public class PortfolioCasePhoto extends ImageViewMoved {

    //максимальная ширина фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_WIDTH_MAX = 746 / DEBUGGING_RATIO;

    //максимальная высота фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_HEIGHT_MAX = 1142 / DEBUGGING_RATIO;

    //отступ слева до фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_X = 36 / DEBUGGING_RATIO;

    //отступ сверху до фотографии в раскрытом личном деле
    private final double PORTFOLIO_PHOTO_Y = 86 / DEBUGGING_RATIO;

    //тени для неподвижной/перемещаемой панели
    private static final String SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);";
    private static final String SHADOW_MOVED = "-fx-effect: dropshadow(gaussian, black, 40, 0, -10, 10);";


    public PortfolioCasePhoto(final File imageFile) {
        super(imageFile);

        this.setFitWidth(PORTFOLIO_PHOTO_WIDTH_MAX);
        this.setFitHeight(PORTFOLIO_PHOTO_HEIGHT_MAX);
        this.setPreserveRatio(true);
        this.setStyle(SHADOW_STILL);

        //если разница между максимальным и реальным размерами фото больше единицы,
        // то положение устанавливается рандомно в заданных пределах
        // в противном случае положение устанавливается по середине заданной области
        if (Math.abs(PORTFOLIO_PHOTO_WIDTH_MAX - this.getLayoutBounds().getWidth()) > 1) {
            this.setLayoutX(randomInRange(PORTFOLIO_PHOTO_X, PORTFOLIO_PHOTO_X + PORTFOLIO_PHOTO_WIDTH_MAX - this.getLayoutBounds().getWidth()));
        } else {
            this.setLayoutX(PORTFOLIO_PHOTO_X + PORTFOLIO_PHOTO_WIDTH_MAX / 2 - this.getLayoutBounds().getWidth() / 2);
        }
        if (Math.abs(PORTFOLIO_PHOTO_HEIGHT_MAX - this.getLayoutBounds().getHeight()) > 1) {
            this.setLayoutY(randomInRange(PORTFOLIO_PHOTO_Y, PORTFOLIO_PHOTO_Y + PORTFOLIO_PHOTO_HEIGHT_MAX - this.getLayoutBounds().getHeight()));
        } else {
            this.setLayoutY(PORTFOLIO_PHOTO_Y + PORTFOLIO_PHOTO_HEIGHT_MAX / 2 - this.getLayoutBounds().getHeight() / 2);
        }

        setRestrCoor(-PORTFOLIO_CASE_X,
                -PORTFOLIO_CASE_Y,
                TABLE_WIDTH - PORTFOLIO_CASE_X,
                TABLE_HEIGHT - PORTFOLIO_CASE_Y);

        this.mouseEvent();
        this.touchEvent();
    }

    @Override
    public void mouseEvent() {
        super.mousePressed();
        super.mouseDragged(SHADOW_MOVED);
        super.mouseReleased(SHADOW_STILL);
    }

    @Override
    public void touchEvent() {
        super.touchPressed();
        super.touchMoved(SHADOW_MOVED);
        super.touchReleased(SHADOW_STILL);
    }

}
