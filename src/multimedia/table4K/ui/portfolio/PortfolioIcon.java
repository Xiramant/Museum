package table4K.ui.portfolio;

import table4K.ui.IDisplayIcon;

import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class PortfolioIcon implements IDisplayIcon {

    private static final String PORTFOLIO_URL = "file:///" + RESOURCES_PATH + "icon/portfolio_icon.png";
    private static final double PORTFOLIO_ICON_WIDTH = 752 / DEBUGGING_RATIO;
    private static final double PORTFOLIO_ICON_X = 1899 / DEBUGGING_RATIO;
    private static final double PORTFOLIO_ICON_Y = 556 / DEBUGGING_RATIO;
    private static final String PORTFOLIO_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 5);";

    public String getIconUrl() {
        return PORTFOLIO_URL;
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
