package table4K.model.imagePresentation;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;



public class ImageViewIteration extends ImageIteration<ImageView> {

    public ImageViewIteration(final ArrayList<Image> imagesArg) {
        super(imagesArg, new ImageView());
    }



    @Override
    protected void setNextImage() {

        Dimension2D oldDimensions = new Dimension2D(this.getImageRepresentation().getLayoutBounds().getWidth(),
                                                this.getImageRepresentation().getLayoutBounds().getHeight());
        this.getImageRepresentation().setImage(this.getImagesIterator().next());
        saveCenterLocation(oldDimensions);
    }

    private void saveCenterLocation(final Dimension2D oldDimensionsArg) {
        assert (oldDimensionsArg != null);

        double xChange = getDimensionChange(oldDimensionsArg.getWidth(),
                                            this.getImageRepresentation().getLayoutBounds().getWidth());

        double yChange = getDimensionChange(oldDimensionsArg.getHeight(),
                                            this.getImageRepresentation().getLayoutBounds().getHeight());

        if (dimensionsNotChange(xChange, yChange)) return;

        this.getImageRepresentation().setLayoutX(this.getImageRepresentation().getLayoutX() + xChange);
        this.getImageRepresentation().setLayoutY(this.getImageRepresentation().getLayoutY() + yChange);
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
