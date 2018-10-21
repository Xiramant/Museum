package table.model;

public enum SectionKey {

    MAP ("map"),
    CASE ("case"),
    MAIL ("mail"),
    MEDAL ("medal");

    private final String keyWord;

    SectionKey(final String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() { return keyWord; }
}
