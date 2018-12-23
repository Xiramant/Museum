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

    static final Color TEXT_COLOR = Color.rgb(7, 153, 89);

    private static final double TEXT_X_CENTER = 1610 / debuggingRatio;

    private static final double QUESTION_NUMBER_TEXT_Y = 398 / debuggingRatio;

    static final double QUESTION_TEXT_WIDTH_MAX = 1800 / debuggingRatio;

    private static final double POINTS_RECEIVED_TEXT_Y = 1456 / debuggingRatio;

    static final int QUESTION_MAX = 20;

    private static double textFontSize;

    static Font textFont;

    static double blockTextVerticalInterval;

    static double lineTextVerticalInterval;

    private static ArrayList<QuizQuestion> questionBlock;
    private static ArrayList<QuizQuestion> questionMediaBlock;

    static QuizQuestion selectQuestion;

    static QuizPlayer player;

    private static Group groupText = new Group();
    private static Group groupButton = new Group();

    private static Text questionNumberText;
    private static Text questionText;

    private static QuizPaneTextAnswer answerOne;
    private static QuizPaneTextAnswer answerTwo;
    private static QuizPaneTextAnswer answerThree;
    private static QuizPaneTextAnswer answerFour;

    static Text pointsReceivedText;


    public static void setQuizScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_quiz.jpg");
        mainPane.getChildren().clear();

        //Текст викторины разбитый по строчкам
        ArrayList<String> quizText = readingFileIntoStringList(new File(RESOURCES_PATH + "quiz/quiz_text.txt"));
        ArrayList<String> quizMedia = readingFileIntoStringList(new File(RESOURCES_PATH + "quiz/quiz_media.txt"));

        //Блоки вопросов викторины представленные в виде листа
        ArrayList<ArrayList<String>> quizTextBlock = new ArrayList<>(getQuizTextBlock(quizText));
        ArrayList<ArrayList<String>> quizMediaBlock = new ArrayList<>(getQuizTextBlock(quizMedia));

        //Блоки вопросов викторины представлены в виде класса questionBlock
        questionBlock = new ArrayList<>(getQuestionBlock(quizTextBlock));
        questionMediaBlock = new ArrayList<>(getQuestionBlock(quizMediaBlock));

        //создание нового игрока
        player = new QuizPlayer();

        //установка отображения вопроса и кнопок
        setQuestion();
        setButton();

        mainPane.getChildren().addAll(groupText, groupButton, returnHome());
    }


    //вывод на экран вопроса викторины
    static void setQuestion() {

        //задание первоначальных значений параметров текста,
        // чтобы при их изменении (из-за того, что текст не помещается на экран),
        // на следующем вопросе они были с первоначальными значениями
        textFontSize = 80 / debuggingRatio;
        textFont =  new Font("Dited", textFontSize);
        blockTextVerticalInterval = 50 / debuggingRatio;
        lineTextVerticalInterval = 20 / debuggingRatio;


        groupText.getChildren().clear();

        questionNumberText = new Text();
        questionText = new Text();
        pointsReceivedText = new Text();

        answerOne = new QuizPaneTextAnswer();
        answerTwo = new QuizPaneTextAnswer();
        answerThree = new QuizPaneTextAnswer();
        answerFour = new QuizPaneTextAnswer();

        player.incrementQuestionNumber();

        if (player.getQuestionNumber() % 5 == 0) {
            int random = new Random().nextInt(questionMediaBlock.size());
            selectQuestion = questionMediaBlock.get(random);
            questionMediaBlock.remove(random);
        } else {
            int random = new Random().nextInt(questionBlock.size());
            selectQuestion = questionBlock.get(random);
            questionBlock.remove(random);
        }

        ArrayList<String> randomAnswer = selectQuestion.getRandomAnswer();

        String questionNumber = "Вопрос: " + String.valueOf(player.getQuestionNumber()) + " / " + String.valueOf(QUESTION_MAX);

        String pointsReceived = "Получено баллов: " + String.valueOf(player.getReceivePoints());


        questionNumberText.setText(questionNumber);
        questionNumberText.setFill(TEXT_COLOR);
        questionNumberText.setLayoutX(TEXT_X_CENTER - questionNumberText.getLayoutBounds().getWidth() / 2);
        questionNumberText.setLayoutY(QUESTION_NUMBER_TEXT_Y);

        StringBuilder point = new StringBuilder();
        point.append("(Вопрос на ");
        point.append(selectQuestion.getPoints());
        if (selectQuestion.getPoints() == 1) {
            point.append(" балл). ");
        } else {
            point.append(" балла). ");
        }

        questionText.setText(point.toString() + selectQuestion.getQuestion());
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


        groupText.getChildren().addAll(questionNumberText, questionText, answerOne, answerTwo, answerThree, answerFour, pointsReceivedText);
    }


    //метод уменьшающий размер текста и вертикальных интервалов,
    //если четверный ответ с вертикальным отступом находится ниже линии баллов
    private static void setFontSize() {

        double multiplier = 1;

        do {
            textFontSize *= multiplier;
            textFont =  new Font("Dited", textFontSize);
            blockTextVerticalInterval *= multiplier;
            lineTextVerticalInterval *= multiplier;

            questionNumberText.setFont(textFont);

            questionText.setFont(textFont);
            questionText.setLayoutY(questionNumberText.getLayoutY() + questionNumberText.getLayoutBounds().getHeight() + blockTextVerticalInterval);

            answerOne.setQPTAFont();
            answerOne.setQPTAHeight();
            answerOne.setLayoutY(questionText.getLayoutY() + questionText.getLayoutBounds().getHeight());

            answerTwo.setQPTAFont();
            answerTwo.setQPTAHeight();
            answerTwo.setLayoutY(answerOne.getLayoutY() + answerOne.getPrefHeight());

            answerThree.setQPTAFont();
            answerThree.setQPTAHeight();
            answerThree.setLayoutY(answerTwo.getLayoutY() + answerTwo.getPrefHeight());

            answerFour.setQPTAFont();
            answerFour.setQPTAHeight();
            answerFour.setLayoutY(answerThree.getLayoutY() + answerThree.getPrefHeight());

            pointsReceivedText.setFont(textFont);


            multiplier *= 0.95;
        }
        while ((answerFour.getLayoutY() + answerFour.getPrefHeight() + blockTextVerticalInterval) > pointsReceivedText.getLayoutY());

    }


    static void setResult() {

        groupText.getChildren().clear();

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

        groupText.getChildren().addAll(end, result);

    }

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

    static void setButton() {

        groupButton.getChildren().clear();

        //создание кнопок управления
//        if (selectQuestion.getMedia().length() == 0) {}
        QuizButtonMedia buttonMedia = new QuizButtonMedia(new File(RESOURCES_PATH + "quiz/buttonMedia.png"), selectQuestion.getMedia());
        buttonMedia.setLayoutX(505 / debuggingRatio);
        buttonMedia.setLayoutY(1840 / debuggingRatio);

        QuizButtonSelect buttonOne = new QuizButtonSelect("1", answerOne);
        buttonOne.setLayoutX(999 / debuggingRatio);
        buttonOne.setLayoutY(1840 / debuggingRatio);

        QuizButtonSelect buttonTwo = new QuizButtonSelect("2", answerTwo);
        buttonTwo.setLayoutX(1218 / debuggingRatio);
        buttonTwo.setLayoutY(1840 / debuggingRatio);

        QuizButtonSelect buttonThree = new QuizButtonSelect("3", answerThree);
        buttonThree.setLayoutX(1437 / debuggingRatio);
        buttonThree.setLayoutY(1840 / debuggingRatio);

        QuizButtonSelect buttonFour = new QuizButtonSelect("4", answerFour);
        buttonFour.setLayoutX(1656 / debuggingRatio);
        buttonFour.setLayoutY(1840 / debuggingRatio);

        QuizButtonSelectGroup buttonSelectGroup = new QuizButtonSelectGroup();
        buttonSelectGroup.add(buttonOne);
        buttonSelectGroup.add(buttonTwo);
        buttonSelectGroup.add(buttonThree);
        buttonSelectGroup.add(buttonFour);

        QuizButtonTest buttonTest = new QuizButtonTest("?", selectQuestion.getCorrectAnswer(), buttonSelectGroup);
        buttonTest.setLayoutX(1974 / debuggingRatio);
        buttonTest.setLayoutY(1840 / debuggingRatio);

        QuizButtonNext buttonNext = new QuizButtonNext(new File(RESOURCES_PATH + "quiz/buttonNext.png"), buttonMedia, buttonTest);
        buttonNext.setLayoutX(2478 / debuggingRatio);
        buttonNext.setLayoutY(1840 / debuggingRatio);

        groupButton.getChildren().addAll(buttonMedia, buttonSelectGroup, buttonTest, buttonNext);
    }

    static void setButtonResult() {

        groupButton.getChildren().clear();

        //создание кнопок управления
        QuizButton buttonPlay = new QuizButton(new File(RESOURCES_PATH + "quiz/buttonPlay.png"));
        buttonPlay.setLayoutX(505 / debuggingRatio);
        buttonPlay.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonOne = new QuizButton("1");
        buttonOne.setLayoutX(999 / debuggingRatio);
        buttonOne.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonTwo = new QuizButton("2");
        buttonTwo.setLayoutX(1218 / debuggingRatio);
        buttonTwo.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonThree = new QuizButton("3");
        buttonThree.setLayoutX(1437 / debuggingRatio);
        buttonThree.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonFour = new QuizButton("4");
        buttonFour.setLayoutX(1656 / debuggingRatio);
        buttonFour.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonTest = new QuizButton("?");
        buttonTest.setLayoutX(1974 / debuggingRatio);
        buttonTest.setLayoutY(1840 / debuggingRatio);

        QuizButtonNew buttonNew = new QuizButtonNew(new File(RESOURCES_PATH + "quiz/buttonNext.png"));
        buttonNew.setLayoutX(2478 / debuggingRatio);
        buttonNew.setLayoutY(1840 / debuggingRatio);

        groupButton.getChildren().addAll(buttonPlay, buttonOne, buttonTwo, buttonThree, buttonFour, buttonTest, buttonNew);
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

//    //Получение листа класса QuizQuestionWithMedia для викторины
//    // где QuizQuestion является представлением отдельного блока вопроса викторины
//    private static ArrayList<QuizQuestionWithMedia> getQuestionMediaBlock(final ArrayList<ArrayList<String>> quizTextBlock) {
//
//        ArrayList<QuizQuestionWithMedia> questionMediaBlock = new ArrayList<>();
//
//        for (ArrayList<String> block: quizTextBlock) {
//
//            int pointTemp = getPoints(block);
//            String questionTemp = getQuestion(block);
//            String correctAnswerTemp = getCorrectAnswer(block);
//            ArrayList<String> answerTemp = new ArrayList<>(getAnswers(block));
//            File mediaFileTemp = getMediaFile(block);
//
//            if (pointTemp != 0 &&
//                    !questionTemp.equals("") &&
//                    !correctAnswerTemp.equals("") &&
//                    answerTemp.size() != 0 &&
//                    !mediaFileTemp.toString().equals("")) {
//
//                questionMediaBlock.add(new QuizQuestionWithMedia(pointTemp, questionTemp, answerTemp, correctAnswerTemp, mediaFileTemp));
//            }
//        }
//
//        return questionMediaBlock;
//    }



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
