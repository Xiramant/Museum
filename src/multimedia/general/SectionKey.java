package general;

public enum SectionKey {

    MAP ("map"),
    PORTFOLIO("portfolio"),
    MAIL ("mail"),
    MEDAL ("medal"),
    BOOK ("book");

    private final String keyWord;

    SectionKey(final String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() { return keyWord; }
}