package com.haui.phamdai.multilanguageapp.model;

public class Language {
    private int mId;
    private String mName;
    private String mCode;

    public Language(int mId, String mName, String mCode) {
        this.mId = mId;
        this.mName = mName;
        this.mCode = mCode;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }
}
