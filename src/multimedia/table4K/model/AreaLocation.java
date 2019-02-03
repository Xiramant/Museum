package table4K.model;

import javafx.scene.shape.Rectangle;



public class AreaLocation extends Rectangle {

    private double minHorizontalSpacing;

    private double minVerticalSpacing;



    public double getMinHorizontalSpacing() {
        return minHorizontalSpacing;
    }

    public double getMinVerticalSpacing() {
        return minVerticalSpacing;
    }



    public AreaLocation(final double xBeginArg,
                        final double yBeginArg,
                        final double widthArg,
                        final double heightArg,
                        final double minHorizontalSpacingArg,
                        final double minVerticalSpacingArg) {
        super(xBeginArg, yBeginArg, widthArg, heightArg);
        minHorizontalSpacing = minHorizontalSpacingArg;
        minVerticalSpacing = minVerticalSpacingArg;
    }

}
