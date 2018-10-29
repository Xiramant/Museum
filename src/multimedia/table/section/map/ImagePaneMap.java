package table.section.map;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import table.model.ImagePaneSection;

import java.io.File;
import java.util.ArrayList;

public class ImagePaneMap extends ImagePaneSection{

    //отображение текста количества карт
    private Text countPageText = new Text();

    //отступ сверху для блока количество карт
    private final double TEXT_TOP_PADDING = 7;

    //отступ сверху для блока количество карт
    private final double TEXT_LEFT_PADDING = 12;

    //шрифт блока количество карт
    private final Font COUNT_PAGE_FONT = new Font("Book Antiqua Bold Italic", 10);

    public ImagePaneMap(final ArrayList<File> imageFiles) {
        super(imageFiles);

//        countPageText.setWrappingWidth(this.getPrefWidth() * 0.5);
        countPageText.setFont(COUNT_PAGE_FONT);
        countPageText.setX(TEXT_LEFT_PADDING);
        countPageText.setTextAlignment(TextAlignment.LEFT);
        countPageText.setY(TEXT_TOP_PADDING);
        countPageText.setTextOrigin(VPos.TOP);

        setCountPageText();

        Rectangle r = new Rectangle(8, 5, countPageText.getLayoutBounds().getWidth() + 8,15);
        r.setFill(Color.WHITE);

        this.getChildren().addAll(r, countPageText);
    }

    @Override
    //установка следующего фонового изображения
    public void setNextImageBackground() {
        super.setNextImageBackground();
        setCountPageText();
    }

    @Override
    //установка предыдущего фонового изображения из списка imageFiles
    public void setPrevImageBackground() {
        super.setPrevImageBackground();
        setCountPageText();
    }

    @Override
    //установка первого фонового изображения из списка imageFiles
    public void setFirstImageBackground() {
        super.setFirstImageBackground();
        setCountPageText();
    }

    @Override
    //установка последнего фонового изображения из списка imageFiles
    public void setLastImageBackground() {
        super.setLastImageBackground();
        setCountPageText();
    }

    private void setCountPageText() {
        countPageText.setText("карта: " + (super.getCurrentBackgroundIndex() + 1) + " / " + (super.getLastBackgroundIndex() + 1));
    }
}
