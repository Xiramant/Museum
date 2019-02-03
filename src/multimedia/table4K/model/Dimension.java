package table4K.model;



public interface Dimension {

    double getWidthWithScaling();

    double getHeightWithScaling();

    void setX(final double xArg);

    void setY(final double yArg);

    void setScaling(final double scaleArg);

}