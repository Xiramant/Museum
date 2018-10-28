package table.model;

import javafx.geometry.VPos;
import javafx.scene.text.*;

import java.io.File;

import static javafx.scene.text.TextBoundsType.LOGICAL_VERTICAL_CENTER;
import static table.Main.RESOURCES_PATH;

public class InitialMapPane extends ImagePane{

    static final File sectionIconFile = new File(RESOURCES_PATH + "map/text_case_0_1.png");

    public InitialMapPane(final String operationName) {
        super(sectionIconFile);
        Text textOperationName = new Text(operationName);
        textOperationName.setFont(new Font("Book Antiqua Bold Italic", 30));
        textOperationName.setWrappingWidth(this.getPrefWidth() * 0.8);
        textOperationName.setX(this.getPrefWidth() * 0.1);
        textOperationName.setTextAlignment(TextAlignment.CENTER);
//        textOperationName.setUnderline(true);
        textOperationName.setY(200);
//        textOperationName.setBoundsType(LOGICAL_VERTICAL_CENTER);
        textOperationName.setTextOrigin(VPos.CENTER);
        this.getChildren().add(textOperationName);
//        this.getChildren().get(0).boundsInParentProperty();


    }
}
