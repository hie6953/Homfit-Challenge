package com.ssafy.homfit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.spring.web.json.Json;

@ApiModel(value = "Favorite : 회원 선호도 정보", description = "회원의 선호도 정보를 나타낸다")
public class Favorite {

    @ApiModelProperty(value = "회원 번호(32자)")
    private String uid;
    @ApiModelProperty(value = "회원의 선호 운동 종류 리스트")
    private String fit_list;
    @ApiModelProperty(value = "회원의 선호 부위 리스트")
    private String body_list;
    @ApiModelProperty(value = "회원의 선호 요일 리스트")
    private String day_list;

    public String getFit_list() {
        return this.fit_list;
    }

    public void setFit_list(String fit_list) {
        this.fit_list = fit_list;
    }

    public String getBody_list() {
        return this.body_list;
    }

    public void setBody_list(String body_list) {
        this.body_list = body_list;
    }

    public String getDay_list() {
        return this.day_list;
    }

    public void setDay_list(String day_list) {
        this.day_list = day_list;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    

}
