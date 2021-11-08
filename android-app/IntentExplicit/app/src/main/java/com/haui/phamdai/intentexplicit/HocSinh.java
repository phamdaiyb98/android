package com.haui.phamdai.intentexplicit;

import java.io.Serializable;

public class HocSinh implements Serializable {
    private String hoTen;
    private int namSinh;

    public HocSinh(String hoTen, int namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
