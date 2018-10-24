package table.model;

import java.util.ArrayList;

//Класс создан для решения проблемы использования стандартного итератора ArrayList,
//который при последовательности: next, previous, next, previous - отдает один и тот же элемент,
//что, например, при пролистывании страниц книги вперед/назад не приводит к интуативно ожидаемому результату
//Класс может некорректно отрабатывать, при добавлении/удалении элементов (не тестировалось)
public class ArrayListIndex<E> extends ArrayList<E> {

    private int index = -1;

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public E getNextElement() {
        return super.get(++index);
    }

    public E getPrevElement() {
        return super.get(--index);
    }

    public E getCurrentElement() {
        return super.get(index);
    }

    public E getFirstElement() {
        index = 0;
        return getCurrentElement();
    }

    public E getLastElement() {
        index = super.size() - 1;
        return getCurrentElement();
    }

    public int getNextIndex() {
        return (index < super.size() - 1)? ++index: -1;
    }

    public int getPrevIndex() {
        return (index > 0)? --index: -1;
    }

    public int getCurrentIndex() {
        return (index > -1 && index < super.size())? index: -1;
    }

    public boolean hasNextElement() {
        return index < super.size() - 1;
    }

    public boolean hasPrevElement() {
        return index > 0;
    }

    public boolean hasCurrentElement() {
        return index > -1 && index < super.size();
    }

}
