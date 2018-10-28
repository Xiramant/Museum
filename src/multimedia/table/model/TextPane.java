package table.model;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;

public class TextPane extends ImagePane {

    //текст, который нужно листать в текстовой панели
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    private final double TEXT_HEIGHT = 330;

    private final char LINE_FEED = 10;

    private final char SPACE = 32;

    public ArrayListIndex<String> getTextPaneList() {
        return textPaneList;
    }

    public void setTextPaneList(final ArrayListIndex<String> textPaneList) {
        this.textPaneList.addAll(textPaneList);
    }

    public TextPane(final File backgroundImageFile, final String text) {

        super(backgroundImageFile);

        Text displayText = new Text();
        displayText.setFont(new Font("Book Antiqua Bold Italic", 14));
        displayText.setWrappingWidth(this.getPrefWidth() * 0.8);

        ArrayList <String> textSplitWord = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == SPACE || text.charAt(i) == LINE_FEED) {
                textSplitWord.add(sb.toString());
                textSplitWord.add(String.valueOf(text.charAt(i)));
                sb.setLength(0);
                continue;
            }

            sb.append(text.charAt(i));
        }
        textSplitWord.add(sb.toString());

        sb.setLength(0);

        for (int i = 0; i < textSplitWord.size(); i++) {
            sb.append(textSplitWord.get(i));
            displayText.setText(sb.toString());

            if (displayText.getLayoutBounds().getHeight() > TEXT_HEIGHT) {
                sb.setLength(sb.length() - textSplitWord.get(i).length());
                textPaneList.add(sb.toString());
                sb.setLength(0);
                if (textSplitWord.get(i).charAt(0) != LINE_FEED &&
                        textSplitWord.get(i).charAt(0) != SPACE) {
                    i--;
                }
            }
        }
        textPaneList.add(sb.toString());

//        for (int i = 0; i < textPaneList.size(); i++) {
//            System.out.println("i = " + i);
//            System.out.println(textPaneList.get(i));
//        }

        displayText.setText(textPaneList.get(0));

        displayText.setX(this.getPrefWidth() * 0.1);
        displayText.setTextAlignment(TextAlignment.LEFT);
        displayText.setY(82);
        displayText.setTextOrigin(VPos.TOP);

        this.getChildren().add(displayText);

    }

}
