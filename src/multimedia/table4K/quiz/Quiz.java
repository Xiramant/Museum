package table4K.quiz;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static general.TextProcessing.readingFileIntoStringList;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;


public class Quiz {

    private static Group groupText;

    private static final Font TEXT_FONT =  new Font("Dited", 80/debuggingRatio);

    private static final Color TEXT_COLOR = Color.rgb(7, 153, 89);

    private static final double TEXT_X_CENTER = 1610 / debuggingRatio;

    private static final double QUESTION_NUMBER_TEXT_Y = 398 / debuggingRatio;

    private static final double QUESTION_TEXT_WIDTH_MAX = 1800 / debuggingRatio;

    private static final double POINTS_RECEIVED_TEXT_Y = 1456 / debuggingRatio;

    private static final double BLOCK_TEXT_HORIZONTAL_INTERVAL = 50 / debuggingRatio;

    private static final double LINE_TEXT_HORIZONTAL_INTERVAL = 20 / debuggingRatio;

    private static ArrayList<QuizQuestion> questionBlock;

    private static QuizPlayer player;

    private static final int QUESTION_MAX = 20;



    public static void setQuizScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_quiz.jpg");
        mainPane.getChildren().clear();

        QuizButton buttonPlay = new QuizButton(new File(RESOURCES_PATH + "quiz/buttonPlay.png"));
        buttonPlay.setLayoutX(505 / debuggingRatio);
        buttonPlay.setLayoutY(1840 / debuggingRatio);

        QuizButton buttonNext = new QuizButton(new File(RESOURCES_PATH + "quiz/buttonNext.png"));
        buttonNext.setLayoutX(2478 / debuggingRatio);
        buttonNext.setLayoutY(1840 / debuggingRatio);

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

        QuizButton buttonQuestion = new QuizButton("?");
        buttonQuestion.setLayoutX(1974 / debuggingRatio);
        buttonQuestion.setLayoutY(1840 / debuggingRatio);


        mainPane.getChildren().addAll(buttonPlay, buttonOne, buttonTwo, buttonThree, buttonFour, buttonQuestion, buttonNext);


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

        buttonOne.setOnMouseClicked(event -> {
            
        });

    }


    private static Group setQuestion() {

        Text questionNumberText = new Text();
        Text questionText = new Text();
        Text answerOneText = new Text();
        Text answerTwoText = new Text();
        Text answerThreeText = new Text();
        Text answerFourText = new Text();
        Text pointsReceivedText = new Text();

        Group groupText = new Group();
        groupText.getChildren().addAll(questionNumberText, questionText, answerOneText, answerTwoText, answerThreeText, answerFourText, pointsReceivedText);

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
        questionText.setLayoutX(TEXT_X_CENTER - questionText.getWrappingWidth() / 2);
        questionText.setLayoutY(questionNumberText.getLayoutY() + questionNumberText.getLayoutBounds().getHeight() + BLOCK_TEXT_HORIZONTAL_INTERVAL);

        answerOneText.setText("1.  " + randomAnswer.get(0));
        answerOneText.setWrappingWidth(questionText.getWrappingWidth());
        answerOneText.setFont(TEXT_FONT);
        answerOneText.setFill(TEXT_COLOR);
        answerOneText.setLayoutX(questionText.getLayoutX());
        answerOneText.setLayoutY(questionText.getLayoutY() + questionText.getLayoutBounds().getHeight() + BLOCK_TEXT_HORIZONTAL_INTERVAL);

        answerTwoText.setText("2. " + randomAnswer.get(1));
        answerTwoText.setWrappingWidth(questionText.getWrappingWidth());
        answerTwoText.setFont(TEXT_FONT);
        answerTwoText.setFill(TEXT_COLOR);
        answerTwoText.setLayoutX(questionText.getLayoutX());
        answerTwoText.setLayoutY(answerOneText.getLayoutY() + answerOneText.getLayoutBounds().getHeight() + LINE_TEXT_HORIZONTAL_INTERVAL);

        answerThreeText.setText("3. " + randomAnswer.get(2));
        answerThreeText.setWrappingWidth(questionText.getWrappingWidth());
        answerThreeText.setFont(TEXT_FONT);
        answerThreeText.setFill(TEXT_COLOR);
        answerThreeText.setLayoutX(questionText.getLayoutX());
        answerThreeText.setLayoutY(answerTwoText.getLayoutY() + answerTwoText.getLayoutBounds().getHeight() + LINE_TEXT_HORIZONTAL_INTERVAL);

        answerFourText.setText("4. " + randomAnswer.get(3));
        answerFourText.setWrappingWidth(questionText.getWrappingWidth());
        answerFourText.setFont(TEXT_FONT);
        answerFourText.setFill(TEXT_COLOR);
        answerFourText.setLayoutX(questionText.getLayoutX());
        answerFourText.setLayoutY(answerThreeText.getLayoutY() + answerThreeText.getLayoutBounds().getHeight() + LINE_TEXT_HORIZONTAL_INTERVAL);

        pointsReceivedText.setText(pointsReceived);
        pointsReceivedText.setFont(TEXT_FONT);
        pointsReceivedText.setFill(TEXT_COLOR);
        pointsReceivedText.setLayoutX(TEXT_X_CENTER - pointsReceivedText.getLayoutBounds().getWidth() / 2);
        pointsReceivedText.setLayoutY(POINTS_RECEIVED_TEXT_Y);

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
