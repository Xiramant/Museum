package table4K.medal;

import general.ImageViewController;

import java.io.File;

import static table4K.Main4K.debuggingRatio;
import static table4K.medal.Medal.DESCRIPTION_HEIGHT;
import static table4K.medal.Medal.DESCRIPTION_WIDTH;
import static table4K.medal.MedalElement.RATIO_MEDAL_OR_ORDEN;

public class MedalDescriptionImage extends ImageViewController {

    //максимальная высота изображения медали для слайдера,
    // а также ордена, похожего на медаль
    private static final double MEDAL_IMAGE_HEIGHT_MAX = 800 / debuggingRatio;

    //максимальная высота изображения медали для ордена
    private static final double ORDEN_IMAGE_HEIGHT_MAX = 600 / debuggingRatio;

    //ограничения на расположение
    private static final double LEFT = 0 / debuggingRatio;
    private static final double TOP = 0 / debuggingRatio;

    //тени для неподвижной/перемещаемой панели
    private static final String SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);";
    private static final String SHADOW_MOVED = "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);";


    MedalDescriptionImage(final File fileImage) {
        super(fileImage);

        this.setFlagDragAndDrop(true);

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

        this.ivcMouseDragAndDrop();
        this.ivcTouchDragAndDrop();

        this.setStyle(SHADOW_STILL);
    }

    @Override
    public void ivcMouseDragAndDrop() {
        super.ivcMousePressed();
        super.ivcMouseDragged(SHADOW_MOVED);
        super.ivcMouseReleased(SHADOW_STILL);
    }

    @Override
    public void ivcTouchDragAndDrop() {
        super.ivcTouchPressed();
        super.ivcTouchMoved(SHADOW_MOVED);
        super.ivcTouchReleased(SHADOW_STILL);
    }

}
