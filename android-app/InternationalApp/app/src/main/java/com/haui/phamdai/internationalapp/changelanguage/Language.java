package com.haui.phamdai.internationalapp.changelanguage;

public class Language {
    private int image;
    private String code;
    private String name;

    public Language(int image, String code, String name) {
        this.image = image;
        this.code = code;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
