package table4K.controller.video;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.model.video.VideoList.setVideoScene;


public class VideoIconController extends IconController {

    public VideoIconController(final Node videoIconArg) {
        super(videoIconArg);
    }

    public void selectSection(){
        setVideoScene();
    }

}