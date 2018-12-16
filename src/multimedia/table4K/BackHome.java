package table4K;

import javafx.geometry.VPos;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static table4K.Main4K.TABLE_HEIGHT;
import static table4K.Main4K.TABLE_WIDTH;
import static table4K.Main4K.setMainScene;

public class BackHome {

    public static Text returnHome() {

        Text home = setText();

        home.setOnMouseClicked(event -> setMainScene());
        home.setOnTouchReleased(event -> setMainScene());

        return home;
    }

    public static Text returnHome(final MediaPlayer mediaPlayer) {

        Text home = setText();

        home.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            setMainScene();
        });
        home.setOnTouchReleased(event -> {
            mediaPlayer.stop();
            setMainScene();
        });

        return home;
    }

    private static Text setText() {

        Text home = new Text("К выбору разделов");
        home.setFont(new Font("Arial", 30));
        home.setFill(Color.WHITE);

        home.setLayoutX(TABLE_WIDTH - home.getLayoutBounds().getWidth());
        home.setLayoutY(TABLE_HEIGHT - 10);
        home.setTextOrigin(VPos.BOTTOM);

        return home;
    }
}
