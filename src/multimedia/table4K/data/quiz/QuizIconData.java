package table4K.data.quiz;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;


public class QuizIconData implements IIconData {

    private Image quizIcon = new Image("file:///" + RESOURCES_PATH + "icon/quiz_icon.png");

    public Image getIconImage() {
        return quizIcon;
    }

}