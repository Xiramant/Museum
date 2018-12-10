package table4K.medal;

import general.ImagePane;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;

public class MedalDescribtionText extends ImagePane {

    public MedalDescribtionText(final File textFileDescription) {
        super(new File(RESOURCES_PATH + "medal/description.jpg"), 0, 900 / debuggingRatio);

        
    }

}
