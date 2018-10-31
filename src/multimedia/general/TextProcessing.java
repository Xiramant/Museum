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

    //Метод копирования текста из файла в лист стрингов
    //каждый стринг представляет собой строчку из файла с текстом
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
        } catch (IOException e) {
            System.out.println("Ошибка чтения первой строки из файла = " + file.toString() + e);
        }

        return "";
    }

    //Метод копирования первой строчки из файла в String
    // и разбития ее на две строчки
    //Используется для разбития ФИО на фамилию, имя+отчество
    // (сначала я хотел разделить строчку на 3 слова: фамилию, имя, отчество
    // но у некоторых людей фио не подпадают под эту категорию)
    public static ArrayList<String> readingFirstStokeFromFileAndSplitIntoWord(final File file) {

        //временная строка, куда записывается первая строка из файла
        String firstString = readingFirstStokeFromFile(file);
        if (firstString.length() == 0) return null;

        //лист фио разделенных на фамилию и имя + отчество
        ArrayList<String> fio = new ArrayList<>();

        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) == SPACE) {
                fio.add(firstString.substring(0, i));
                fio.add(firstString.substring(i+1));
            }
        }

        return fio;
    }
}
