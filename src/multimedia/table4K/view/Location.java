package table4K.view;

import general.GroupingOrder;
import javafx.scene.shape.Rectangle;
import table4K.model.AreaLocation;
import table4K.model.Dimension;

import java.util.ArrayList;
import java.util.List;

import static general.GroupingOrder.STAGGERED;
import static general.GroupingOrder.TABLED;
import static table4K.view.MainView.TABLE_HEIGHT;

public class Location {

    private static final double SCALE_FACTOR_INITIAL = 1;
    private static final double SCALE_FACTOR_DECREASE = 0.9;



    //Метод первоначального расположения одинаковых графических элементов
    // в заданном порядке (шахматном/табличном) в заданной области
    //Если элементов в ряду меньше максимального количества,
    // то они располагаются по центру области с интервалом
    // равным интервалу для максимального количества элементов
    public static void setElementsLocation( final List<Dimension> elementListArg,
                                             final AreaLocation areaArg,
                                             final GroupingOrder orderArg ) {

        scalingElementsForPlacementInArea( elementListArg, areaArg, orderArg );

        double elementWidth = elementListArg.get( 0 ).getWidthWithScaling();

        int maxElementsInRow = getMaxElementsInRow( areaArg.getWidth(),
                                                    elementWidth,
                                                    areaArg.getMinHorizontalSpacing() );

        //Группировка элементов по рядам
        // в заданном GroupingOrder порядке.
        //Внешний список - список рядов,
        // внутренний список - элементы в ряду.
        ArrayList< ArrayList< Dimension > > groupedElements = groupingElements( elementListArg,
                                                                                maxElementsInRow,
                                                                                orderArg );

        //Установка вертикальных координат
        setVerticalCoordinates( groupedElements, areaArg );

        //установка горизонтальных координат
        setHorizontalCoordinates( groupedElements, areaArg, maxElementsInRow );

    }



    //Масштабирование элементов для того,
    // чтобы они поместились в заданной области
    private static void scalingElementsForPlacementInArea(final List<Dimension> elementListArg,
                                                          final AreaLocation areaArg,
                                                          final GroupingOrder orderArg ) {

        double scaleFactor = SCALE_FACTOR_INITIAL;

        while ( verticalSpacingNotEnough( elementListArg, areaArg, orderArg ) ) {
            scaleFactor *= SCALE_FACTOR_DECREASE;
            scalingElements( elementListArg, scaleFactor);
        }

    }

    private static void scalingElements( final List<Dimension> elementListArg,
                                         final double scaleFactorArg) {
        for ( Dimension element : elementListArg ) {
            element.setScaling( scaleFactorArg );
        }
    }

    private static boolean verticalSpacingNotEnough( final List<Dimension> elementListArg,
                                                     final AreaLocation areaArg,
                                                     final GroupingOrder orderArg ) {
        return getVerticalSpacing( elementListArg, areaArg, orderArg )
                < areaArg.getMinVerticalSpacing();

    }

    private static double getVerticalSpacing( final List<Dimension> elementListArg,
                                              final AreaLocation areaArg,
                                              final GroupingOrder orderArg){

        int maxElementsInRow = getMaxElementsInRow( areaArg.getWidth(),
                                                    elementListArg.get( 0 ).getWidthWithScaling(),
                                                    areaArg.getMinHorizontalSpacing() );

        //Если все элементы умещаются в один ряд,
        // то интервала между рядами не существует.
        //Т.к. бесконечность возвращать смысла нет, то возвращаем TABLE_HEIGHT
        if ( maxElementsInRow >= elementListArg.size() ) return TABLE_HEIGHT;

        int rowsNumber = getRowsNumber( orderArg,
                                        elementListArg.size(),
                                        maxElementsInRow );
        double areaHeight = areaArg.getHeight();
        double elementHeight = elementListArg.get( 0 ).getHeightWithScaling();

        //Предполагается, что размещение первого ряда идет строго по верху заданной области
        // а последнего - строго по низу заданной области.
        //В противном случае вычисления не верны.
        double spacingHeight = areaHeight - rowsNumber * elementHeight;
        int spacingNumber = rowsNumber - 1;

        return ( spacingNumber != 0 ) ?
                spacingHeight / spacingNumber :
                TABLE_HEIGHT;
    }

    private static int getRowsNumber( final GroupingOrder orderArg,
                                      final int elementListNumberArg,
                                      final int MaxElementsInRowArg) {
        switch ( orderArg ) {
            case STAGGERED:
                return getStaggeredRowsNumber( elementListNumberArg, MaxElementsInRowArg );
            case TABLED:
                return getTableRowsNumber( elementListNumberArg, MaxElementsInRowArg );
            default:
                return getTableRowsNumber( elementListNumberArg, MaxElementsInRowArg );
        }
    }

    private static int getTableRowsNumber( final int elementListNumberArg,
                                           final int MaxElementsInRowArg ) {
        return ( elementListNumberArg % MaxElementsInRowArg == 0 ) ?
                ( elementListNumberArg / MaxElementsInRowArg ) :
                ( elementListNumberArg / MaxElementsInRowArg ) + 1;
    }

    //Расположение в шахматном порядке:
    // четная строчка (начиная с 0-й) == максимальное количество элементов, вместившееся в ряду,
    // нечетная строчка (начиная с 1-й) == максимальное количество в ряду - 1
    // последняя строчка - оставшееся количество элементов.
    //Вычисление количества рядов при расположении элементов в шахматном порядке:
    // если общее количество разделить на среднее количество в ряду, то при получении:
    // 0 - возвращаем получившуюся цифру;
    // четного числа с дробным результатом - возвращаем получившуюся цифру + 1;
    // нечетного числа с дробным результатом - требуется дальнейшее уточнение:
    //      от общего количества отнимаем количество в первом ряду и делим на срденее количество в ряду. При получении:
    //      0 - возвращаем получившуюся цифру + 1;
    //      четного числа с дробным результом - возвращаем получившуюся цифру + 2.
    //      нечетное число с дробным результатом получиться не должно.
    //Среднее количество элементов в ряду = количество в четной строчке / 2 + количество в нечетной строчке / 2.
    //Т.к. среднее количество элементов в ряду будет всегда дробью int/2, то
    // чтобы не возиться со сравнением дробных чисел с использованием минимального интервала,
    // используется умножение чисел на 2.
    // В результате вычисление количества строк проводится с использованием целых чисел.
    private static int getStaggeredRowsNumber( final int elementListNumberArg,
                                               final int MaxElementsInRowArg ) {

        int rowsNumber = ( ( elementListNumberArg * 2 ) / ( ( MaxElementsInRowArg * 2 ) - 1 ) );

        if ( rowsNumberInteger( elementListNumberArg, MaxElementsInRowArg ) ) {
            return rowsNumber;
        }

        if ( rowsNumber % 2 == 0 ) {
            return rowsNumber + 1;
        }

        int rowsNumberWithoutFirstRow = ( ( ( elementListNumberArg - MaxElementsInRowArg ) * 2 )
                / ( ( MaxElementsInRowArg * 2 ) - 1 ) );

        if ( rowsNumberInteger( (elementListNumberArg - MaxElementsInRowArg ), MaxElementsInRowArg ) ) {
            return rowsNumberWithoutFirstRow + 1;
        }

        return rowsNumberWithoutFirstRow + 2;

    }

    private static boolean rowsNumberInteger(final int elementListNumberArg,
                                             final int MaxElementsInRowArg) {
        return ( elementListNumberArg * 2 ) % ( ( MaxElementsInRowArg * 2 ) - 1 ) == 0;
    }



    private static void setVerticalCoordinates(final ArrayList< ArrayList< Dimension > > groupedElementsArg,
                                               final AreaLocation areaArg) {

        double elementHeight = groupedElementsArg.get( 0 ).get( 0 ).getHeightWithScaling();

        //Если строка одна, то ряд располагается вертикально по середине области,
        // а не приклеивается к верхней или нижней границе области.
        if ( groupedElementsArg.size() == 1 ) {
            double topPadding = areaArg.getY() + ( areaArg.getHeight() - elementHeight ) / 2;
            setElementsY( groupedElementsArg.get( 0 ), topPadding );
            return;
        }

        double verticalSpacing = getVerticalSpacing( groupedElementsArg, areaArg );

        for ( int rowIndex = 0; rowIndex < groupedElementsArg.size(); rowIndex++ ) {
            double topPadding = areaArg.getY() + rowIndex * ( elementHeight + verticalSpacing );
            setElementsY( groupedElementsArg.get( rowIndex ), topPadding );
        }

    }

    private static void setHorizontalCoordinates(final ArrayList< ArrayList< Dimension > > groupedElementsArg,
                                                 final AreaLocation areaArg,
                                                 final int maxElementsInRowArg) {

        //В качесте горизонтального интервала между элементами в любой строке
        // используется горизонтальный интервал для строки с максимальным количеством элементов.
        double horizontalSpacing = getHorizontalSpacing( groupedElementsArg, areaArg, maxElementsInRowArg );

        for ( ArrayList< Dimension > rowElements : groupedElementsArg ) {
            setHorizontalCoordinatesInRow(rowElements, areaArg, horizontalSpacing);
        }

    }

    private static void setHorizontalCoordinatesInRow(final ArrayList< Dimension > rowElementsArg,
                                                      final AreaLocation areaArg,
                                                      final double horizontalSpacingArg) {

        double elementWidth = rowElementsArg.get(0).getWidthWithScaling();

        double rowWidth = ( elementWidth * rowElementsArg.size() ) + ( horizontalSpacingArg * ( rowElementsArg.size() - 1 ) );
        double leftPadding = areaArg.getX() + ( areaArg.getWidth() - rowWidth ) / 2;

        setRowDistributionElements( rowElementsArg, rowWidth, leftPadding );
    }



    private static double getHorizontalSpacing( final ArrayList<ArrayList<Dimension>> elementsArg,
                                                final Rectangle areaArg,
                                                final int maxElementsInRowArg) {

        double elementWidth = elementsArg.get( 0 ).get( 0 ).getWidthWithScaling();

        return ( elementsArg.get( 0 ).size() > 1 ) ?
                ( areaArg.getWidth() - ( maxElementsInRowArg * elementWidth ) ) / ( maxElementsInRowArg - 1 ) :
                0;
    }

    private static double getVerticalSpacing( final ArrayList< ArrayList< Dimension > > elementsArg,
                                              final Rectangle areaArg ) {

        double elementHeight = elementsArg.get( 0 ).get( 0 ).getHeightWithScaling();
        int rowsNumberElements = elementsArg.size();

        return ( rowsNumberElements > 1 ) ?
                ( areaArg.getHeight() - ( rowsNumberElements * elementHeight ) ) / ( rowsNumberElements - 1 ) :
                0;
    }



    //Для корректного определения максимального количества элементов в первом ряду
    // к ширине области в числителе необходимо добавить размер минимального горизонтального интервала.
    // Без этого, например, если у нас задано:
    // ширина области = 260 пикселей,
    // 2 объекта по 100 пикселей,
    // минимальный интервал = 50 пикселей.
    //При вычислении 260 / (100 + 50) = 1.73,
    // т.е. в заданной области может поместиться только 1 объект,
    // хотя это и не так: 100 + 50 + 100 = 250 < 260.
    //Это получается из-за лишнего интервала в знаменателе для последнего объекта.
    //Поэтому для корректного вычисления количества объектов
    // в числителе прибавляется 1 минимальный горизонтальный интервал.
    private static int getMaxElementsInRow( final double areaWidth,
                                            final double elementWidth,
                                            final double minHorizontalInterval ) {
        return ( int ) ( ( areaWidth + minHorizontalInterval ) / ( elementWidth + minHorizontalInterval ) );
    }


    //Группировка переданного листа элементов
    // в список списков элементов
    // по их расположению в заданной области
    //Внешний список - список рядов,
    // внутренний список - элементы в ряду
    private static ArrayList< ArrayList< Dimension > > groupingElements(final List<Dimension> elementListArg,
                                                                        final int maxElementsInRowArg,
                                                                        final GroupingOrder orderArg ) {

        //Внешний список - список рядов
        //Внутренний список - элементы в ряду
        ArrayList< ArrayList< Dimension > > out = new ArrayList<>();

        ArrayList< Dimension > elementsInRow = new ArrayList<>();

        //Максимальное количество элементов в ряду
        //Для табличной формы расположения элементов
        // соответствует maxElementsInFirstRow
        //Для шахматного расположения элементов
        // в нечетном ряду количество элементов на 1 меньше,
        // чем в четном (или наоборот)
        int maxNumberInRow;

        int rowNumber = 0;

        int currentElementInRow = 0;

        //группировка элементов
        for ( Dimension element : elementListArg ) {

            maxNumberInRow = getMaxNumberInRow( orderArg, rowNumber, maxElementsInRowArg );

            if ( currentElementInRow == maxNumberInRow ) {
                //Т.к. объекты передаются по ссылкам, то
                // для того, чтобы внешний лист groupElements
                // не ссылался все разы только на последнюю версию groupElementsInRow
                // нужно создавать новый внешний лист
                out.add( new ArrayList<>( elementsInRow ) );
                elementsInRow.clear();
                currentElementInRow = 0;
                rowNumber++;
            }

            elementsInRow.add( element );
            currentElementInRow++;
        }

        //Добавление последнего ряда в groupElements,
        // т.к. цикл не доходит до условия в котором он добавляется
        out.add( elementsInRow );

        return out;
    }

    private static int getMaxNumberInRow( final GroupingOrder orderArg,
                                          final int rowNumber,
                                          final int maxElementsInRowArg) {

        if ( orderArg == TABLED ) {
            return maxElementsInRowArg;
        }

        if ( orderArg == STAGGERED ) {
            return  isEven( rowNumber ) ? maxElementsInRowArg : maxElementsInRowArg - 1;
        }

        return 0;
    }

    private static boolean isEven( final int numberArg ) {
        return numberArg % 2 == 0;
    }


    //Задание координат элементов в ряду,
    // для равномерного распределения по ряду
    // с заданной шириной и отступом (слева)
    // Если элемент один, то он распологается по центру области
    // Если элементов больше одного, то первый и последний элемент располагаются по краям области
    private static void setRowDistributionElements ( final List< Dimension > elementsArg,
                                                     final double widthArg,
                                                     final double leftPaddingArg) {

        if ( elementsArg.size() == 0 ) {
            System.out.println( "В метод setRowDistributionElements() передан пустой список" );
            return;
        }

        double elementWidth = elementsArg.get( 0 ).getWidthWithScaling();

        if ( elementsArg.size() == 1 ) {
            elementsArg.get( 0 ).setX( leftPaddingArg + widthArg - elementWidth );
            return;
        }

        double horizontalInterval = ( widthArg - ( elementsArg.size() * elementWidth ) ) / ( elementsArg.size() - 1 );

        for ( int elementIndex = 0; elementIndex < elementsArg.size(); elementIndex++ ) {
            elementsArg.get( elementIndex ).setX( leftPaddingArg + elementIndex * ( elementWidth + horizontalInterval ) );
        }
    }


    //Задание вертикальной координаты всем элементам в переданном списке
    private static void setElementsY ( final List<Dimension> elements, final double y ) {

        for ( Dimension element : elements ) {
            element.setY( y );
        }
    }
}
