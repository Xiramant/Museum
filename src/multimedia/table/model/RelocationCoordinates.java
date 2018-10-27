package table.model;

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

    public RelocationCoordinates() {
        this.xBegin = 0;
        this.yBegin = 0;
        this.xDelta = 0;
        this.yDelta = 0;
    }
}
