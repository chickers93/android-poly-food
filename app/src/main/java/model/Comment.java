package model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String avatar;
    private String name;
    private String content;
    private Float rating;
    private String time;

    public Comment(String avatar, String name, String content, Float rating, String time) {
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.rating = rating;
        this.time = time;
    }

    public Comment() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
