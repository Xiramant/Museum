package table4K.model;



public class Area {

    private double xBegin;

    private double xEnd;

    private double yBegin;

    private double yEnd;



    public double getXBegin() {
        return xBegin;
    }

    public double getXEnd() {
        return xEnd;
    }

    public double getYBegin() {
        return yBegin;
    }

    public double getYEnd() {
        return yEnd;
    }



    public Area(final double xBeginArg,
                final double xEndArg,
                final double yBeginArg,
                final double yEndArg) {
        xBegin = xBeginArg;
        xEnd = xEndArg;
        yBegin = yBeginArg;
        yEnd = yEndArg;
    }

}
