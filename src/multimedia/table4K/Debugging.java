package table4K;

import static table4K.Main4K.*;
import static table4K.book.Book.*;
import static table4K.mail.Mail.*;
import static table4K.map.Map.*;
import static table4K.map.MapPaneInitial.*;

public class Debugging {

    private static double reductionRatio = 2;

    public static void setDebugging() {

        TABLE_WIDTH /= reductionRatio;
        TABLE_HEIGHT /= reductionRatio;

        //иконки разделов
        MAP_ICON_WIDTH_MAX /= reductionRatio;
        MAP_ICON_HEIGHT_MAX /=reductionRatio;
        MAP_ICON_X /= reductionRatio;
        MAP_ICON_Y /= reductionRatio;

        MAIL_ICON_WIDTH_MAX /= reductionRatio;
        MAIL_ICON_HEIGHT_MAX /=reductionRatio;
        MAIL_ICON_X /= reductionRatio;
        MAIL_ICON_Y /= reductionRatio;

        PORTFOLIO_ICON_WIDTH_MAX /= reductionRatio;
        PORTFOLIO_ICON_HEIGHT_MAX /= reductionRatio;
        PORTFOLIO_ICON_X /= reductionRatio;
        PORTFOLIO_ICON_Y /= reductionRatio;

        MEDAL_ICON_WIDTH_MAX /= reductionRatio;
        MEDAL_ICON_HEIGHT_MAX /= reductionRatio;
        MEDAL_ICON_X /= reductionRatio;
        MEDAL_ICON_Y /= reductionRatio;

        BOOK_ICON_WIDTH_MAX /= reductionRatio;
        BOOK_ICON_HEIGHT_MAX /= reductionRatio;
        BOOK_ICON_X /= reductionRatio;
        BOOK_ICON_Y /= reductionRatio;

        //раздел Письма
        MAIL_WIDTH_MAX /= reductionRatio;
        MAIL_WIDTH_SPACING_MIN /= reductionRatio;

        MAIL_FIRST_X /= reductionRatio;
        MAIL_FIRST_Y /= reductionRatio;
        MAIL_SECOND_X /= reductionRatio;
        MAIL_SECOND_Y /= reductionRatio;
        MAIL_THIRD_X /= reductionRatio;
        MAIL_THIRD_Y /= reductionRatio;

        MAIL_AREA_BEGIN_X /= reductionRatio;
        MAIL_AREA_BEGIN_Y /= reductionRatio;
        MAIL_AREA_END_X /= reductionRatio;
        MAIL_AREA_END_Y /= reductionRatio;

        MAIL_LEFT /= reductionRatio;
        MAIL_TOP /= reductionRatio;
        //переопределения размеров для MAIL_RIGHT и MAIL_BOTTOM не сделаны, т.к.
        // они определяются по значениям TABLE_WIDTH и TABLE_HEIGHT
        // которые уже уменьшены
        MAIL_RIGHT = MAIL_RIGHT;
        MAIL_BOTTOM = MAIL_BOTTOM;

        //раздел Книга
        BOOK_WIDTH_MAX /= reductionRatio;
        BOOK_X /= reductionRatio;
        BOOK_Y /= reductionRatio;

        //раздел Карты
        MAP_INITIAL_PANE_WIDTH_MAX /= reductionRatio;
        MAP_INITIAL_PANE_MIN_WIDTH_SPACING /= reductionRatio;
        MAP_INITIAL_TEXT_TOP_PADDING /= reductionRatio;
        MAP_INITIAL_TEXT_FONT /= reductionRatio;

        MAP_INITIAL_AREA_X_BEGIN /= reductionRatio;
        MAP_INITIAL_AREA_Y_BEGIN /= reductionRatio;
        MAP_INITIAL_AREA_X_END /= reductionRatio;
        MAP_INITIAL_AREA_Y_END /= reductionRatio;
    }
}