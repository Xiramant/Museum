package table.section.portfolio;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import general.ImagePane;

import java.io.File;
import java.util.ArrayList;

import static table.Main.RESOURCES_PATH;
import static general.TextProcessing.readingFirstStokeFromFileAndSplitIntoWord;

public class PortfolioPane extends ImagePane{

    //Файл изображения фона для текстового блока
    public static final File PORTFOLIO_BACKGROUND_INITIAL_FILE = new File(RESOURCES_PATH + "portfolio/portfolio_0.png");
    public static final File PORTFOLIO_BACKGROUND_MAIN_FILE = new File(RESOURCES_PATH + "portfolio/portfolio_1.png");

    //максимальная ширина панели с текстом
    private static final double PORTFOLIO_PANE_HEIGHT_MAX = 460;

    private ArrayList<File> foto;
    private File text;
    private String surname;
    //поле включающее в себя имя и отчество
    private String name;




    //отступ текстового блока от верха
    private static final double TEXT_TOP_PADDING = 200;

    //шрифт текстового блока
    private static final Font TEXT_FONT = new Font("Book Antiqua Bold Italic", 14);





    public PortfolioPane(final ArrayList<File> fotoTransferred, final File textTransferred) {
        super(PORTFOLIO_BACKGROUND_INITIAL_FILE, 0, PORTFOLIO_PANE_HEIGHT_MAX);

        foto = new ArrayList<>(fotoTransferred);
        text = textTransferred;

        ArrayList<String> fio = readingFirstStokeFromFileAndSplitIntoWord(textTransferred);
        if (fio != null && fio.size() > 1) {
            surname = fio.get(0);
            name = fio.get(1);
        }

        setInitialPane();
    }

    private void setInitialPane() {
        this.setImageBackground(PORTFOLIO_BACKGROUND_INITIAL_FILE);

        Text textSurname = new Text(surname);

        textSurname.setFont(TEXT_FONT);
        textSurname.setWrappingWidth(this.getPrefWidth() * 0.9);
        textSurname.setX(0);
        textSurname.setTextAlignment(TextAlignment.CENTER);
        textSurname.setY(222);
        textSurname.setTextOrigin(VPos.TOP);

        Text textNamePlusPatronymic = new Text(name);

        textNamePlusPatronymic.setFont(TEXT_FONT);
        textNamePlusPatronymic.setWrappingWidth(this.getPrefWidth() * 0.9);
        textNamePlusPatronymic.setX(0);
        textNamePlusPatronymic.setTextAlignment(TextAlignment.CENTER);
        textNamePlusPatronymic.setY(265);
        textNamePlusPatronymic.setTextOrigin(VPos.TOP);

        this.getChildren().addAll(textSurname, textNamePlusPatronymic);

        this.setOnMouseClicked(event -> {
            setMainPane();
        });
    }

    private void setMainPane() {
        System.out.println("Выбрано личное дело " + surname + " " + name);
    }
}
