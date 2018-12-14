package general;

public enum FileFormat {

    IMAGE ("img"),
    TEXT ("text"),
    VIDEO("video");

    private final String keyWord;

    FileFormat(final String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() { return keyWord; }
}
