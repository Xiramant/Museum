package general;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static table.Main.*;

public class InitialLocation {

    //Метод первоначального расположения одинаковых панелей в шахматном порядке в заданной области
    //Если элементов в ряду меньше максимального количества,
    // то они располагаются по центру области с интервалом
    // равным интервалу для максимального количества элементов
    public static void initialPositionElementsForArea(final List<Node> elements,
                                                      final double minHorizontalInterval,
                                                      final double areaXBegin,
                                                      final double areaYBegin,
                                                      final double areaXEnd,
                                                      final double areaYEnd) {

        //размеры элемента (панели)
        double elementWidth = ((Pane)elements.get(0)).getPrefWidth();
        double elementHeight = ((Pane)elements.get(0)).getPrefHeight();

        //размеры области для размещения элементов
        double areaWidth = areaXEnd - areaXBegin;
        double areaHeight = areaYEnd - areaYBegin;

        //Определение максимального количества элементов в первой строке
        int maxElementsInFirstRow = getMaxElementsInFirstRow(areaWidth, elementWidth, minHorizontalInterval);

        //Группа элементов распределенная по рядам
        // в шахматном порядке
        //Внешний список - список рядов
        //Внутренний список - элементы в ряду
        ArrayList<ArrayList<Pane>> groupElements =
                setStaggeredArrangementElements(elements, minHorizontalInterval, areaWidth);

        //интервал между элементами в первой строке
        double firstRowInterval = (groupElements.get(0).size() > 1)?
                (areaWidth - (maxElementsInFirstRow * elementWidth)) / (maxElementsInFirstRow - 1):
                0;

        //вертикальный интервал между рядами
        double verticalInterval = (groupElements.size() > 1)?
                (areaHeight - (groupElements.size() * elementHeight)) / (groupElements.size() - 1):
                0;

        //установка координат для элементов
        for (int i = 0; i < groupElements.size(); i++) {

            //отступ сверху
            //если строка одна, то ряд располагается (вертикально) по середине области
            double topInset = (groupElements.size() > 1)?
                    areaYBegin + i * (elementHeight + verticalInterval):
                    areaYBegin + (areaHeight - elementHeight) / 2;

            //разница в количестве элементов в первом ряду и текущем ряду
            int elementsNumberDifference = maxElementsInFirstRow - groupElements.get(i).size();

            //установка горизонтальных координат
            setRowDistributionElements(groupElements.get(i),
                    areaWidth - elementsNumberDifference * (elementWidth + firstRowInterval),
                    areaXBegin + elementsNumberDifference * (elementWidth + firstRowInterval) / 2);

            //установка вертикальных координат
            setElementsY(groupElements.get(i), topInset);
        }
    }

    //Определение максимального количества элементов в первой строке
    private static int getMaxElementsInFirstRow(final double areaWidth, final double elementWidth, final double minHorizontalInterval) {

        int maxElementsInFirstRow = (int) (areaWidth / (elementWidth + minHorizontalInterval));
        maxElementsInFirstRow =
                (((maxElementsInFirstRow + 1) * elementWidth + elementWidth * minHorizontalInterval) > areaWidth)?
                        maxElementsInFirstRow: maxElementsInFirstRow + 1;

        return maxElementsInFirstRow;
    }


    //Распределение переданного листа элементов
    // в список списков элементов их расположения
    // в заданной области с установленной шириной в шахматном порядке
    // (высота заданной области не требуется,
    // т.к. если элементы не влезут по высоте, то ужать их все-равно не получится)
    //Внешний список - список рядов
    //Внутренний список - элементы в ряду
    private static ArrayList<ArrayList<Pane>> setStaggeredArrangementElements (final List<Node> elements,
                                                                               final double minHorizontalInterval,
                                                                               final double areaWidth) {

        //ширина элемента (панели)
        double elementWidth = ((Pane)elements.get(0)).getPrefWidth();

        //Определение максимального количества элементов в первой строке
        int maxElementsInFirstRow = getMaxElementsInFirstRow(areaWidth, elementWidth, minHorizontalInterval);

        //Лист элементов с разбитием по рядам
        //Внешний список - список рядов
        //Внутренний список - элементы в ряду
        ArrayList<ArrayList<Pane>> groupElements = new ArrayList<>();

        //Временный список группы элементов в ряду
        ArrayList<Pane> groupElementsInRow = new ArrayList<>();

        //Максимальное количество элементов в ряду
        //Для табличной формы расположения элементов
        // оно бы соответствовало maxElementsInFirstRow
        //Для шахматного расположения элементов
        // в нечетном ряду количество элементов должно быть на 1 меньше,
        // чем в четном (или наоборот)
        int maxElementsInRow;

        //Номер ряда
        int rowNumber = 0;

        //Текущий элемент в ряду
        int currentElementInRow = 0;

        //расположение элементов в groupElements
        for (int i = 0; i < elements.size(); i++) {

            maxElementsInRow = (rowNumber % 2 == 0)? maxElementsInFirstRow: maxElementsInFirstRow - 1;

            if (currentElementInRow == maxElementsInRow) {
                //Т.к. объекты передаются по ссылкам, то
                // для того, чтобы внешний лист groupElements
                // не ссылался все разы только на последнюю версию groupElementsInRow
                // нужно создавать новый внешний лист,
                // в конструктор которого передается ссылка на groupElementsInRow
                groupElements.add(new ArrayList<>(groupElementsInRow));
                groupElementsInRow.clear();
                currentElementInRow = 0;
                rowNumber++;
            }

            groupElementsInRow.add((Pane) elements.get(i));
            currentElementInRow++;
        }

        //Добавление последнего ряда в groupElements,
        // т.к. цикл не доходит до условия в котором он добавляется
        groupElements.add(groupElementsInRow);

        return groupElements;
    }


    //Задание координат элементов в ряду,
    // для равномерного распределения по ряду
    // с заданной шириной и отступом (слева)
    // Если элемент один, то он распологается по центру области
    // Если элементов больше одного, то первый и последний элемент располагаются по краям области
    private static void setRowDistributionElements (final List<Pane> elements, final double width, final double xInset) {

        if (elements.size() == 0) {
            System.out.println("В метод setRowDistributionElements() передан пустой список");
            return;
        }

        double elementWidth = elements.get(0).getPrefWidth();

        if (elements.size() == 1) {
            elements.get(0).setLayoutX(xInset + width - elementWidth);
            return;
        }

        double horizontalInterval = (width - (elements.size() * elementWidth)) / (elements.size() - 1);

        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setLayoutX(xInset + i * (elementWidth + horizontalInterval));
        }
    }


    //Задание вертикальной координаты всем элементам в переданном списке
    private static void setElementsY (final List<Pane> elements, final double y) {

        for (Pane element: elements) {
            element.setLayoutY(y);
        }
    }


    //Метод первоначального расположения панелей на SectionPane в шахматном порядке
    //Элементы в строке располагаются с равным расстоянием между элементами
    //Вертикальное выравнивание в строке - по низу элементов
    public static void initialPositionElements(final double sectionWidth, final double sectionHeight, final List<Node> elements, final double widthPlusInterval) {

        //Определение максимального количества элементов в первой строке
        int maxElementsInFirstRow = (int) (sectionWidth / widthPlusInterval);

        //Лист элементов с разбитием по рядам
        //Внешний список - список рядов
        //Внутренний список - элементы в ряду
        ArrayList<ArrayList<Pane>> groupElements = new ArrayList<>();

        //Временный список группы элементов в ряду
        ArrayList<Pane> groupElementsInRow = new ArrayList<>();

        //Максимальное количество элементов в ряду
        //Для табличной формы расположения элементов
        // оно бы соответствовало maxElementsInFirstRow
        //Для шахматного расположения элементов
        // в нечетном ряду количество элементов должно быть на 1 меньше,
        // чем в четном (или наоборот)
        int maxElementsInRow;

        //Номер ряда
        int rowNumber = 0;

        //Текущий элемент в ряде
        int currentElementInRow = 0;

        //расположение элементов в groupElements
        for (int i = 0; i < elements.size(); i++) {

            maxElementsInRow = (rowNumber % 2 == 0)? maxElementsInFirstRow: maxElementsInFirstRow - 1;

            if (currentElementInRow == maxElementsInRow) {
                //Т.к. объекты передаются по ссылкам, то
                // для того, чтобы внешний лист groupElements
                // не ссылался все разы только на последнюю версию groupElementsInRow
                // нужно создавать новый внешний лист,
                // в конструктор которого передается ссылка на groupElementsInRow
                groupElements.add(new ArrayList<>(groupElementsInRow));
                groupElementsInRow.clear();
                currentElementInRow = 0;
                rowNumber++;
            }

            groupElementsInRow.add((Pane) elements.get(i));
            currentElementInRow++;
        }

        //Добавление последнего ряда в groupElements,
        // т.к. цикл не доходит до условия в котором он добавляется
        groupElements.add(groupElementsInRow);

        //Лист сумм длин всех элементов в одном ряду
        ArrayList<Double> sumWidthInRow = new ArrayList<>();

        //Лист максимальных высот элементов в одном ряду
        ArrayList<Double> maxHeightInRow = new ArrayList<>();

        for (ArrayList<Pane> elementsInRow: groupElements) {

            double sumWidth = 0;
            double maxHeight = 0;

            for (Pane pane: elementsInRow) {

                sumWidth += pane.getPrefWidth();

                if (maxHeight < pane.getPrefHeight()) {
                    maxHeight = pane.getPrefHeight();
                }
            }

            sumWidthInRow.add(sumWidth);
            maxHeightInRow.add(maxHeight);
        }

        //Сумма максимальных высот элементов в ряду
        int sumMaxHeightInRow = 0;

        for (double maxHeight: maxHeightInRow) {
            sumMaxHeightInRow += maxHeight;
        }

        //Лист горизонтальных отступов между элементами в каждом ряду
        ArrayList<Double> xSpacingInRow = new ArrayList<>();

        //Вертикальный отступ между рядами элементов
        double ySpacing = 0;

        for (int i = 0; i < groupElements.size(); i++) {
            xSpacingInRow.add((sectionWidth - sumWidthInRow.get(i)) / (groupElements.get(i).size() + 1));
        }

        for (int i = 0; i < groupElements.size(); i++) {
            ySpacing = (sectionHeight - sumMaxHeightInRow) / (groupElements.size() + 1);
        }

        //распределение элементов по горизонтали
        for (int i = 0; i < groupElements.size(); i++) {

            double leftSpace = 0;

            for (Pane pane: groupElements.get(i)) {
                leftSpace += xSpacingInRow.get(i);
                pane.setLayoutX(leftSpace);
                leftSpace += pane.getPrefWidth();
            }
        }

        //распределение элементов по вертикали
        double topSpace = 0;

        for (int i = 0; i < groupElements.size(); i++) {

            topSpace += ySpacing + maxHeightInRow.get(i);

            for (Pane pane: groupElements.get(i)) {
                pane.setLayoutY(topSpace - pane.getPrefHeight());
            }
        }
    }

    //заглушка для старого стола
    public static void initialPositionElements(final List<Node> elements, final double widthPlusInterval) {
        initialPositionElements(TABLE_CENTER_SECTION_WIDTH, TABLE_CENTER_SECTION_HEIGHT, elements, widthPlusInterval);
    }

    public static void setRandomPositionInArea(final List<Node> elements,
                                               final double xBeginArea,
                                               final double yBeginArea,
                                               final double xEndArea,
                                               final double yEndArea) {

        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setLayoutX(randomInRange(xBeginArea, xEndArea));
            elements.get(i).setLayoutY(randomInRange(yBeginArea, yEndArea));
        }
    }

    public static double randomInRange(final double begin, final double end) {
        return new Random().nextInt((int)(end - begin)) + begin;
    }
}
