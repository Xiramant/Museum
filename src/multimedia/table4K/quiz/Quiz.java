package table4K.quiz;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static general.TextProcessing.readingFileIntoStringList;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;


public class Quiz {

    //Число вопросов викторины
    static final int QUESTION_MAX = 20;

    //Номер вопроса для которого используется вопрос с медиа
    private static final int QUESTION_MEDIA = 5;

    //цвет текста викторины
    static final Color TEXT_COLOR = Color.rgb(7, 153, 89);

    //центр экрана телевизора по горизонтали
    private static final double TEXT_X_CENTER = 1610 / debuggingRatio;

    //отступ сверху для надписи Номер вопроса
    private static final double QUESTION_NUMBER_TEXT_Y = 398 / debuggingRatio;

    //отступ сверху для текста Полученные очки
    private static final double POINTS_RECEIVED_TEXT_Y = 1456 / debuggingRatio;

    //максимальная ширина текста викторины
    static final double QUESTION_TEXT_WIDTH_MAX = 1800 / debuggingRatio;

    //первоначальные параметры шрифта текста викторины
    private static final double TEXT_FONT_SIZE_INITIAL = 80 / debuggingRatio;
    private static final String TEXT_FONT_NAME = "Dited";
    private static final double BLOCK_TEXT_VERTICAL_INTERVAL_INITIAL = 50 / debuggingRatio;
    private static final double LINE_TEXT_VERTICAL_INTERVAL_INITIAL = 20 / debuggingRatio;

    //множитель, на который постепенно уменьшается размер шрифта и отступов текста викторины,
    // чтобы влезть в экран телевизора
    private static final double MULTIPLIER = 0.95;

    //месторасположение изображений кнопок Медиа и Следующий
    private static final File BUTTON_MEDIA_BACKGROUND_FILE = new File(RESOURCES_PATH + "quiz/buttonMedia.png");
    private static final File BUTTON_NEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "quiz/buttonNext.png");

    //расположение кнопок управления викториной
    private static final double BUTTON_MEDIA_X = 505 / debuggingRatio;
    private static final double BUTTON_ONE_X = 999 / debuggingRatio;
    private static final double BUTTON_TWO_X = 1218 / debuggingRatio;
    private static final double BUTTON_THREE_X = 1437 / debuggingRatio;
    private static final double BUTTON_FOUR_X = 1656 / debuggingRatio;
    private static final double BUTTON_TEST_X = 1974 / debuggingRatio;
    private static final double BUTTON_NEW_X = 2478 / debuggingRatio;
    private static final double BUTTON_Y = 1840 / debuggingRatio;

    //размер шрифта текста викторины
    private static double textFontSize;

    //шрифт текста викторины
    static Font textFont;

    //вертикальный отступ для абзацев текста викторины
    static double blockTextVerticalInterval;

    //вертикальный отступ для вариантов ответов викторины
    static double lineTextVerticalInterval;

    //листы QuizQuestion для текстовых вопросов и вопросов с медиа
    private static ArrayList<QuizQuestion> questionBlock;
    private static ArrayList<QuizQuestion> questionMediaBlock;

    //текущий вопрос викторины
    static QuizQuestion currentQuestion;

    //игрок с результатами для текущей викторины
    static QuizPlayer player;

    //группа текстовых блоков викторины
    private static Group groupText = new Group();

    //группа кнопок викторины
    private static Group groupButton = new Group();

    //Text номера вопроса, вопроса и полученных очков
    private static Text questionNumberText;
    private static Text questionText;
    static Text pointsReceivedText;

    //панели вариантов ответов
    private static QuizPaneTextAnswer answerOne;
    private static QuizPaneTextAnswer answerTwo;
    private static QuizPaneTextAnswer answerThree;
    private static QuizPaneTextAnswer answerFour;


    public static void setQuizScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_quiz.jpg");

        //Текст викторины разбитый по строчкам
        ArrayList<String> quizText = readingFileIntoStringList(new File(RESOURCES_PATH + "quiz/quiz_text.txt"));
        ArrayList<String> quizMedia = readingFileIntoStringList(new File(RESOURCES_PATH + "quiz/quiz_media.txt"));

        //Блоки вопросов викторины представленные в виде листа
        ArrayList<ArrayList<String>> quizTextBlock = new ArrayList<>(getQuizTextBlock(quizText));
        ArrayList<ArrayList<String>> quizMediaBlock = new ArrayList<>(getQuizTextBlock(quizMedia));

        //Блоки вопросов викторины представлены в виде класса QuizQuestion
        questionBlock = new ArrayList<>(getQuestionBlock(quizTextBlock));
        questionMediaBlock = new ArrayList<>(getQuestionBlock(quizMediaBlock));

        //создание нового игрока
        player = new QuizPlayer();

        //установка отображения вопроса и кнопок
        setQuestion();
        setButton();

        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(groupText, groupButton, returnHome());
    }


    //вывод на экран вопроса викторины
    static void setQuestion() {

        //задание первоначальных значений параметров текста,
        // чтобы при их изменении (из-за того, что текст не помещается на экран),
        // на следующем вопросе они были с первоначальными значениями
        textFontSize = TEXT_FONT_SIZE_INITIAL;
        textFont = new Font(TEXT_FONT_NAME, TEXT_FONT_SIZE_INITIAL);
        blockTextVerticalInterval = BLOCK_TEXT_VERTICAL_INTERVAL_INITIAL;
        lineTextVerticalInterval = LINE_TEXT_VERTICAL_INTERVAL_INITIAL;

        questionNumberText = new Text();
        questionText = new Text();
        pointsReceivedText = new Text();

        answerOne = new QuizPaneTextAnswer();
        answerTwo = new QuizPaneTextAnswer();
        answerThree = new QuizPaneTextAnswer();
        answerFour = new QuizPaneTextAnswer();

        player.incrementQuestionNumber();

        //получение текущего вопроса: текстового или с медиа
        if (player.getQuestionNumber() % QUESTION_MEDIA == 0) {
            currentQuestion = getCurrentQuestion(questionMediaBlock);
        } else {
            currentQuestion = getCurrentQuestion(questionBlock);
        }

        //формирование списка ответов со случайным расположением
        ArrayList<String> randomAnswer = currentQuestion.getRandomAnswer();

        String questionNumber = "Вопрос: " + String.valueOf(player.getQuestionNumber()) + " / " + String.valueOf(QUESTION_MAX);

        String pointsReceived = "Получено баллов: " + String.valueOf(player.getReceivePoints());

        questionNumberText.setText(questionNumber);
        questionNumberText.setFill(TEXT_COLOR);
        questionNumberText.setLayoutX(TEXT_X_CENTER - questionNumberText.getLayoutBounds().getWidth() / 2);
        questionNumberText.setLayoutY(QUESTION_NUMBER_TEXT_Y);

        StringBuilder point = new StringBuilder();
        point.append("(Вопрос на ");
        point.append(currentQuestion.getPoints());
        if (currentQuestion.getPoints() == 1) {
            point.append(" балл). ");
        } else {
            point.append(" балла). ");
        }

        String Question = point.toString() + currentQuestion.getQuestion();
        if (player.getQuestionNumber() % QUESTION_MEDIA == 0) {
            Question += System.lineSeparator() +
                    "Для просмотра фотографии нажмите нижнюю левую кнопку с символом треугольника.";
        }

        questionText.setText(Question);
        questionText.setWrappingWidth(QUESTION_TEXT_WIDTH_MAX);
        questionText.setFill(TEXT_COLOR);
        questionText.setTextAlignment(TextAlignment.JUSTIFY);
        questionText.setLayoutX(TEXT_X_CENTER - questionText.getWrappingWidth() / 2);

        answerOne.setQPTAText("1.  ", randomAnswer.get(0));
        answerOne.setLayoutX(questionText.getLayoutX());

        answerTwo.setQPTAText("2. ", randomAnswer.get(1));
        answerTwo.setLayoutX(questionText.getLayoutX());

        answerThree.setQPTAText("3. ", randomAnswer.get(2));
        answerThree.setLayoutX(questionText.getLayoutX());

        answerFour.setQPTAText("4. ", randomAnswer.get(3));
        answerFour.setLayoutX(questionText.getLayoutX());

        pointsReceivedText.setText(pointsReceived);
        pointsReceivedText.setFill(TEXT_COLOR);
        pointsReceivedText.setLayoutX(TEXT_X_CENTER - pointsReceivedText.getLayoutBounds().getWidth() / 2);
        pointsReceivedText.setLayoutY(POINTS_RECEIVED_TEXT_Y);

        setFontSize();


        groupText.getChildren().clear();
        groupText.getChildren().addAll(questionNumberText, questionText, answerOne, answerTwo, answerThree, answerFour, pointsReceivedText);
    }

    //получение текущего вопроса из переданного блока вопросов
    private static QuizQuestion getCurrentQuestion(final ArrayList<QuizQuestion> questionBlock) {

        QuizQuestion temp;

        int random = new Random().nextInt(questionBlock.size());
        temp = questionBlock.get(random);
        questionBlock.remove(random);

        return temp;
    }


    //метод уменьшающий размер текста и вертикальные интервалы,
    //если текст викторины не помещается на экран телевизора
    private static void setFontSize() {

        double multiplierCurrent = 1;

        do {
            textFontSize *= multiplierCurrent;
            textFont =  new Font(TEXT_FONT_NAME, textFontSize);
            blockTextVerticalInterval *= multiplierCurrent;
            lineTextVerticalInterval *= multiplierCurrent;

            questionNumberText.setFont(textFont);

            questionText.setFont(textFont);
            questionText.setLayoutY(questionNumberText.getLayoutY() +
                                    questionNumberText.getLayoutBounds().getHeight() +
                                    blockTextVerticalInterval);

            answerOne.setQPTAFont();
            answerOne.setQPTAHeight();
            answerOne.setLayoutY(questionText.getLayoutY() +
                                questionText.getLayoutBounds().getHeight());

            answerTwo.setQPTAFont();
            answerTwo.setQPTAHeight();
            answerTwo.setLayoutY(answerOne.getLayoutY() +
                                answerOne.getPrefHeight());

            answerThree.setQPTAFont();
            answerThree.setQPTAHeight();
            answerThree.setLayoutY(answerTwo.getLayoutY() +
                                answerTwo.getPrefHeight());

            answerFour.setQPTAFont();
            answerFour.setQPTAHeight();
            answerFour.setLayoutY(answerThree.getLayoutY() +
                                answerThree.getPrefHeight());

            pointsReceivedText.setFont(textFont);


            multiplierCurrent *= MULTIPLIER;
        }
        while ((answerFour.getLayoutY() +
                answerFour.getPrefHeight() +
                blockTextVerticalInterval) > pointsReceivedText.getLayoutY());

    }


    //вывод результатов викторины на экран
    static void setResult() {

        Text end = new Text();
        Text result = new Text();

        end.setText("Результаты викторины:");
        end.setFont(textFont);
        end.setFill(TEXT_COLOR);
        end.setLayoutX(TEXT_X_CENTER - end.getLayoutBounds().getWidth() / 2);
        end.setLayoutY(QUESTION_NUMBER_TEXT_Y);

        result.setText(getTextResult());
        result.setWrappingWidth(QUESTION_TEXT_WIDTH_MAX);
        result.setFont(textFont);
        result.setFill(TEXT_COLOR);
        result.setTextAlignment(TextAlignment.JUSTIFY);
        result.setLayoutX(TEXT_X_CENTER - result.getWrappingWidth() / 2);
        result.setLayoutY(QUESTION_NUMBER_TEXT_Y + end.getLayoutBounds().getHeight() + blockTextVerticalInterval);

        groupText.getChildren().clear();
        groupText.getChildren().addAll(end, result);
    }

    //получение текста результата викторины
    private static String getTextResult() {

        StringBuilder resultSB = new StringBuilder();
        resultSB.append("Игроком правильно отвечено на ");
        resultSB.append(player.getCorrectAnswer());
        resultSB.append(" вопросов из ");
        resultSB.append(QUESTION_MAX);
        resultSB.append(".");

        resultSB.append("\n");

        resultSB.append("Получено ");
        resultSB.append(player.getReceivePoints());
        resultSB.append(" баллов из ");
        resultSB.append(player.getMaxPoints());
        resultSB.append(" возможных.");

        resultSB.append("\n");
        resultSB.append("\n");

        resultSB.append("Чтобы начать игру заново, нажмите кнопку перехода (нижняя правая кнопка).");

        return resultSB.toString();
    }

    //создание кнопок управления викториной
    static void setButton() {

        QuizButtonMedia buttonMedia = new QuizButtonMedia(BUTTON_MEDIA_BACKGROUND_FILE, currentQuestion.getMedia());
        buttonMedia.setLayoutX(BUTTON_MEDIA_X);
        buttonMedia.setLayoutY(BUTTON_Y);

        QuizButtonSelect buttonOne = new QuizButtonSelect("1", answerOne);
        buttonOne.setLayoutX(BUTTON_ONE_X);
        buttonOne.setLayoutY(BUTTON_Y);

        QuizButtonSelect buttonTwo = new QuizButtonSelect("2", answerTwo);
        buttonTwo.setLayoutX(BUTTON_TWO_X);
        buttonTwo.setLayoutY(BUTTON_Y);

        QuizButtonSelect buttonThree = new QuizButtonSelect("3", answerThree);
        buttonThree.setLayoutX(BUTTON_THREE_X);
        buttonThree.setLayoutY(BUTTON_Y);

        QuizButtonSelect buttonFour = new QuizButtonSelect("4", answerFour);
        buttonFour.setLayoutX(BUTTON_FOUR_X);
        buttonFour.setLayoutY(BUTTON_Y);

        QuizButtonSelectGroup buttonSelectGroup = new QuizButtonSelectGroup();
        buttonSelectGroup.add(buttonOne);
        buttonSelectGroup.add(buttonTwo);
        buttonSelectGroup.add(buttonThree);
        buttonSelectGroup.add(buttonFour);

        QuizButtonTest buttonTest = new QuizButtonTest("?",
                                                        currentQuestion.getCorrectAnswer(),
                                                        buttonSelectGroup);
        buttonTest.setLayoutX(BUTTON_TEST_X);
        buttonTest.setLayoutY(BUTTON_Y);

        QuizButtonNext buttonNext = new QuizButtonNext(BUTTON_NEXT_BACKGROUND_FILE,
                                                        buttonMedia,
                                                        buttonTest);
        buttonNext.setLayoutX(BUTTON_NEW_X);
        buttonNext.setLayoutY(BUTTON_Y);


        groupButton.getChildren().clear();
        groupButton.getChildren().addAll(buttonMedia, buttonSelectGroup, buttonTest, buttonNext);
    }

    //установка кнопок управления викториной при выводе результатов викторины на экран
    static void setButtonResult() {

        //создание кнопок управления
        QuizButton buttonMedia = new QuizButton(BUTTON_MEDIA_BACKGROUND_FILE);
        buttonMedia.setLayoutX(BUTTON_MEDIA_X);
        buttonMedia.setLayoutY(BUTTON_Y);

        QuizButton buttonOne = new QuizButton("1");
        buttonOne.setLayoutX(BUTTON_ONE_X);
        buttonOne.setLayoutY(BUTTON_Y);

        QuizButton buttonTwo = new QuizButton("2");
        buttonTwo.setLayoutX(BUTTON_TWO_X);
        buttonTwo.setLayoutY(BUTTON_Y);

        QuizButton buttonThree = new QuizButton("3");
        buttonThree.setLayoutX(BUTTON_THREE_X);
        buttonThree.setLayoutY(BUTTON_Y);

        QuizButton buttonFour = new QuizButton("4");
        buttonFour.setLayoutX(BUTTON_FOUR_X);
        buttonFour.setLayoutY(BUTTON_Y);

        QuizButton buttonTest = new QuizButton("?");
        buttonTest.setLayoutX(BUTTON_TEST_X);
        buttonTest.setLayoutY(BUTTON_Y);

        QuizButtonNew buttonNew = new QuizButtonNew(BUTTON_NEXT_BACKGROUND_FILE);
        buttonNew.setLayoutX(BUTTON_NEW_X);
        buttonNew.setLayoutY(BUTTON_Y);

        groupButton.getChildren().clear();
        groupButton.getChildren().addAll(buttonMedia, buttonOne, buttonTwo, buttonThree, buttonFour, buttonTest, buttonNew);
    }

    //Получение листа листов строк викторины, где
    // внешний лист - блок текста по отдельному вопросу
    // внутренний лист - строки текста соответствующие блоку вопроса:
    //      количеству получаемых баллов, тексту вопроса, ответам
    private static ArrayList<ArrayList<String>> getQuizTextBlock(final ArrayList<String> quizText) {

        ArrayList<ArrayList<String>> quizTextBlock = new ArrayList<>();

        ArrayList<String> temp = new ArrayList<>();
        for (String str: quizText) {

            if (str.startsWith("Баллов: ")) {
                if (temp.size() != 0) {
                    quizTextBlock.add(new ArrayList<>(temp));
                    temp.clear();
                }
            }

            temp.add(str);
        }

        if (temp.size() != 0) {
            quizTextBlock.add(new ArrayList<>(temp));
        }

        return quizTextBlock;
    }


    //Получение листа класса QuizQuestion для викторины
    // где QuizQuestion является представлением отдельного блока вопроса викторины
    private static ArrayList<QuizQuestion> getQuestionBlock(final ArrayList<ArrayList<String>> quizTextBlock) {

        ArrayList<QuizQuestion> questionBlock = new ArrayList<>();

        for (ArrayList<String> block: quizTextBlock) {

            int pointTemp = getPoints(block);
            String questionTemp = getQuestion(block);
            String correctAnswerTemp = getCorrectAnswer(block);
            ArrayList<String> answerTemp = new ArrayList<>(getAnswers(block));
            String mediaTemp = getMedia(block);

            if (pointTemp != 0 &&
                    !questionTemp.equals("") &&
                    !correctAnswerTemp.equals("") &&
                    answerTemp.size() != 0) {

                questionBlock.add(new QuizQuestion(pointTemp, questionTemp, answerTemp, correctAnswerTemp, mediaTemp));
            }
        }

        return questionBlock;
    }


    private static int getPoints(final ArrayList<String> block) {

        for (String str: block) {
            if (str.startsWith("Баллов: ")) {
                String strTemp = str.substring((new String("Баллов: ")).length());
                strTemp = strTemp.replaceAll(" ", "");
                return (Integer.parseInt(strTemp));
            }
        }

        return 0;
    }

    private static String getQuestion(final ArrayList<String> block) {

        for (String str: block) {
            if (str.startsWith("Вопрос: ")) {
                return str.substring((new String("Вопрос: ")).length());
            }
        }

        return "";
    }

    private static String getCorrectAnswer(final ArrayList<String> block) {

        for (String str: block) {
            if (str.startsWith("+ ")) {
                return str.substring((new String("+ ")).length());
            }
        }

        return "";
    }

    private static ArrayList<String> getAnswers(final ArrayList<String> block) {

        ArrayList<String> answerTemp = new ArrayList<>();

        for (String str: block) {
            if (str.startsWith("+ ") || str.startsWith("- ")) {
                answerTemp.add(str.substring((new String("+ ")).length()));
            }
        }

        return answerTemp;
    }

    private static String getMedia(final ArrayList<String> block) {

        for (String str: block) {
            if (str.startsWith("Медиа: ")) {
                String mediaTemp = str.substring((new String("Медиа: ")).length());
                mediaTemp = mediaTemp.replaceAll(" ", "");
                return mediaTemp;
            }
        }

        return "";
    }
}
