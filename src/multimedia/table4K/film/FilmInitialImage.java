package table4K.film;

import general.ImagePane;
import javafx.geometry.VPos;
import javafx.scene.input.InputEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;

import static general.TextProcessing.readingFirstStokeFromFile;
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.actionPermission;
import static table4K.film.Film.obj;
import static table4K.film.FilmShow.FilmShowSet;

public class FilmInitialImage extends ImagePane {

    //параметры надписи названия фильма
    private static final double FILM_BOX_WIDTH = 600 / DEBUGGING_RATIO;
    private static final Font FILM_NAME_FONT = Font.font("Arial Narrow", FontWeight.BOLD, 36 / DEBUGGING_RATIO);
    private static final Color FILM_NAME_COLOR = Color.rgb(52, 54, 70);
    private static final double FILM_NAME_WIDTH_MAX = 240 / DEBUGGING_RATIO;
    private static final double FILM_NAME_X = 309 / DEBUGGING_RATIO;
    private static final double FILM_NAME_Y = 294 / DEBUGGING_RATIO;

    //путь к видеофайлу
    private String videoPath;

    //соотношение ширины и высоты видеофайла
    private double ratio;


    FilmInitialImage(final File videoFileEnter, final File textFileEnter) {
        super(new File(RESOURCES_PATH + "film/box_with_name.png"), FILM_BOX_WIDTH, 0);

        videoPath = videoFileEnter.toURI().toString();

        Text filmName = new Text(readingFirstStokeFromFile(textFileEnter));

        filmName.setFont(FILM_NAME_FONT);
        filmName.setFill(FILM_NAME_COLOR);
        filmName.setWrappingWidth(FILM_NAME_WIDTH_MAX);
        filmName.setTextAlignment(TextAlignment.CENTER);
        filmName.setTextOrigin(VPos.CENTER);
        filmName.setX(FILM_NAME_X - filmName.getLayoutBounds().getWidth() / 2);
        filmName.setY(FILM_NAME_Y);

        this.getChildren().add(filmName);

        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            ratio = (double) mediaPlayer.getMedia().getWidth() / (double) mediaPlayer.getMedia().getHeight();
            synchronized(obj) {
                obj.notify();
            }
        });


        this.setOnMouseClicked(event -> filmAction(event));

        this.setOnTouchReleased(event -> filmAction(event));
    }

    private void filmAction(final InputEvent event) {
        if (isTimeWaitEnd() && actionPermission(event)) {
            FilmShowSet(videoPath, ratio);
            setTimeWait();
        }
    }

}
