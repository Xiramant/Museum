package general;

public class RestrictionCoordinates {

    private static double notDetermined = -10_000;

    private double left;

    private double top;

    private double right;

    private double bottom;

    public static double getNotDetermined() {
        return notDetermined;
    }

    public double getLeft() {
        return left;
    }

    public double getTop() {
        return top;
    }

    public double getRight() {
        return right;
    }

    public double getBottom() {
        return bottom;
    }

    public RestrictionCoordinates(final double left, final double top, final double right, final double bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public RestrictionCoordinates() {
        this(notDetermined, notDetermined, notDetermined, notDetermined);
    }

    public void setRestrictionCoordinates(final double left, final double top, final double right, final double bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
}
