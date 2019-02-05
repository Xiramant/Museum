package table4K.model.imagePresentation;



public class Restriction {

    private static final double NOT_DETERMINED = -10_000;
    private static final double ACCURACY = 0.01;

    private final double xBegin;
    private final double yBegin;
    private final double xEnd;
    private final double yEnd;



    double getXBegin() {
        return xBegin;
    }
    double getYBegin() {
        return yBegin;
    }
    double getXEnd() {
        return xEnd;
    }
    double getYEnd() {
        return yEnd;
    }



    public static class Builder{

        // Optional parameters
        private double xBegin = NOT_DETERMINED;
        private double yBegin = NOT_DETERMINED;
        private double xEnd = NOT_DETERMINED;
        private double yEnd = NOT_DETERMINED;



        public Builder xBegin(final double xBeginArg) {
            xBegin = xBeginArg;
            return this;
        }

        public Builder yBegin(final double yBeginArg) {
            yBegin = yBeginArg;
            return this;
        }

        public Builder xEnd(final double xEndArg) {
            xEnd = xEndArg;
            return this;
        }

        public Builder yEnd(final double yEndArg) {
            yEnd = yEndArg;
            return this;
        }

        public Restriction build() {
            return new Restriction(this);
        }
    }



    private Restriction(final Builder builderArg) {
        xBegin = builderArg.xBegin;
        yBegin = builderArg.yBegin;
        xEnd = builderArg.xEnd;
        yEnd = builderArg.yEnd;
    }



    public static boolean determined(final double paddingArg) {
        return Math.abs(paddingArg - NOT_DETERMINED) > ACCURACY;
    }

}
