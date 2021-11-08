package com.haui.phamdai.sqlitesaveimagekpt;

import java.io.Serializable;
import java.util.Arrays;

public class DoVat implements Serializable {
    private int id;
    private String ten;
    private String moTa;
    private byte[] hinhAnh;

    public DoVat(int id, String ten, String moTa, byte[] hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "DoVat{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
