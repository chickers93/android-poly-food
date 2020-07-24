package model;

import java.io.Serializable;

public class CuaHang implements Serializable {
    private String macuahang;
    private String matheloai;
    private String tencuahang;
    private String diachi;
    private Double rating;
    private String hinhanh;
    private Double latitude;
    private Double longitude;

    public CuaHang(String macuahang, String matheloai, String tencuahang, String diachi, Double rating, String hinhanh, Double latitude, Double longitude) {
        this.macuahang = macuahang;
        this.matheloai = matheloai;
        this.tencuahang = tencuahang;
        this.diachi = diachi;
        this.rating = rating;
        this.hinhanh = hinhanh;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CuaHang() {
    }

    public String getMacuahang() {
        return macuahang;
    }

    public void setMacuahang(String macuahang) {
        this.macuahang = macuahang;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
