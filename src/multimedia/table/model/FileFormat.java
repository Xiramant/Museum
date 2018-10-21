package table.model;

public enum FileFormat {

    IMAGE ("img"),
    TEXT ("text");

    private final String keyWord;

    FileFormat(final String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() { return keyWord; }
}
