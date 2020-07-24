package model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String mamonan;
    private String tenmonan;
    private int gia;
    private String hinhanh;

    public Menu(String mamonan, String tenmonan, int gia, String hinhanh) {
        this.mamonan = mamonan;
        this.tenmonan = tenmonan;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }

    public Menu() {
    }

    public String getMamonan() {
        return mamonan;
    }

    public void setMamonan(String mamonan) {
        this.mamonan = mamonan;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
