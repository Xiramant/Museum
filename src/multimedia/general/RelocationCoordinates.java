package general;

public class RelocationCoordinates {

    private double xBegin;

    private double yBegin;

    private double xDelta;

    private double yDelta;

    public double getXBegin() {
        return xBegin;
    }

    public void setXBegin(final double xBegin) {
        this.xBegin = xBegin;
    }

    public double getXDelta() {
        return xDelta;
    }

    public void setXDelta(final double xDelta) {
        this.xDelta = xDelta;
    }

    public double getYBegin() {
        return yBegin;
    }

    public void setYBegin(final double yBegin) {
        this.yBegin = yBegin;
    }

    public double getYDelta() {
        return yDelta;
    }

    public void setYDelta(final double yDelta) {
        this.yDelta = yDelta;
    }

    public RelocationCoordinates(final double xBegin, final double yBegin) {
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.xDelta = 0;
        this.yDelta = 0;
    }

    public RelocationCoordinates() {
        this(0,0);
    }


}
