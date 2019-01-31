package table4K.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FileType {

    IMAGE (".bmp", ".gif", ".jpeg", ".jpg", "png"),
    TEXT (".text"),
    VIDEO(".mp4", ".flv", ".fxm");



    private ArrayList<String> fileFormats = new ArrayList<>();

    public ArrayList<String> getFileFormats() {
        return fileFormats;
    }

    FileType(String ... fileFormatsArg) {
        Collections.addAll(fileFormats, fileFormatsArg);
    }

}