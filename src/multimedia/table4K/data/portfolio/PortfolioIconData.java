package table4K.data.portfolio;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;


public class PortfolioIconData implements IIconData {

    private Image portfolioIcon = new Image("file:///" + RESOURCES_PATH + "icon/portfolio_icon.png");

    public Image getIconImage() {
        return portfolioIcon;
    }

}