package table4K.model.imagePresentation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;



public class ImageViewIteration extends ImageIteration<ImageView> {

    public ImageViewIteration(final ArrayList<Image> imagesArg) {
        super(imagesArg, new ImageView());
    }



    @Override
    protected void setNextImage() {

        Rectangle oldDimensions = new Rectangle(this.getImagePresentation().getLayoutBounds().getWidth(),
                                                this.getImagePresentation().getLayoutBounds().getHeight());
        this.getImagePresentation().setImage(this.getImagesIterator().next());
        saveCenterLocation(oldDimensions);
    }

    private void saveCenterLocation(final Rectangle oldDimensionsArg) {
        assert (oldDimensionsArg != null);

        double xChange = getDimensionChange(oldDimensionsArg.getWidth(),
                                            this.getImagePresentation().getLayoutBounds().getWidth());

        double yChange = getDimensionChange(oldDimensionsArg.getHeight(),
                                            this.getImagePresentation().getLayoutBounds().getHeight());

        if (dimensionsNotChange(xChange, yChange)) return;

        this.getImagePresentation().setLayoutX(this.getImagePresentation().getLayoutX() + xChange);
        this.getImagePresentation().setLayoutY(this.getImagePresentation().getLayoutY() + yChange);
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
