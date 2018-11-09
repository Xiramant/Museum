package table4K.portfolio;

import general.ImagePane;

import java.io.File;

import static table4K.Main4K.debuggingRatio;

public class PortfolioPane extends ImagePane {

    private final static double PORTFOLIO_PANE_WIDTH_MAX = 1372 / debuggingRatio;

    public PortfolioPane(final File background) {
        super(background, PORTFOLIO_PANE_WIDTH_MAX, 0);

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -1, 3);");
    }
}
