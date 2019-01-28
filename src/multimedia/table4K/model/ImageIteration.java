package table4K.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
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
//В качестве типа "E" планируется использовать ImageView и Pane.
abstract public class ImageIteration<E extends Node> {

    private ArrayList<Image> _images;

    private Iterator<Image> _imagesIterator;

    private E _imagePresentation;



    protected Iterator<Image> getImagesIterator() {
        return _imagesIterator;
    }

    public E getImagePresentation() {
        return _imagePresentation;
    }



    protected ImageIteration(final ArrayList<Image> imagesArg, final E imagePresentationArg) {
        assert (imagesArg != null);

        this._images = imagesArg;
        this._imagesIterator = _images.listIterator();
        _imagePresentation = imagePresentationArg;
        setNextImage();

        setEvent();
    }



    private void setEvent() {
        setMouseEvent();
        setTouchEvent();
    }

    private void setMouseEvent() {
        _imagePresentation.setOnMouseReleased(this :: clickAction);
    }

    private void setTouchEvent() {
        _imagePresentation.setOnTouchReleased(this :: clickAction);
    }

    private void clickAction(final InputEvent eventArg) {
        assert (_images.size() != 0);

        if (_images.size() == 1) return;

        if (isEventPermission(eventArg)) {
            setNextImageLoop();
            eventDelayBegin();
        }
    }

    private void setNextImageLoop() {
        if (!_imagesIterator.hasNext()) {
            _imagesIterator = _images.listIterator(0);
        }

        setNextImage();
    }

    abstract protected void setNextImage();

}