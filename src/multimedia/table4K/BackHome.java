package table4K;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static table4K.Main4K.TABLE_HEIGHT;
import static table4K.Main4K.TABLE_WIDTH;
import static table4K.Main4K.setMainScene;

public class BackHome {

    public static Text returnHome() {

        Text home = new Text("Вернуться к выбору разделов");
        home.setFont(new Font("Arial", 40));
        home.setFill(Color.WHITE);

        home.setLayoutX(TABLE_WIDTH - home.getLayoutBounds().getWidth());
        home.setLayoutY(TABLE_HEIGHT - home.getLayoutBounds().getHeight());

        home.setOnMouseClicked(event -> setMainScene());
        home.setOnTouchReleased(event -> setMainScene());

        return home;
    }
}
