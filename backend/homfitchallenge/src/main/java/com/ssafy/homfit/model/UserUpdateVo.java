package com.ssafy.homfit.model;

import org.springframework.web.multipart.MultipartFile;

public class UserUpdateVo {
    private String uid;
    private MultipartFile imgFile;
    private String user_img;
    private String nick_name;
    private String password;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MultipartFile getImgFile() {
        return this.imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getUser_img() {
        return this.user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
