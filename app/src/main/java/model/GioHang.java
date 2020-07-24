package model;

public class GioHang {
    private String macuahang;
    private String mamonan;
    private String tenmonan;
    private int soluong;
    private int gia;
    private String hinhanh;

    public GioHang(String macuahang, String mamonan, String tenmonan, int soluong, int gia, String hinhanh) {
        this.macuahang = macuahang;
        this.mamonan = mamonan;
        this.tenmonan = tenmonan;
        this.soluong = soluong;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }

    public GioHang() {
    }

    public String getMacuahang() {
        return macuahang;
    }

    public void setMacuahang(String macuahang) {
        this.macuahang = macuahang;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
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
