package table4K.controller.film;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.film.Film.setFilmScene;


public class FilmIconController extends IconController {

    public FilmIconController(final Node filmIconArg) {
        super(filmIconArg);
    }

    public void selectSection(){
        setFilmScene();
    }

}