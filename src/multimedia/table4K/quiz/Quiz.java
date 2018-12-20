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

    private static Group groupText;

    static final Font TEXT_FONT =  new Font("Dited", 80/debuggingRatio);

    static final Color TEXT_COLOR = Color.rgb(7, 153, 89);

    private static final double TEXT_X_CENTER = 1610 / debuggingRatio;

    private static final double QUESTION_NUMBER_TEXT_Y = 398 / debuggingRatio;

    static final double QUESTION_TEXT_WIDTH_MAX = 1800 / debuggingRatio;

    private static final double POINTS_RECEIVED_TEXT_Y = 1456 / debuggingRatio;

    static final double BLOCK_TEXT_VERTICAL_INTERVAL = 50 / debuggingRatio;

    static final double LINE_TEXT_VERTICAL_INTERVAL = 20 / debuggingRatio;

    private static ArrayList<QuizQuestion> questionBlock;

    private static QuizPlayer player;

    private static final int QUESTION_MAX = 20;

    static QuizPaneTextAnswer answerOne = new QuizPaneTextAnswer();
    static QuizPaneTextAnswer answerTwo = new QuizPaneTextAnswer();
    static QuizPaneTextAnswer answerThree = new QuizPaneTextAnswer();
    static QuizPaneTextAnswer answerFour = new QuizPaneTextAnswer();


    public static void setQuizScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_quiz.jpg");
        mainPane.getChildren().clear();

        QuizButton buttonPlay = new QuizButton(new File(RESOURCES_PATH + "quiz/buttonPlay.png"));
        buttonPlay.setLayoutX(505 / debuggingRatio);
        buttonPlay.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonNext = new QuizButton(new File(RESOURCES_PATH + "quiz/buttonNext.png"));
        buttonNext.setLayoutX(2478 / debuggingRatio);
        buttonNext.setLayoutY(1840 / debuggingRatio);

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

        QuizButton buttonQuestion = new QuizButton("?");
        buttonQuestion.setLayoutX(1974 / debuggingRatio);
        buttonQuestion.setLayoutY(1840 / debuggingRatio);


        QuizButtonSelectGroup buttonGroup = new QuizButtonSelectGroup();
        buttonGroup.add(buttonOne);
        buttonGroup.add(buttonTwo);
        buttonGroup.add(buttonThree);
        buttonGroup.add(buttonFour);

        mainPane.getChildren().addAll(buttonPlay, buttonGroup, buttonQuestion, buttonNext);


        //Текст викторины разбитый по строчкам
        ArrayList<String> quizText = readingFileIntoStringList(new File(RESOURCES_PATH + "quiz/quiz_text.txt"));

        //Блоки вопросов викторины представленные в виде листа
        ArrayList<ArrayList<String>> quizTextBlock = new ArrayList<>(getQuizTextBlock(quizText));

        //Блоки вопросов викторины представлены в виде класса questionBlock
        questionBlock = new ArrayList<>(getQuestionBlock(quizTextBlock));

        player = new QuizPlayer();

        groupText = setQuestion();

        mainPane.getChildren().addAll(groupText);



        mainPane.getChildren().addAll(returnHome());


    }


    private static Group setQuestion() {

        Text questionNumberText = new Text();
        Text questionText = new Text();
        Text pointsReceivedText = new Text();

        Group groupText = new Group();

        player.incrementQuestionNumber();

        ArrayList<QuizQuestion> questionBlockTemp = new ArrayList<>(questionBlock);

        int random = new Random().nextInt(questionBlockTemp.size());

        QuizQuestion selectQuestion = questionBlockTemp.get(random);
        questionBlockTemp.remove(random);

        ArrayList<String> randomAnswer = selectQuestion.getRandomAnswer();

        String questionNumber = "Вопрос: " + String.valueOf(player.getQuestionNumber()) + " / " + String.valueOf(QUESTION_MAX);

        String pointsReceived = "Получено баллов: " + String.valueOf(player.getReceivePoints());


        questionNumberText.setText(questionNumber);
        questionNumberText.setFont(TEXT_FONT);
        questionNumberText.setFill(TEXT_COLOR);
        questionNumberText.setLayoutX(TEXT_X_CENTER - questionNumberText.getLayoutBounds().getWidth() / 2);
        questionNumberText.setLayoutY(QUESTION_NUMBER_TEXT_Y);

        questionText.setText(selectQuestion.getQuestion());
        questionText.setWrappingWidth(QUESTION_TEXT_WIDTH_MAX);
        questionText.setFont(TEXT_FONT);
        questionText.setFill(TEXT_COLOR);
        questionText.setTextAlignment(TextAlignment.JUSTIFY);
        questionText.setLayoutX(TEXT_X_CENTER - questionText.getWrappingWidth() / 2);
        questionText.setLayoutY(questionNumberText.getLayoutY() + questionNumberText.getLayoutBounds().getHeight() + BLOCK_TEXT_VERTICAL_INTERVAL);

        answerOne.setQPTAText("1.  " + randomAnswer.get(0));
        answerOne.setLayoutX(questionText.getLayoutX());
        answerOne.setLayoutY(questionText.getLayoutY() + questionText.getLayoutBounds().getHeight());

        answerTwo.setQPTAText("2. " + randomAnswer.get(1));
        answerTwo.setLayoutX(questionText.getLayoutX());
        answerTwo.setLayoutY(answerOne.getLayoutY() + answerOne.getPrefHeight());

        answerThree.setQPTAText("3. " + randomAnswer.get(2));
        answerThree.setLayoutX(questionText.getLayoutX());
        answerThree.setLayoutY(answerTwo.getLayoutY() + answerTwo.getPrefHeight());

        answerFour.setQPTAText("4. " + randomAnswer.get(3));
        answerFour.setLayoutX(questionText.getLayoutX());
        answerFour.setLayoutY(answerThree.getLayoutY() + answerThree.getPrefHeight());

        pointsReceivedText.setText(pointsReceived);
        pointsReceivedText.setFont(TEXT_FONT);
        pointsReceivedText.setFill(TEXT_COLOR);
        pointsReceivedText.setLayoutX(TEXT_X_CENTER - pointsReceivedText.getLayoutBounds().getWidth() / 2);
        pointsReceivedText.setLayoutY(POINTS_RECEIVED_TEXT_Y);


        groupText.getChildren().addAll(questionNumberText, questionText, answerOne, answerTwo, answerThree, answerFour, pointsReceivedText);



        return groupText;
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
            int pointTemp = 0;
            String questionTemp = "";
            String correctAnswerTemp = "";
            ArrayList<String> answerTemp = new ArrayList<>();

            for (String str: block) {

                if (str.startsWith("Баллов: ")) {
                    String strTemp = str.substring((new String("Баллов: ")).length());
                    strTemp = strTemp.replaceAll(" ", "");
                    pointTemp = (Integer.parseInt(strTemp));
                    continue;
                }

                if (str.startsWith("Вопрос: ")) {
                    questionTemp = str.substring((new String("Вопрос: ")).length());
                    continue;
                }

                if (str.startsWith("+ ") || str.startsWith("- ")) {
                    answerTemp.add(str.substring((new String("+ ")).length()));

                    if (str.startsWith("+ ")) {
                        correctAnswerTemp = str.substring((new String("+ ")).length());
                    }
                }
            }

            if (pointTemp != 0 &&
                    !questionTemp.equals("") &&
                    !correctAnswerTemp.equals("") &&
                    answerTemp.size() != 0) {

                questionBlock.add(new QuizQuestion(pointTemp, questionTemp, answerTemp, correctAnswerTemp));
            }
        }

        return questionBlock;
    }
}
