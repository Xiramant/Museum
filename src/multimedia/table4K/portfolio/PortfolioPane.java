package table4K.portfolio;

import general.ImagePane;

import java.io.File;

public class PortfolioPane extends ImagePane {

    public static double PORTFOLIO_PANE_WIDTH_MAX = 1372;

    public PortfolioPane(final File background) {
        super(background, PORTFOLIO_PANE_WIDTH_MAX, 0);

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 3);");
    }
}
