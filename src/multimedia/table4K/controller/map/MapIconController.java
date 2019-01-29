package table4K.controller.map;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.map.Map.setMapScene;


public class MapIconController extends IconController {

    public MapIconController(final Node mapIconArg) {
        super(mapIconArg);
    }

    public void selectSection(){
        setMapScene();
    }

}
