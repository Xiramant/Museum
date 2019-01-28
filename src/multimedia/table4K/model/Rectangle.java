package table4K.model;

public class Rectangle {

    private final double _WIDTH;

    private final double _HEIGHT;

    Rectangle(final double widthArg,
              final double heightArg) {
        _WIDTH = widthArg;
        _HEIGHT = heightArg;
    }

    public double getWidth() {
        return _WIDTH;
    }

    public double getHeight() {
        return _HEIGHT;
    }

}