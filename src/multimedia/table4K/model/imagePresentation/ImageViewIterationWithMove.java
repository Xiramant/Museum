package table4K.model.imagePresentation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ImageViewIterationWithMove extends ImageIterationWithMove<ImageView> {

    public ImageViewIterationWithMove(final ArrayList<Image> imagesArg, final Restriction restrictionAreaArg) {
        super(new ImageViewIteration(imagesArg), restrictionAreaArg);
    }

}
