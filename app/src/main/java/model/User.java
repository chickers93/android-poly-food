package model;

import java.io.Serializable;

public class User implements Serializable {
    private String uid;
    private String name;
    private String address;
    private String password;
    private String phonenumber;
    private String email;
    private String birthday;

    public User(String uid, String name, String address, String password, String phonenumber, String email, String birthday) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.birthday = birthday;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
