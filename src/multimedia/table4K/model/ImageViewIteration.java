package table4K.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;

import java.util.ArrayList;
import java.util.Iterator;

import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;


//Класс для отображения следующего изображения
// из списка переданных изображений
// при клике/тапе на изображении.
//Отображение зациклено:
// при окончании списка следующим отображется первое изображение.
public class ImageViewIteration extends ImageView {

    private ArrayList<Image> _images;

    private Iterator<Image> _imagesIterator;



    public ImageViewIteration(final ArrayList<Image> imagesArg) {
        assert (imagesArg != null);

        this._images = imagesArg;
        this._imagesIterator = _images.listIterator();
        setNextImage();

        setEvent();
    }



    private void setEvent() {
        setMouseEvent();
        setTouchEvent();
    }

    private void setMouseEvent() {
        this.setOnMouseReleased(this :: clickAction);
    }

    private void setTouchEvent() {
        this.setOnTouchReleased(this :: clickAction);
    }

    private void clickAction(final InputEvent eventArg) {
        assert (_images.size() != 0);

        //если в списке только 1 картинка
        // то пролистывать ее нет смысла
        if (_images.size() == 1) return;

        if (isEventPermission(eventArg)) {
            setNextImage();
            eventDelayBegin();
        }
    }

    private void setNextImage() {
        if (!_imagesIterator.hasNext()) {
            _imagesIterator = _images.listIterator(0);
        }

        Rectangle oldDimensions = new Rectangle(this.getLayoutBounds().getWidth(),
                                                this.getLayoutBounds().getHeight());
        this.setImage(_imagesIterator.next());
        saveCenterLocation(oldDimensions);
    }

    private void saveCenterLocation(final Rectangle oldDimensionsArg) {
        assert (oldDimensionsArg == null);

        double xChange = getDimensionChange(oldDimensionsArg.getWidth(),
                                            this.getLayoutBounds().getWidth());

        double yChange = getDimensionChange(oldDimensionsArg.getHeight(),
                                            this.getLayoutBounds().getHeight());

        if (dimensionsNotChange(xChange, yChange)) return;

        this.setLayoutX(this.getLayoutX() + xChange);
        this.setLayoutY(this.getLayoutY() + yChange);
    }

    private double getDimensionChange(final double oldDimensionArg,
                                      final double currentDimensionArg) {
        return (oldDimensionArg / 2 - currentDimensionArg / 2);
    }

    private boolean dimensionsNotChange(final double xChangeArg,
                                        final double yChangeArg) {
        return (xChangeArg == 0 && yChangeArg == 0);
    }

}
