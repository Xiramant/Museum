package table.section.portfolio;

import javafx.scene.layout.Pane;
import general.FileFormat;
import general.SectionKey;

import java.util.ArrayList;
import java.io.File;

import static table.Main.sectionPane;
import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.InitialLocation.initialPositionElements;

public class Portfolio {

    //Ключевое слово раздела Portfolio
    private static final SectionKey PORTFOLIO_KEY = SectionKey.PORTFOLIO;

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    private static final double MIN_WIDTH_SPACING = 30;

    //Фото:
    //внешний лист - личное дело;
    //внутренний лист - фотографии для личного дела
    private static ArrayList<ArrayList<File>> portfolioImageFiles;

    //Текстовые файлы:
    //внешний лист - лист сражений;
    //внутренний лист - текстовые файлы для личных дел
    private static ArrayList<File> portfolioTextFiles;

//    //лист стрингов, в которые преобразуется содержание текстовых файлов
//    private static ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

    //флаг осуществления перемещения текстового блока
    private static Boolean isDragAndDropPortfolio = false;

    //метод установки элементов раздела Portfolio в sectionPane
    public static void setPortfolioScene() {

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDirKey(PORTFOLIO_KEY));

        portfolioImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));
        portfolioTextFiles = new ArrayList<>();
        //В личных делах должен быть только 1 текстовый файл,
        // но getFiles() возвращает ArrayList<ArrayList<File>>,
        // поэтому пришлось сделать такую конструкцию:
        for (ArrayList<File> temp: getFiles(fileMapDirs, FileFormat.TEXT)) {
            portfolioTextFiles.add(temp.get(0));
        }

        //Инициализация первоначального состояния раздела Личные дела
        // с расположением Фамилия, Имя+Отчество
        sectionPane.getChildren().clear();
        for (int i = 0; i < portfolioTextFiles.size(); i++) {
            sectionPane.getChildren().add(new PortfolioPane(portfolioImageFiles.get(i), portfolioTextFiles.get(i)));
        }
        initialPositionElements(sectionPane.getChildren(), ((Pane) sectionPane.getChildren().get(0)).getPrefWidth() + MIN_WIDTH_SPACING);


        System.out.println();

    }

}
