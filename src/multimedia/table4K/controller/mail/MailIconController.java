package table4K.controller.mail;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.mail.Mail.setMailScene;


public class MailIconController extends IconController {

    public MailIconController(final Node mailIconArg) {
        super(mailIconArg);
    }

    public void selectSection(){
        setMailScene();
    }

}