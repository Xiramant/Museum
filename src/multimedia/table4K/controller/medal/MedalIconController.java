package table4K.controller.medal;

import javafx.scene.Node;
import table4K.controller.IconController;

import static general.SectionKey.ORDEN;
import static table4K.medal.Medal.setMedalScene;


public class MedalIconController extends IconController {

    public MedalIconController(final Node medalIconArg) {
        super(medalIconArg);
    }

    public void selectSection(){
        setMedalScene(ORDEN);
    }

}