package table4K.view.portfolio;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class PortfolioIcon implements IDisplayIcon {

    private static final Image PORTFOLIO_IMAGE = IconData.getPortfolioIconImage();
    private static final double PORTFOLIO_ICON_WIDTH = 752 / DEBUGGING_RATIO;
    private static final double PORTFOLIO_ICON_X = 1899 / DEBUGGING_RATIO;
    private static final double PORTFOLIO_ICON_Y = 556 / DEBUGGING_RATIO;
    private static final String PORTFOLIO_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 5);";


    public Image getIconImage() {
        return PORTFOLIO_IMAGE;
    }

    public double getIconWidth() {
        return PORTFOLIO_ICON_WIDTH;
    }

    public double getIconX() {
        return PORTFOLIO_ICON_X;
    }

    public double getIconY() {
        return PORTFOLIO_ICON_Y;
    }

    public String getIconStyle() {
        return PORTFOLIO_SHADOW;
    }

}
