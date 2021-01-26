package com.ssafy.homfit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User : 회원정보", description = "회원의 상세 정보를 나타낸다")
public class User {

    @ApiModelProperty(value = "회원 번호(32자)")
    private String uid;
    @ApiModelProperty(value = "회원 이메일")
    private String email;
    @ApiModelProperty(value = "회원 비밀번호")
    private String password;
    @ApiModelProperty(value = "회원 닉네임")
    private String nick_name;
    @ApiModelProperty(value = "회원 전화번호")
    private String phone_number;
    @ApiModelProperty(value = "회원 카카오 인증키")
    private String kakao_key;
    @ApiModelProperty(value = "회원 성별")
    private char sex;
    @ApiModelProperty(value = "회원 나이")
    private int age;
    @ApiModelProperty(value = "회원 등급")
    private String grade;
    @ApiModelProperty(value = "회원 프로필 사진 주소")
    private String user_img;
    @ApiModelProperty(value = "회원 or 관리자 확인")
    private boolean admin_check;
    @ApiModelProperty(value = "접속 방법")
    private boolean provider_id;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getKakao_key() {
        return this.kakao_key;
    }

    public void setKakao_key(String kakao_key) {
        this.kakao_key = kakao_key;
    }

    public char getSex() {
        return this.sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUser_img() {
        return this.user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public boolean isAdmin_check() {
        return this.admin_check;
    }

    public boolean getAdmin_check() {
        return this.admin_check;
    }

    public void setAdmin_check(boolean admin_check) {
        this.admin_check = admin_check;
    }

    public boolean isProvider_id() {
        return this.provider_id;
    }

    public boolean getProvider_id() {
        return this.provider_id;
    }

    public void setProvider_id(boolean provider_id) {
        this.provider_id = provider_id;
    }


    @Override
    public String toString() {
        return "User {" +
            " uid='" + getUid() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", nick_name='" + getNick_name() + "'" +
            ", phone_number='" + getPhone_number() + "'" +
            ", kakao_key='" + getKakao_key() + "'" +
            ", sex='" + getSex() + "'" +
            ", age='" + getAge() + "'" +
            ", grade='" + getGrade() + "'" +
            ", user_img='" + getUser_img() + "'" +
            ", admin_check='" + isAdmin_check() + "'" +
            ", provider_id='" + isProvider_id() + "'" +
            "}";
    }


}
