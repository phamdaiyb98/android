package com.haui.phamdai.listview;

public class TraiCay {
    private String ten;
    private String moTa;
    private int hinhAnh;

    public TraiCay(String ten, String moTa, int hinhAnh) {
        this.ten = ten;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
