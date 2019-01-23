package table4K.medal;

import general.ImageViewMoved;

import java.io.File;

import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.medal.Medal.DESCRIPTION_HEIGHT;
import static table4K.medal.Medal.DESCRIPTION_WIDTH;
import static table4K.medal.MedalElement.RATIO_MEDAL_OR_ORDEN;

public class MedalDescriptionImage extends ImageViewMoved {

    //максимальная высота изображения медали для слайдера,
    // а также ордена, похожего на медаль
    private static final double MEDAL_IMAGE_HEIGHT_MAX = 800 / DEBUGGING_RATIO;

    //максимальная высота изображения медали для ордена
    private static final double ORDEN_IMAGE_HEIGHT_MAX = 600 / DEBUGGING_RATIO;

    //ограничения на расположение
    private static final double LEFT = 0 / DEBUGGING_RATIO;
    private static final double TOP = 0 / DEBUGGING_RATIO;

    //тени для неподвижной/перемещаемой панели
    private static final String SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);";
    private static final String SHADOW_MOVED = "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);";


    MedalDescriptionImage(final File fileImage) {
        super(fileImage);

        double ratio = this.getLayoutBounds().getHeight() / this.getLayoutBounds().getWidth();
        if (ratio > RATIO_MEDAL_OR_ORDEN) {
            this.setFitHeight(MEDAL_IMAGE_HEIGHT_MAX);
        } else {
            this.setFitHeight(ORDEN_IMAGE_HEIGHT_MAX);
        }

        this.setPreserveRatio(true);

        //!!! setRestrCoor должно определяться
        // до вызова обработки событий щелчка мыши и тача
        this.setRestrCoor(LEFT,
                        TOP,
                        DESCRIPTION_WIDTH,
                        DESCRIPTION_HEIGHT);

        this.mouseEvent();
        this.touchEvent();

        this.setStyle(SHADOW_STILL);
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
