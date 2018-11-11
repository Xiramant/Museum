package general;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

//Класс для разбития текста на блоки с заданными параметрами (ширина, высота, шрифт),
// которые будут использоваться для отображения на сцене в заданных границах
public class TextPane {

    //Символ конца строки
    private final char LINE_FEED = 10;

    //Символ пробела
    private final char SPACE = 32;

    public ArrayList<String> breakTextIntoBlocks(final String text,
                                                 final double widthBlock,
                                                 final double heightBlock,
                                                 final Font fontBlock,
                                                 final double lineSpacing) {

        //блоки текстов
        ArrayList<String> textPaneList = new ArrayList<>();

        Text textBlock = new Text();
        textBlock.setFont(fontBlock);
        textBlock.setWrappingWidth(widthBlock);
        if (lineSpacing != 0) {
            textBlock.setLineSpacing(lineSpacing);
        }

        //Разбитие текста на слова,
        // где разделителями считаются: Пробел и Перевод каретки на новую строку
        //Слова в листе представлены в виде:
        // слово + знак препинания (если есть) + пробел + перевод каретки (если есть)
        ArrayList <String> textIntoWord = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == SPACE || text.charAt(i) == LINE_FEED) {
                textIntoWord.add(sb.toString());
                textIntoWord.add(String.valueOf(text.charAt(i)));
                sb.setLength(0);
                continue;
            }

            sb.append(text.charAt(i));
        }
        textIntoWord.add(sb.toString());
        sb.setLength(0);

        //Разбитие текста на блоки Text,
        // которые бы удовлетворяли заданным параметрам: ширина, высота, шрифт
        //Подстановка при поиске происходит по словам,
        // т.к. по словам происходит переход на новую строчку в Text
        for (int i = 0; i < textIntoWord.size(); i++) {
            sb.append(textIntoWord.get(i));
            textBlock.setText(sb.toString());

            if (textBlock.getLayoutBounds().getHeight() > heightBlock) {
                sb.setLength(sb.length() - textIntoWord.get(i).length());
                textPaneList.add(sb.toString());
                sb.setLength(0);
                //Для того, чтобы слово, которое вызывает превышение допустимой высоты блока Text
                // вошло в следующий блок вызывается операция i--,
                // но в случае, если это слово - перевод каретки,
                // то ее включать в следующий блок не нужно
                if (textIntoWord.get(i).charAt(0) != LINE_FEED) {
                    i--;
                }
            }
        }
        textPaneList.add(sb.toString());

        return textPaneList;
    }

    public ArrayList<String> breakTextIntoBlocks(final String text,
                                                 final double widthBlock,
                                                 final double heightBlock,
                                                 final Font fontBlock) {

        return breakTextIntoBlocks(text, widthBlock, heightBlock, fontBlock, 0);
    }

}
