package table4K.model.imagePresentation;

import javafx.scene.image.ImageView;



public class ImageViewIterationWithMove extends ImageIterationWithMove<ImageView> {

    public static class Builder extends ImageIterationWithMove.Builder<ImageView, Builder> {

        public Builder(final ImageIteration<ImageView> imageIterationArg) {
            super(imageIterationArg);
        }

        @Override
        public ImageViewIterationWithMove build() {
            return new ImageViewIterationWithMove(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private ImageViewIterationWithMove(final Builder builderArg) {
        super(builderArg);
    }

}
