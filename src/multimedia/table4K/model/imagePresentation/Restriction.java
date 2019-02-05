package table4K.model.imagePresentation;



public class Restriction {

    private static final double NOT_DETERMINED = -10_000;

    private static final double ACCURACY = 0.01;

    private double left;

    private double top;

    private double right;

    private double bottom;



    public static double getNotDetermined() {
        return NOT_DETERMINED;
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



    public Restriction(final double leftArg,
                       final double topArg,
                       final double rightArg,
                       final double bottomArg) {
        this.left = leftArg;
        this.top = topArg;
        this.right = rightArg;
        this.bottom = bottomArg;
    }

    public Restriction() {
        this(NOT_DETERMINED, NOT_DETERMINED, NOT_DETERMINED, NOT_DETERMINED);
    }



    public static boolean determined(final double paddingArg) {
        return Math.abs(paddingArg - NOT_DETERMINED) > ACCURACY;
    }

}
