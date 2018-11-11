package general;

import java.io.*;
import java.util.ArrayList;

public class TextProcessing {

    //Символы, которые НЕ НУЖНО переносить из файла Истории рук в лист стрингов:
    //Символ 65279 не что иное, как BOM (Byte Order Mark), который находится в начале файла с кодировкой UTF-8
    //Служит указателем порядка байтов и может указывать, какой кодировкой Unicode закодирован текст
    private final static int BOM = 65279;
    //Символ конца строки
    private final static int LINE_FEED = 10;
    //Символ возврата каретки (переноса строки)
    private final static int CARRIAGE_RETURN = 13;
    //Символ пробела
    private final static char SPACE = 32;

    //Метод копирования текста из файла в стринг
    public static String readingFileIntoString(final File file) {

        //временная строка, куда записываются символы из одной строки
        StringBuilder builderTemp = new StringBuilder();

        //Создание потока для чтения данных из файла
        // с кодировкой "windows-1251"
        //Данная кодировка получается по умолчанию при создании текстового файла в OS Windows
        //Без try/catch пишет, что не проверяется ошибка FileNotFoundException
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "windows-1251");
            BufferedReader br = new BufferedReader(isr);

            //чтение данных из файла пока переменная чтения не вернет -1 (конец файла)
            //!!!!! не вставлять проверку и чтение символов с помощью только br.read()),
            //иначе будет читать через символ, т.к. при проверке символ уже считывается
            int c;
            while ((c = br.read()) != -1) {

                if (c != CARRIAGE_RETURN) {
                    if (c != LINE_FEED && c != BOM) {
                        builderTemp.append((char) c);
                    }
                } else {
                    builderTemp.append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла = " + file.toString() + e);
        }

        return builderTemp.toString();
    }

    //добавление пробелов в начале каждой строчки текста
    public static String addSpaceToEachLine(final String text, final int number) {

        StringBuilder add = new StringBuilder();
        for (int i = 0; i < number; i++) {
            add.append(" ");
        }

        StringBuilder out = new StringBuilder(add.toString());

        for (int i = 0; i < text.length(); i++) {

            out.append(text.charAt(i));

            if (text.codePointAt(i) == LINE_FEED) {
                out.append(add.toString());
            }
        }

        return out.toString();
    }

    //Метод копирования первой строчки из файла в String
    public static String readingFirstStokeFromFile(final File file) {

        //временная строка, куда записываются символы из одной строки
        StringBuilder builderTemp = new StringBuilder();

        //Создание потока для чтения данных из файла
        // с кодировкой "windows-1251"
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "windows-1251");
            BufferedReader br = new BufferedReader(isr);

            int c;
            while ((c = br.read()) != -1) {

                if (c != LINE_FEED) {
                    if (c != BOM && c != CARRIAGE_RETURN) {
                        builderTemp.append((char) c);
                    }
                } else {
                    return builderTemp.toString();
                }
            }

            //в этом методе возврат метода нужен,
            // если в файле только одна строка
            return builderTemp.toString();

        } catch (IOException e) {
            System.out.println("Ошибка чтения первой строки из файла = " + file.toString() + e);
        }

        return "";
    }

    //Метод копирования первой строчки из файла в String
    // и разбития ее слова
    public static ArrayList<String> readingFirstStokeFromFileAndSplitIntoWord(final File file) {

        //временная строка, куда записывается первая строка из файла
        String firstString = readingFirstStokeFromFile(file);
        if (firstString.length() == 0) return null;

        //лист фио разделенных на фамилию и имя + отчество
        ArrayList<String> fio = new ArrayList<>();

        //временная строка, куда записываются символы одного слова
        StringBuilder builderTemp = new StringBuilder();

        for (int i = 0; i < firstString.length(); i++) {

            if (firstString.charAt(i) == SPACE) {
                if (builderTemp.length() != 0) {
                    fio.add(builderTemp.toString());
                }
                builderTemp.setLength(0);
                continue;
            }

            builderTemp.append(firstString.charAt(i));
        }

        if (builderTemp.length() != 0) {
            fio.add(builderTemp.toString());
        }

        return fio;
    }

}
