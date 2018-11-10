package general;

public class SliderIndex {

    //Начальный индекс элемента,
    // с которого начинается слайдер
    private int indexBegin;

    //количество элементов в коллекции
    private int size;

    //Количество элементов в слайдере
    private int sizeSlider;

    public SliderIndex(final int indexBeginEnter, final int sizeEnter, final int sizeSliderEnter){
        indexBegin = indexBeginEnter;
        size = sizeEnter;
        sizeSlider = sizeSliderEnter;
     }

    public SliderIndex(final int sizeEnter, final int sizeSliderEnter){
        this(0, sizeEnter, sizeSliderEnter);
    }

    public int getNextSliderIndex() {
        int elementIndex = indexBegin + sizeSlider - ((indexBegin + sizeSlider >= size)? size: 0);
        indexBegin = (++indexBegin == size)? 0: indexBegin;
        return elementIndex;
    }

    public int gePrevSliderIndex() {
        indexBegin = (indexBegin == 0)? size - 1: --indexBegin;
        return indexBegin;
    }

}
