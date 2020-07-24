package model;

import java.io.Serializable;
import java.util.List;

public class DonHang implements Serializable {
    private String id;
    private String uid;
    private String name;
    private String address;
    private String phonenumber;
    private List<GioHang> gioHangList;
    private int total;
    private String status;
    private String time;
    private String macuahang;

    public DonHang(String id, String uid, String name, String address, String phonenumber, List<GioHang> gioHangList, int total, String status, String time, String macuahang) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.gioHangList = gioHangList;
        this.total = total;
        this.status = status;
        this.time = time;
        this.macuahang = macuahang;
    }

    public DonHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<GioHang> getGioHangList() {
        return gioHangList;
    }

    public void setGioHangList(List<GioHang> gioHangList) {
        this.gioHangList = gioHangList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMacuahang() {
        return macuahang;
    }

    public void setMacuahang(String macuahang) {
        this.macuahang = macuahang;
    }
}
